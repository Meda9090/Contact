package com.example.ddd.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Filter {
    private Integer limit;
    private Integer offset;
}
