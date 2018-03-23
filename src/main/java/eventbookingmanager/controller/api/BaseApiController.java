package main.java.eventbookingmanager.controller.api;

import main.java.eventbookingmanager.models.BaseEntity;
import main.java.eventbookingmanager.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

@RestController
@RequestMapping(path="/api")
public class BaseApiController<T extends BaseEntity> {

    @PersistenceContext
    protected EntityManager entityManager;
}
