package com.nagarro.assignment.controllers;

import com.nagarro.assignment.model.StatementReq;
import com.nagarro.assignment.model.StatementRes;
import com.nagarro.assignment.services.StatementService;
import com.nagarro.assignment.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController

public class StatementController {
    @Autowired
    private StatementService statementService;
    @Autowired
    private Validator validator;

    private static final Logger logger = LoggerFactory.getLogger(StatementController.class);

    //both user and admin
    @GetMapping("/statements/{accountId}")
    public List<StatementRes> getAllStatementsForUser(@PathVariable long accountId) throws Exception {
        logger.info("StatementController.getAllStatementsForUser() called!");
        logger.info("Request var accountId = " + accountId);
        validator.validateAccountId(accountId);
        List<StatementRes> statements = statementService.getAllStatements(accountId);
        logger.info("Response object : " + statements);
        return statements;
    }

    //only admin can access the below method

    @PostMapping("/statements/{accountId}")
    public List<StatementRes> getAllStatementsForAdmin(@PathVariable long accountId, @RequestBody @Valid StatementReq statementReq) throws Exception {
        logger.info("StatementController.getAllStatementsForAdmin() called");
        logger.info("Request var: accountId = " + accountId + " StatementReq = " + statementReq);
        validator.validateAccountId(accountId, statementReq.getAccountId());
        List<StatementRes> statements = getStatements(statementReq, accountId);
        logger.info("Response object : " + statements);
        return statements;
    }

    //only admin can access the below method
    @PostMapping("/statements")
    public List<StatementRes> getAllStatements(@RequestBody StatementReq statementReq) throws Exception {
        logger.info("StatementController.getAllStatements() called");
        logger.info("Request var: StatementReq = " + statementReq);
        validator.validateAccountId(statementReq.getAccountId());
        List<StatementRes> statements = getStatements(statementReq, statementReq.getAccountId());
        logger.info("Response object : " + statements);
        return statements;
    }

    public List<StatementRes> getStatements(StatementReq statementReq, long accountId) throws Exception {
        logger.debug("StatementController.getStatements()");
        Date fromDate = statementReq.getFromDate();
        Date toDate = statementReq.getToDate();
        Double fromAmount = statementReq.getFromAmount();
        Double toAmount = statementReq.getToAmount();

        logger.debug("fromDate : " + fromDate + " toDate : " + toDate + " fromAmount : " + fromAmount + " toAmount : " + toAmount);

        validator.ValidateDateAndAmountVariables(fromDate, toDate, fromAmount, toAmount);

        if (null != fromDate && null != toDate && null != fromAmount && null != toAmount) {
            return statementService.getStatementWithinDatesForAmountRange(accountId, fromDate, toDate, fromAmount, toAmount);
        } else if (null != fromDate && null != toDate) {
            return statementService.getStatementWithinDates(accountId, fromDate, toDate);
        } else if (null != fromAmount && null != toAmount) {
            return statementService.getStatementForAmountRange(accountId, fromAmount, toAmount);
        } else {
            return statementService.getAllStatements(accountId);
        }
    }




}
