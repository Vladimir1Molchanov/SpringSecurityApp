package vm.projects.SpringSecurityApp.service;

import vm.projects.SpringSecurityApp.model.SecurityUser;

import java.util.List;

public interface UserService {
    SecurityUser findById(long id);

    List<SecurityUser> findAll();

    SecurityUser saveUser(SecurityUser securityUser);

    void deleteById(long id);

    SecurityUser findByName(String name);
}
