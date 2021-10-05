package by.andruhovich.petclinic.repository;

import by.andruhovich.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
