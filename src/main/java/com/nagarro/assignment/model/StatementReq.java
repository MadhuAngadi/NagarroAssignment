package com.nagarro.assignment.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class StatementReq {
    @NotNull(message = "Invalid AccountId: AccountId is NULL")
    private Long accountId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    private Double fromAmount;
    private Double toAmount;
}
