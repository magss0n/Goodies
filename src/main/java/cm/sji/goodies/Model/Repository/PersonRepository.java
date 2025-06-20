package cm.sji.goodies.Model.Repository;

import cm.sji.goodies.Model.Entities.Client;
import cm.sji.goodies.Model.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p from Person p where p.class = Client ")
    Optional<List<Client>> findAllClients();

    Optional<Person> findByEmail(String email);

    Optional<Person> findByName(String name);

    Optional<Person> findByPhone(String phone);
}
