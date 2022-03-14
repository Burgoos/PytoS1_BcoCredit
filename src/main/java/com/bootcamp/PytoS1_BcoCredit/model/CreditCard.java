package com.bootcamp.PytoS1_BcoCredit.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CreditCard {
    @Id
    private int id;
    private int idClient;
    private String type;
    private BigDecimal limit;
    private BigDecimal amountUsed;
    private BigDecimal interestRateMonth;
    private int fees;
    private int feesPaid;
    private int cutoffDate;
    private int limitDate;
    private List<Payment> payments;

}
