package main.java.eventbookingmanager.controller.api;

import main.java.eventbookingmanager.models.BaseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping(path="/api")
public class BaseApiController<T extends BaseEntity> {

    @PersistenceContext
    protected EntityManager entityManager;
}
