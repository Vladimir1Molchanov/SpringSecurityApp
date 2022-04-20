package vm.projects.SpringSecurityApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vm.projects.SpringSecurityApp.model.SecurityUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SecurityUser, Long> {
    Optional<SecurityUser> findByFirstName(String name);

}
