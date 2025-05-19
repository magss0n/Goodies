package cm.sji.goodies.Model.Services;

import cm.sji.goodies.Model.DTO.AdminDTO;
import cm.sji.goodies.Model.DTO.LoginRequestDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Optional<AdminDTO> authenticateAdmin(LoginRequestDTO loginRequest);

    List<AdminDTO> getAllAdmins();

    Optional<AdminDTO> getAdminById(Long id);

    Optional<AdminDTO> getAdminByEmail(String email);
}