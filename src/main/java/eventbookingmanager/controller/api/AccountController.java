package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.models.Account;
import main.java.eventbookingmanager.models.AccountDTO;
import main.java.eventbookingmanager.repository.AccountRepository;
import main.java.eventbookingmanager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @ApiOperation(value = "Créer un compte utilisateur")
    @PostMapping(path = "/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> createStudent(@RequestBody Account account) {
        String encodedPassword = new AccountService().encodePassword(account.getPassword());
        account.setPassword(encodedPassword);
        Account savedAccount = repository.save(account);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedAccount.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    //
    // TODO : Améliorer la sécurité
    //
    @ApiOperation(value = "Authentification à partir de l'email et du mot de passe")
    @PostMapping(path = "/account/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean authenticate(@RequestBody AccountDTO accountDTO) {
        Optional<Account> accountOptional = Optional.ofNullable(repository.findByEmail(accountDTO.getEmail()));

        if (!accountOptional.isPresent()) {
            throw new RuntimeException("No account found for email "+accountDTO.getEmail());
        }

        return new AccountService().checkCredentials(accountOptional.get(), accountDTO.getPassword());
    }
}
