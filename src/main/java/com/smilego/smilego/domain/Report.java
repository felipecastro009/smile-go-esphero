package com.smilego.smilego.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Report {
    private Integer activeSubscriptions;
    private Integer inactiveSubscriptions;
    private BigDecimal totalAmount;
}
