package vm.projects.SpringSecurityApp.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "roleName")
    private String roleName;
    @Column(name = "users")
    @ManyToMany(targetEntity = User.class, mappedBy = "roles")
    private List<User> users;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
