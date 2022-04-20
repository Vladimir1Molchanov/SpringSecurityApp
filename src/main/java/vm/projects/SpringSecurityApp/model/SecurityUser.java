package vm.projects.SpringSecurityApp.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "security_users")
public class SecurityUser implements UserDetails {

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
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roleList = new ArrayList<>();
//        roleList.addAll(getRoles());
        for (Role role : getRoles()) {
            roleList.add(role);
        }
        return roleList;
//        return getRoles();
    }

    @Override
    public String getUsername() {
        return getFirstName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDetails fromUser(SecurityUser securityUser) {
        return new org.springframework.security.core.userdetails.User(
                securityUser.getFirstName(), securityUser.getPassword(),
                securityUser.isEnabled(),
                securityUser.isAccountNonExpired(),
                securityUser.isCredentialsNonExpired(),
                securityUser.isAccountNonLocked(),
                securityUser.getAuthorities()
        );
    }
}
