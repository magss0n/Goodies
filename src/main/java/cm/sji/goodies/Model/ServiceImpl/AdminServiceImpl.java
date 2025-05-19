package cm.sji.goodies.Model.ServiceImpl;

import cm.sji.goodies.Model.DTO.AdminDTO;
import cm.sji.goodies.Model.DTO.LoginRequestDTO;
import cm.sji.goodies.Model.Entities.Admin;
import cm.sji.goodies.Model.Repository.AdminRepository;
import cm.sji.goodies.Model.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Optional<AdminDTO> authenticateAdmin(LoginRequestDTO loginRequest) {
        return adminRepository.findByEmail(loginRequest.getEmail())
                .filter(admin -> passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword()))
                .map(this::mapToDTO);
    }

    @Override
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AdminDTO> getAdminById(Long id) {
        return adminRepository.findById(id)
                .map(this::mapToDTO);
    }

    @Override
    public Optional<AdminDTO> getAdminByEmail(String email) {
        return adminRepository.findByEmail(email)
                .map(this::mapToDTO);
    }

    // Helper method for mapping between Entity and DTO
    private AdminDTO mapToDTO(Admin admin) {
        return AdminDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .phone(admin.getPhone())
                .build();
    }
}
