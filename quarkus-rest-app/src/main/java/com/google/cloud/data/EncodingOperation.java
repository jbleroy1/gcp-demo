package com.google.cloud.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EncodingOperation {

    private final static int DEFAULT_DELAY = 0;

    private String value;
    private String result;
    private int delay = DEFAULT_DELAY;
}
