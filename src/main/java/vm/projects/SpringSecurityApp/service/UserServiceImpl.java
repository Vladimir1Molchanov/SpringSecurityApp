package vm.projects.SpringSecurityApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vm.projects.SpringSecurityApp.model.Role;
import vm.projects.SpringSecurityApp.model.SecurityUser;
import vm.projects.SpringSecurityApp.repository.RoleRepository;
import vm.projects.SpringSecurityApp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository
            , RoleRepository roleRepository
            , PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SecurityUser findById(long id) {
        return userRepository.getOne(id);
    }

    public List<SecurityUser> findAll() {
        return userRepository.findAll();
    }

    public SecurityUser saveUser(SecurityUser securityUser) {
        securityUser.setPassword(passwordEncoder.encode(securityUser.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        securityUser.setRoles(roles);
        return userRepository.save(securityUser);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public SecurityUser findByName(String name) {
        return userRepository.findByFirstName(name).orElse(new SecurityUser());

    }
}
