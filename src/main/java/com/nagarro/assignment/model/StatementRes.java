package com.nagarro.assignment.model;

import lombok.Data;

@Data
public class StatementRes {
    private long id;
    private String accountId;
    private String dateField;
    private String amount;

    public StatementRes(){

    }

    public StatementRes(long id, String accountId, String dateField, String amount) {
        super();
        this.id = id;
        this.accountId = accountId;
        this.dateField = dateField;
        this.amount = amount;
    }
}
