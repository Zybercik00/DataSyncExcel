package com.github.zybercik00;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Attributes {

    private List<QualifiedAttributeJson> qualified;
    private List<ReferencedAttributeJson> reference;
    private List<SimpleAttributeJson> simple;
}
