package main.java.eventbookingmanager.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import main.java.eventbookingmanager.models.Account;
import main.java.eventbookingmanager.models.AuthenticationRequest;
import main.java.eventbookingmanager.models.Event;
import main.java.eventbookingmanager.models.JwtTokens;
import main.java.eventbookingmanager.repository.AccountRepository;
import main.java.eventbookingmanager.service.AccountService;
import main.java.eventbookingmanager.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = {"Compte"}, description = "Opérations concernant les comptes utilisateurs")
public class AccountController extends BaseApiController<Account> {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    protected AccountRepository repository;

    @ApiOperation(value = "Obtenir tous les comptes utilisateurs")
    @GetMapping(path = "account", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> retrieveAccounts() {
        return (List<Account>) repository.findAll();
    }


    @ApiOperation(value = "Obtenir tous les comptes utilisateurs")
    @GetMapping(path = "account/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> retrieveAccount(@PathVariable long id) {
        Optional<Account> account = Optional.ofNullable(repository.findOne(id));

        if (!account.isPresent())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(account.get(), HttpStatus.OK);
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

    @ApiOperation(value = "Authentification à partir de l'email et du mot de passe")
    @PostMapping(path = "/account/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(authentication != null && authentication.isAuthenticated()) {
            JwtTokens tokens = jwtTokenService.createTokens(authentication);
            return ResponseEntity.ok().body(tokens);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}