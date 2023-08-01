package com.github.zybercik00;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Mapping {
    private final String target;
    private final String source;
}