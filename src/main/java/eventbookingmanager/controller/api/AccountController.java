package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.models.Account;
import main.java.eventbookingmanager.models.dto.AccountDTO;
import main.java.eventbookingmanager.repository.AccountRepository;
import main.java.eventbookingmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"Compte"}, description ="Opérations concernant les comptes utilisateurs")
public class AccountController extends BaseApiController<Account> {

    @Autowired
    protected AccountRepository repository;

    @ApiOperation(value = "Obtenir tous les comptes utilisateurs")
    @GetMapping(path = "account", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> retrieveAccounts() {
        return (List<Account>) repository.findAll();
    }

    @Transactional
    @ResponseBody
    @ApiOperation(value = "Créer un compte utilisateur")
    @PostMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account createAccount(@RequestBody Account account) {
        String encodedPassword = new AccountService().encodePassword(account.getPassword());
        account.setPassword(encodedPassword);

        entityManager.persist(account);
        entityManager.flush();

        return account;
    }

    //
    // TODO : Améliorer la sécurité
    //
    @ApiOperation(value = "Authentification à partir de l'email et du mot de passe")
    @PostMapping(path = "/account/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Account authenticate(@RequestBody AccountDTO accountDTO) {
        Optional<Account> accountOptional = Optional.ofNullable(repository.findByEmail(accountDTO.getEmail()));

        if (!accountOptional.isPresent()) {
            throw new RuntimeException("No account found for email "+accountDTO.getEmail());
        }

        boolean isCorrect = new AccountService().checkCredentials(accountOptional.get(), accountDTO.getPassword());

        if (!isCorrect) {
            throw new RuntimeException("Password incorrect");
        }

        return accountOptional.get();
    }
}
