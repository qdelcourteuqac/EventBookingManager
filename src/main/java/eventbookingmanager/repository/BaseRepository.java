package main.java.eventbookingmanager.repository;

import main.java.eventbookingmanager.models.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface BaseRepository<T extends BaseEntity> extends CrudRepository<T, Long> {
}
