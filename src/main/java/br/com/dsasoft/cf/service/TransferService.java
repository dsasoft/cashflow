package br.com.dsasoft.cf.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.dsasoft.cf.document.Account;

@Service
public interface TransferService {

	public String transter(final Account originId, final Account destiny, final BigDecimal amount);

}
