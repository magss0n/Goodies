package cm.sji.goodies.Model.ServiceImpl;

import cm.sji.goodies.Model.DTO.ClientDTO;
import cm.sji.goodies.Model.DTO.LoginRequestDTO;
import cm.sji.goodies.Model.DTO.RegisterRequestDTO;
import cm.sji.goodies.Model.Entities.Client;
import cm.sji.goodies.Model.Repository.ClientRepository;
import cm.sji.goodies.Model.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public ClientDTO registerClient(RegisterRequestDTO registerRequest) {
        Client client = new Client();
        client.setName(registerRequest.getName());
        client.setEmail(registerRequest.getEmail());
        client.setPhone(registerRequest.getPhone());
        client.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        client.setOrders(new HashSet<>());

        Client savedClient = clientRepository.save(client);
        return mapToDTO(savedClient);
    }

    @Override
    public Optional<ClientDTO> authenticateClient(LoginRequestDTO loginRequest) {
        return clientRepository.findByEmail(loginRequest.getEmail())
                .filter(client -> passwordEncoder.matches(loginRequest.getPassword(), client.getPassword()))
                .map(this::mapToDTO);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDTO> getClientById(Long id) {
        return clientRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public Optional<ClientDTO> getClientByEmail(String email) {
        return clientRepository.findByEmail(email)
                .map(this::mapToDTO);
    }

    @Override
    @Transactional
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setName(clientDTO.getName());
                    client.setEmail(clientDTO.getEmail());
                    client.setPhone(clientDTO.getPhone());
                    Client updatedClient = clientRepository.save(client);
                    return mapToDTO(updatedClient);
                })
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    // Helper method for mapping between Entity and DTO
    private ClientDTO mapToDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .build();
    }
}