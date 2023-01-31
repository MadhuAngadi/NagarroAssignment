package com.nagarro.assignment.dao;

import com.nagarro.assignment.entities.Statement;

import java.util.Date;
import java.util.List;

public interface StatementDao {
    //get all statements of last 3 months
    List<Statement> getAllStatements(long accountId) throws Exception;

    List<Statement> getStatementWithinDates(long accountId, Date fromDate, Date toDate) throws Exception;

    List<Statement> getStatementForAmountRange(long accountId, double fromAmount, double toAmount) throws Exception;

    List<Statement> getStatementWithinDatesForAmountRange(long accountId, Date fromDate, Date toDate, double fromAmount, double toAmount) throws Exception;
}
