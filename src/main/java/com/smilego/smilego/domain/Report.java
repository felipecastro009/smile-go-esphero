package com.smilego.smilego.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Report {
    private Integer activeSubscriptions;
    private Integer inactiveSubscriptions;
    private BigDecimal totalAmount;

    @JsonCreator
    public Report(
            @JsonProperty("activeSubscriptions") Integer activeSubscriptions,
            @JsonProperty("inactiveSubscriptions") Integer inactiveSubscriptions,
            @JsonProperty("totalAmount") BigDecimal totalAmount
    ) {
        this.activeSubscriptions = activeSubscriptions;
        this.inactiveSubscriptions = inactiveSubscriptions;
        this.totalAmount = totalAmount;
    }
}
