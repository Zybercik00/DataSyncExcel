package com.github.zybercik00;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReferenceMappingAttribute implements MappingAttribute {
    private final String targetProperty;
    private final String nestedProperty;
}