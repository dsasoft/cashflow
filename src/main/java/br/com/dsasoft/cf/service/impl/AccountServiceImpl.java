package br.com.dsasoft.cf.service.impl;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dsasoft.cf.db.AccountRepository;
import br.com.dsasoft.cf.db.document.Account;
import br.com.dsasoft.cf.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repo;

	@Override
	public Account save(Account account) {
		return repo.save(account);
	}

	@Override
	public void delete(Account account) {
		repo.delete(account);
	}

	@Override
	public Account update(Account account) {
		return repo.save(account);
	}

	@Override
	public Account findById(final String accountId) {
		Optional<Account> op = repo.findById(accountId);

		if (op.isPresent())
			return op.get();
		throw new NotFoundException();
	}

	@Override
	public List<Account> findAll() {
		return repo.findAll();
	}

}
