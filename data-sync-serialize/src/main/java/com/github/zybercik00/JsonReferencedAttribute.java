package com.github.zybercik00;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JsonReferencedAttribute {

    private List<ReferencedFields> refAttributes;
}
