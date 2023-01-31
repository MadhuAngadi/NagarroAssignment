package com.nagarro.assignment.model;

import lombok.Data;

@Data
public class StatementRes {
    private long id;
    private String accountId;
    private String dateField;
    private String amount;

}
