package com.github.zybercik00;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.zybercik00.domain.process.Extraction;
import com.github.zybercik00.domain.process.Waste;
import com.github.zybercik00.repository.process.ExtractionRepo;
import com.github.zybercik00.repository.process.metadata.AttributeEntityRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.*;

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
        Extraction extraction = getExtractionByConstrain(cursor);
        for (Mapping mapping : extractionMappingService.getMappings()) {
            mappingService.setSimpleValue(
                    cursor,
                    extraction,
                    attributeService.getAttribute(mapping.getTarget()),
                    mapping.getSource());
        }
        return extractionRepo.save(extraction);
    }

    private Extraction getExtractionByConstrain(ExcelTableWithHeader.Cursor cursor) {
        String materialLot = cursor.getStringValue("Lot");
        Date preparedOn = cursor.getDateValue("Made on");
        return getExtraction(materialLot, preparedOn);
    }

    private Extraction getExtraction(String materialLot, Date preparedOn) {
        Optional<Extraction> persistedExtraction = extractionRepo.findByMaterialLotAndPreparedOn(materialLot, preparedOn);
        return persistedExtraction.orElseGet(() -> extractionRepo.save(new Extraction()));
    }

}