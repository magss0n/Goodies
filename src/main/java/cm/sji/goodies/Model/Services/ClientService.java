package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.DTO.ClientDTO;
import cm.sji.goodies.Model.DTO.LoginRequestDTO;
import cm.sji.goodies.Model.DTO.RegisterRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    ClientDTO registerClient(RegisterRequestDTO registerRequest);

    Optional<ClientDTO> authenticateClient(LoginRequestDTO loginRequest);

    List<ClientDTO> getAllClients();

    Optional<ClientDTO> getClientById(Long id);

    Optional<ClientDTO> getClientByEmail(String email);

    ClientDTO updateClient(Long id, ClientDTO clientDTO);

    void deleteClient(Long id);

    boolean existsByEmail(String email);
}
