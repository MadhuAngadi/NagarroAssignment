package com.nagarro.assignment.util;

import com.nagarro.assignment.exceptions.InvalidArgumentsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Validator {

    private static final Logger logger = LoggerFactory.getLogger(Validator.class);
    public boolean validateFromDateLesserThanToDate(Date fromDate, Date toDate) {
        return !fromDate.after(toDate);
    }

    public boolean validateFromToDateLesserThanCurrentDate(Date fromDate, Date toDate) {
        return !fromDate.after(new Date()) && !toDate.after(new Date());
    }

    public boolean validateAccountId(Long accountId) {
        if(accountId == null) {
            logger.error("Account Id should not be null!");
            throw new InvalidArgumentsException("AccountId should not be null!");
        }
        return true;
    }

    public boolean validateAccountId(Long accountIdFromPathVar, Long accountIdFromReqBody) {
        if(null == accountIdFromPathVar || null == accountIdFromReqBody ) {
            logger.error("AccountId in Request URL and Request Body should not be null!");
            throw new InvalidArgumentsException("AccountId in Request URL and Request Body should not be null!");
        }
        if(!accountIdFromPathVar.equals(accountIdFromReqBody)){
            logger.error("AccountId in Request URL and Request Body must be same!");
            throw new InvalidArgumentsException("AccountId in Request URL and Request Body must be same!");
        }
        return true;
    }
    public boolean ValidateDateAndAmountVariables(Date fromDate, Date toDate, Double fromAmount, Double toAmount) {

        if((null == fromDate && null != toDate) || (null != fromDate && null == toDate)){
            logger.error("FromDate and ToDate must be entered together ");
            throw new InvalidArgumentsException("FromDate and ToDate must be entered together");
        }
        if(null != fromDate && null != toDate){
            if(!validateFromDateLesserThanToDate(fromDate, toDate)) {
                logger.error("FromDate should be lesser than ToDate");
                throw new InvalidArgumentsException("FromDate should be lesser than ToDate");
            }
            if(!validateFromToDateLesserThanCurrentDate(fromDate, toDate)) {
                logger.error("FromDate and ToDate should be less than current date");
                throw new InvalidArgumentsException("FromDate and ToDate should be less than current date");
            }
        }
        if((null == fromAmount && null != toAmount) || (null != fromAmount && null == toAmount) ){
            logger.error("FromAmount and ToAmount must be entered together ");
            throw new InvalidArgumentsException("FromAmount and ToAmount must be entered together");
        }
        if(null != fromAmount && null != toAmount){
            if(!validateFromAmountLesserThanToAmount(fromAmount, toAmount)){
                logger.error("FromAmount should be lesser than ToAmount");
                throw new InvalidArgumentsException("FromAmount should be lesser than ToAmount");
            }
        }
        return true;
    }

    private boolean validateFromAmountLesserThanToAmount(double fromAmount, double toAmount) {
        return (fromAmount < toAmount);
    }
}
