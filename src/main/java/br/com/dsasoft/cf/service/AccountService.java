package br.com.dsasoft.cf.service;

import java.util.List;

import br.com.dsasoft.cf.db.document.Account;

public interface AccountService extends DefaultBasicService<Account> {

	public Account save(final Account account);

	public void delete(final Account account);

	public Account update(final Account account);

	public Account findById(final String accountId);

	public List<Account> findAll();
}
