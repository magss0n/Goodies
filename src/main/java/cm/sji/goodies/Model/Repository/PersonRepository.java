package cm.sji.goodies.Model.Repository;

import cm.sji.goodies.Model.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
