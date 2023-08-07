package com.github.zybercik00;

import com.github.zybercik00.domain.process.Extraction;
import com.github.zybercik00.domain.process.Waste;
import com.github.zybercik00.repository.process.ExtractionRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ExtractionService {
    private final ExtractionRepo extractionRepo;
    private final MappingService mappingService;
    private final ExcelTableFactory excelTableFactory;
    private final MappingAttributeService attributeService;
    private final ExtractionMappingService extractionMappingService;

    @Transactional
    public List<Extraction> getExtractions(XSSFSheet sheet) {
        List<Extraction> extractions = new ArrayList<>();
        ExcelTableWithHeader excelTableWithHeader = excelTableFactory.getExcelTable(sheet);
        ExcelTableWithHeader.Cursor cursor = excelTableWithHeader.cursor();
        while (cursor.next()) {
            Extraction extraction = getExtraction(cursor);
            extractions.add(extraction);
        }
        return extractions;
    }

    @SneakyThrows
    private Extraction getExtraction(ExcelTableWithHeader.Cursor cursor) {
        Extraction extraction = getExtraction();
        for (Mapping mapping : extractionMappingService.getMappings()) {
            mappingService.setSimpleValue(
                    cursor,
                    extraction,
                    attributeService.getAttribute(mapping.getTarget()),
                    mapping.getSource());
        }
        // TODO Use batch updates
        return extractionRepo.save(extraction);
    }

    private Extraction getExtraction() {
        // TODO Should be also incremental
        Extraction extraction = new Extraction();
        extraction.setWaste(new Waste());
        extraction.setPurchasePrices(new ArrayList<>());
        extraction.setSalePrices(new ArrayList<>());
        return extractionRepo.save(extraction);
    }

}