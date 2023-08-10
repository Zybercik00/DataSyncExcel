package com.github.zybercik00;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class QualifiedFields {

    private String path;
    private String qualifierProperty;
    private String qualifierParent;
    private Map<String, Object> qualifier;
}
