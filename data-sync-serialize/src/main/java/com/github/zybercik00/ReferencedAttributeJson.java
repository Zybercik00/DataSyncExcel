package com.github.zybercik00;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReferencedAttributeJson {
    private String path;
    private String targetProperty;
    private String nestedProperty;
}
