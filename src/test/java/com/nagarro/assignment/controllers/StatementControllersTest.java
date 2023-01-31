package com.nagarro.assignment.controllers;

import com.nagarro.assignment.model.StatementReq;
import com.nagarro.assignment.model.StatementRes;
import com.nagarro.assignment.services.StatementService;
import com.nagarro.assignment.servicesImpl.StatementServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class StatementControllersTest {
    @Autowired
    private StatementController statementController;

    @Autowired
    private StatementService statementService;

    private static final Logger logger = LoggerFactory.getLogger(StatementControllersTest.class);

    List<StatementRes> statementResList = Arrays.asList(
            new StatementRes(1, "123", "01.01.2023", "1000")
    );
    @Test
    public void getAllStatementsForUserTest() throws Exception {
        logger.info("StatementControllersTest.getAllStatementsForUserTest() running..");
        Mockito.when(statementService.getAllStatements(1)).thenReturn(statementResList);
        List<StatementRes> statementResList2 = statementController.getAllStatementsForUser(1);
        Assert.assertEquals(statementResList, statementResList2);
        logger.info("StatementControllersTest.getAllStatementsForUserTest() run success!");
    }

    @Test
    public void getAllStatementsForAdminWithinAmountRangeTest() throws Exception {
        logger.info("StatementControllersTest.getAllStatementsForAdminWithinAmountRangeTest() running..");
        StatementReq statementReq = new StatementReq();
        long value =1; double fromAmount = 10; double toAmount = 11110;
        statementReq.setAccountId(value);
        statementReq.setFromAmount(fromAmount);
        statementReq.setToAmount(toAmount);
        Mockito.when(statementService.getStatementForAmountRange(1, fromAmount, toAmount)).thenReturn(statementResList);

        List<StatementRes> statementResList2 = statementController.getAllStatementsForAdmin(1, statementReq);
        Assert.assertEquals(statementResList, statementResList2);
        logger.info("StatementControllersTest.getAllStatementsForAdminWithinAmountRangeTest() run success!");
    }

    @Test
    public void getAllStatementsForAdminBetweenDatesTest() throws Exception {
        logger.info("StatementControllersTest.getAllStatementsForAdminBetweenDatesTest() running..");
        StatementReq statementReq = new StatementReq();
        long value =1;
        Date fromDate = new Date("2013/01/01");
        Date toDate = new Date("2023/01/01");
        statementReq.setAccountId(value);
        statementReq.setFromDate(fromDate);
        statementReq.setToDate(toDate);
        Mockito.when(statementService.getStatementWithinDates(1, fromDate, toDate)).thenReturn(statementResList);
        List<StatementRes> statementResList2 = statementController.getAllStatementsForAdmin(1, statementReq);
        Assert.assertEquals(statementResList, statementResList2);
        logger.info("StatementControllersTest.getAllStatementsForAdminBetweenDatesTest() run success!");
    }

    @Test
    public void getAllStatementsForAdminWithinAmountRangeBetweenDatesTest() throws Exception {
        logger.info("StatementControllersTest.getAllStatementsForAdminWithinAmountRangeBetweenDatesTest() running..");
        StatementReq statementReq = new StatementReq();
        long value =1; double fromAmount = 10; double toAmount = 11110;
        Date fromDate = new Date("2013/01/01");
        Date toDate = new Date("2023/01/01");
        statementReq.setAccountId(value);
        statementReq.setFromAmount(fromAmount);
        statementReq.setToAmount(toAmount);
        statementReq.setFromDate(fromDate);
        statementReq.setToDate(toDate);
        Mockito.when(statementService.getStatementWithinDatesForAmountRange(1, fromDate, toDate, fromAmount, toAmount)).thenReturn(statementResList);
        List<StatementRes> statementResList2 = statementController.getAllStatementsForAdmin(1, statementReq);
        Assert.assertEquals(statementResList, statementResList2);
        logger.info("StatementControllersTest.getAllStatementsForAdminWithinAmountRangeBetweenDatesTest() run success!");
    }

    @Test
    public void getAllStatementsTest() throws Exception {
        logger.info("StatementControllersTest.getAllStatementsTest() running..");
        Mockito.when(statementService.getAllStatements(1)).thenReturn(statementResList);
        StatementReq statementReq = new StatementReq();
        long value =1;
        statementReq.setAccountId(value);
        List<StatementRes> statementResList2 = statementController.getAllStatements(statementReq);
        Assert.assertEquals(statementResList, statementResList2);
        logger.info("StatementControllersTest.getAllStatementsTest() run success!");
    }
}