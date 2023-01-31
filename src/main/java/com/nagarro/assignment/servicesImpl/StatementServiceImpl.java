package com.nagarro.assignment.servicesImpl;

import com.nagarro.assignment.daoImpl.StatementDaoImpl;
import com.nagarro.assignment.entities.Statement;
import com.nagarro.assignment.model.StatementRes;
import com.nagarro.assignment.services.StatementService;
import com.nagarro.assignment.util.AccountNumberHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatementServiceImpl implements StatementService {
    @Autowired
    private StatementDaoImpl statementDaoImpl;

    private static final Logger logger = LoggerFactory.getLogger(StatementServiceImpl.class);
    private AccountNumberHash accountNumberHash = new AccountNumberHash();

    @Override
     public List<StatementRes> getAllStatements(long accountId) throws Exception {
        logger.debug("StatementServiceImpl.getAllStatements()");
        List<Statement> statements = statementDaoImpl.getAllStatements(accountId);
        logger.debug("Statements From Query: " + statements);
        List<StatementRes> statementResList = prepareStatementResponse(statements);
        logger.debug("statementResList after hashing accountId : " + statementResList);
        return statementResList;
    }

    @Override
    public List<StatementRes> getStatementWithinDates(long accountId, Date fromDate, Date toDate) throws Exception {
        logger.debug("StatementServiceImpl.getStatementWithinDates()");
        List<Statement> statements = statementDaoImpl.getStatementWithinDates(accountId, fromDate, toDate);
        logger.debug("Statements From Query: " + statements);
        List<StatementRes> statementResList = prepareStatementResponse(statements);
        logger.debug("statementResList after hashing accountId : " + statementResList);
        return statementResList;
       }

    @Override
    public List<StatementRes> getStatementForAmountRange(long accountId, double fromAmount, double toAmount) throws Exception {
        logger.debug("StatementServiceImpl.getStatementForAmountRange()");
        List<Statement> statements = statementDaoImpl.getStatementForAmountRange(accountId, fromAmount, toAmount);
        logger.debug("Statements From Query: " + statements);
        List<StatementRes> statementResList = prepareStatementResponse(statements);
        logger.debug("statementResList after hashing accountId : " + statementResList);
        return statementResList;
       }

    @Override
    public List<StatementRes> getStatementWithinDatesForAmountRange(long accountId, Date fromDate, Date toDate, double fromAmount, double toAmount) throws Exception {
        logger.debug("StatementServiceImpl.getStatementWithinDatesForAmountRange()");
        List<Statement> statements = statementDaoImpl.getStatementWithinDatesForAmountRange(accountId, fromDate, toDate, fromAmount, toAmount);
        logger.debug("Statements From Query: " + statements);
        List<StatementRes> statementResList = prepareStatementResponse(statements);
        logger.debug("statementResList after hashing accountId : " + statementResList);
        return statementResList;
        }

    private List<StatementRes> prepareStatementResponse(List<Statement> statements) {
        List<StatementRes> statementResList = new ArrayList<>();
        statements.forEach(statement -> {
            StatementRes statementRes =  new StatementRes();
            statementRes.setId(statement.getId());
            statementRes.setAmount(statement.getAmount());
            statementRes.setDateField(statement.getDateField());
            try {
                statementRes.setAccountId(accountNumberHash.hashAccountNumber(String.valueOf(statement.getAccountId())));
            } catch (NoSuchAlgorithmException e) {
                logger.error("NoSuchAlgorithmException to hash AccountNumber :"+ e);
                statementRes.setAccountId("");
            }
            statementResList.add(statementRes);
        });
        return statementResList;
    }
}
