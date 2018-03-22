package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByEmail(String email);
}
