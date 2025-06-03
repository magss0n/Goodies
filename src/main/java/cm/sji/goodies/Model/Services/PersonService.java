package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.Entities.Client;
import cm.sji.goodies.Model.Entities.Person;
import cm.sji.goodies.Model.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Client> getAllClients() {
        return personRepository.findAllClients().orElse(new ArrayList<>());
    }

    public Client getClientByName(String name) {
        return (Client) personRepository.findByName(name).orElse(null);
    }

    public Client getClientByEmail(String email) {
        return (Client) personRepository.findByEmail(email).orElse(null);
    }

    public void addPerson(Person person) {
        personRepository.save(person);
    }

    public Client getClientByPhone(String phone) {
        return (Client) personRepository.findByPhone(phone).orElse(null);
    }
}
