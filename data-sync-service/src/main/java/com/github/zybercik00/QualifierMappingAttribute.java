package com.github.zybercik00;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class QualifierMappingAttribute implements MappingAttribute {
    private final String targetProperty;
    private final Map<String, Object> qualifier;
    private final String qualifierProperty;
    private final String qualifierParent;
}