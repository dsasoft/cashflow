package br.com.dsasoft.cf.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.dsasoft.cf.document.Account;

@Service
public interface OperationsService {

	public String withdrawal(final Account account, final BigDecimal amount);

	public String deposit(final Account account, final BigDecimal amount);

}
