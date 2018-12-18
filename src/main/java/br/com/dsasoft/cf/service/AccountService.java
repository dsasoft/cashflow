package br.com.dsasoft.cf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.dsasoft.cf.document.Account;

@Service
public interface AccountService {

	public Account save(final Account account);

	public void delete(final Account account);

	public void update(final Account account);

	public Account findById(final String accountId);

	public List<Account> findAll();
}
