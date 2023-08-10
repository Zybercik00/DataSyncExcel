package com.github.zybercik00;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attributes {
    private JsonQualifiedAttribute jsonQualifiedAttribute;
    private JsonReferencedAttribute jsonReferencedAttribute;
    private JsonSimpleAttribute jsonSimpleAttribute;
}
