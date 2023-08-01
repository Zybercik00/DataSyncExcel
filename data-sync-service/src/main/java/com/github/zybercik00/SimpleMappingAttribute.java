package com.github.zybercik00;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SimpleMappingAttribute implements MappingAttribute {
    private final String targetProperty;
}