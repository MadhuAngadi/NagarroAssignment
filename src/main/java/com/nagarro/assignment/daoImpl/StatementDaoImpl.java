package com.nagarro.assignment.daoImpl;

import com.nagarro.assignment.dao.StatementDao;
import com.nagarro.assignment.entities.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class StatementDaoImpl implements StatementDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(StatementDaoImpl.class);

    @Override
    //get all statements of last 3 months
    public List<Statement> getAllStatements(long accountId) throws Exception{
        List<Statement> statements;
        logger.info("StatementDaoImpl.getAllStatements() ");
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime fromDate = now.minusMonths(3);
            statements = jdbcTemplate.query("SELECT * FROM statement WHERE account_id = ? AND TO_DATE(datefield, 'DD.MM.YYYY') >= ?;",
                    preparedStatement -> {
                        preparedStatement.setDouble(1, accountId);
                        preparedStatement.setDate(2, java.sql.Date.valueOf(fromDate.toLocalDate()));
                    }, new StatementDaoImpl.StatementExtractor());
            logger.debug("Query Result : " + statements);
        }
        catch( Exception e){
            logger.info(e.toString());
            throw e;
        }
        return statements;
   }
    @Override
    public List<Statement> getStatementWithinDates(long accountId, Date fromDate, Date toDate) throws Exception{
        List<Statement> statements;
        logger.info("StatementDaoImpl.getStatementWithinDates() ");
        try {
            statements = jdbcTemplate.query("SELECT * FROM statement WHERE account_id = ? AND TO_DATE(datefield, 'DD.MM.YYYY') BETWEEN ? AND ?;",
                    preparedStatement -> {
                        preparedStatement.setDouble(1, accountId);
                        preparedStatement.setDate(2, new java.sql.Date(fromDate.getTime()));
                        preparedStatement.setDate(3, new java.sql.Date(toDate.getTime()));
                    }, new StatementDaoImpl.StatementExtractor());
            logger.debug("Query Result : " + statements);
        }
        catch( Exception e){
            logger.info(e.toString());
            throw e;
        }
        return statements;
    }

    @Override
    public List<Statement> getStatementForAmountRange(long accountId, double fromAmount, double toAmount) throws Exception{
        List<Statement> statements;
        logger.info("StatementDaoImpl.getStatementForAmountRange() ");
        try {
            statements = jdbcTemplate.query("SELECT ID, account_id, datefield, CAST(amount as DECIMAL(9,2)) amount FROM statement where  account_id=? AND CAST(amount as DECIMAL(9,2)) between ? AND ?;",
                    preparedStatement -> {
                        preparedStatement.setDouble(1, accountId);
                        preparedStatement.setDouble(2, fromAmount);
                        preparedStatement.setDouble(3, toAmount);
                    }, new StatementDaoImpl.StatementExtractor());
            logger.debug("Query Result : " + statements);
        }
        catch( Exception e){
            logger.info(e.toString());
            throw e;
        }
         return statements;
    }

    @Override
    public List<Statement> getStatementWithinDatesForAmountRange(long accountId, Date fromDate, Date toDate, double fromAmount, double toAmount) throws Exception{
        List<Statement> statements = new ArrayList<>();
        logger.info("StatementDaoImpl.getStatementWithinDatesForAmountRange() ");
        try {
            statements = jdbcTemplate.query("SELECT ID, account_id, datefield, CAST(amount as DECIMAL(9,2)) amount FROM statement where  account_id=? AND CAST(amount as DECIMAL(9,2)) between ? AND ? AND TO_DATE(datefield, 'DD.MM.YYYY') BETWEEN ? AND ?; ",
                    preparedStatement -> {
                        preparedStatement.setDouble(1, accountId);
                        preparedStatement.setDouble(2, fromAmount);
                        preparedStatement.setDouble(3, toAmount);
                        preparedStatement.setDate(4, new java.sql.Date(fromDate.getTime()));
                        preparedStatement.setDate(5, new java.sql.Date(toDate.getTime()));
                    }, new StatementDaoImpl.StatementExtractor());
            logger.debug("Query Result : " + statements);
        }
        catch( Exception e){
            logger.info(e.toString());
            throw e;
        }
        return statements;
    }

    public class StatementExtractor implements ResultSetExtractor<List<Statement>> {
        @Override
        public List<Statement> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<Statement> statementList = new ArrayList<>();
            while(rs.next()){
                Statement statement = new Statement();
                statement.setId(rs.getLong("ID"));
                statement.setAccountId(rs.getDouble("account_id"));
                statement.setDateField(rs.getString("datefield"));
                statement.setAmount(rs.getString("amount"));
                statementList.add(statement);
            }
            logger.info("StatementExtractor.extractData() : " + statementList);
            return statementList;
        }
    }
}
