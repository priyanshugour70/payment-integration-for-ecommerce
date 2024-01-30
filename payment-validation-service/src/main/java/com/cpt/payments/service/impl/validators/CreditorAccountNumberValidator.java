package com.cpt.payments.service.impl.validators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cpt.payments.constants.ErrorCodeEnum;
import com.cpt.payments.exceptions.ValidationException;
import com.cpt.payments.pojo.PaymentRequest;
import com.cpt.payments.service.Validator;
import com.cpt.payments.util.LogMessage;

import io.micrometer.core.instrument.util.StringUtils;

@Component
public class CreditorAccountNumberValidator implements Validator {
private static final Logger LOGGER = LogManager.getLogger(CreditorAccountNumberValidator.class);
	
	private static final String ACCOUNT_NUMBER_PATTERN = "[0-9]{1,20}";

	@Override
	public void doValidate(PaymentRequest paymentRequest) {
		LogMessage.log(LOGGER, " Validating CreditorAccountNumber request for -> " + paymentRequest);
		if (StringUtils.isBlank(paymentRequest.getPayment().getDebitorAccount())
				|| !paymentRequest.getPayment().getDebitorAccount().matches(ACCOUNT_NUMBER_PATTERN)) {
			LogMessage.log(LOGGER, " CreditorAccountNumber  feild is missing ");
			throw new ValidationException(HttpStatus.BAD_REQUEST,
					ErrorCodeEnum.CREDITOR_ACCOUNT_NUMBER_VALIDATION_FAILED.getErrorCode(),
					ErrorCodeEnum.CREDITOR_ACCOUNT_NUMBER_VALIDATION_FAILED.getErrorMessage());
		}
	}

}
