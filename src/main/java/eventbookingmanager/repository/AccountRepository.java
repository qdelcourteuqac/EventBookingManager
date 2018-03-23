package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.Account;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface AccountRepository extends BaseRepository<Account> {
    Account findByEmail(String email);
}
