package br.com.dsasoft.cf.controller;

import java.util.List;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.dsasoft.cf.document.Account;
import br.com.dsasoft.cf.service.AccountService;

@RestController
@RequestMapping("ws/")
public class AccountController {

	@Autowired
	private AccountService service;

	@RequestMapping(method = RequestMethod.POST, value = "account/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Account save(final @RequestBody Account account) {
		return service.save(account);
	}

	@GetMapping("account/all")
	public List<Account> findAll() {
		return service.findAll();
	}

	@DeleteMapping("account/delete")
	public void delete(final @RequestBody Account account) {
		service.delete(account);
	}

	@GetMapping("account/find/{accountId}")
	public ResponseEntity<Account> findById(final @PathVariable("accountId") String accountId) {
		try {
			return new ResponseEntity<Account>(service.findById(accountId), HttpStatus.OK);
		} catch (NotFoundException nfe) {
			return new ResponseEntity<Account>(new Account(), HttpStatus.NOT_FOUND);
		}
	}
}