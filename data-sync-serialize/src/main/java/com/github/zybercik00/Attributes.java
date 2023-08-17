package com.github.zybercik00;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Attributes {


    private List<SimpleAttributeJson> simple;
    private List<ReferencedAttributeJson> reference;
    private List<QualifiedAttributeJson> qualified;
}
