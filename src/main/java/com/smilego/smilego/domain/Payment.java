package com.smilego.smilego.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Payment {
    private Long id;
    private String subscriptionId;
    private BigDecimal amount;
    private String status;
    private String method;
    private LocalDateTime paymentDate;
}
