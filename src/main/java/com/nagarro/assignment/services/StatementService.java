package com.nagarro.assignment.services;

import com.nagarro.assignment.model.StatementRes;

import java.util.Date;
import java.util.List;

public interface StatementService {
    List<StatementRes> getAllStatements(long accountId) throws Exception;

    List<StatementRes> getStatementWithinDates(long accountId, Date fromDate, Date toDate) throws Exception;

    List<StatementRes> getStatementForAmountRange(long accountId, double fromAmount, double toAmount) throws Exception;

    List<StatementRes> getStatementWithinDatesForAmountRange(long accountId, Date fromDate, Date toDate, double fromAmount, double toAmount) throws Exception;
}
