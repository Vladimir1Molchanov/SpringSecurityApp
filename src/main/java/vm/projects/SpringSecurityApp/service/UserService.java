package vm.projects.SpringSecurityApp.service;

import vm.projects.SpringSecurityApp.model.User;

import java.util.List;

public interface UserService {
    User findById(long id);

    List<User> findAll();

    User saveUser(User user);

    void deleteById(long id);

    User findByName(String name);
}
