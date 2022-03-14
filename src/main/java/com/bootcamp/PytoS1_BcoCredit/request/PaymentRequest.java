package com.bootcamp.PytoS1_BcoCredit.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PaymentRequest {

    private int idCredit;
    private BigDecimal amount;
    private Date datePay;

}
