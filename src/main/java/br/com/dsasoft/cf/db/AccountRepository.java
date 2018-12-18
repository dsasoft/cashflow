package br.com.dsasoft.cf.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.dsasoft.cf.document.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

}
