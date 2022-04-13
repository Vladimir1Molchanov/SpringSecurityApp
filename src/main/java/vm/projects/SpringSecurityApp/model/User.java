package vm.projects.SpringSecurityApp.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    @ManyToMany(targetEntity = Role.class)
    private Set<Role> roles;

    @Override
    public String getAuthority() {
        String roles = null;
        for (Role role : getRoles()) {
            roles = role.getRoleName();
        }

        return roles;
    }
}
