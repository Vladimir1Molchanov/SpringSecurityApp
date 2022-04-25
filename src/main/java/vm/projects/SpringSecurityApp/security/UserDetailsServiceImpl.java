package vm.projects.SpringSecurityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vm.projects.SpringSecurityApp.model.Role;
import vm.projects.SpringSecurityApp.repository.UserRepository;

import java.util.*;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<vm.projects.SpringSecurityApp.model.User> securityUserOpt = userRepository.findByFirstName(username);

        vm.projects.SpringSecurityApp.model.User user = securityUserOpt.get();
        Set<GrantedAuthority> authority = new HashSet<>();
        for (Role role : user.getRoles()) {
            authority.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new User(
                user.getUsername(),
                user.getPassword(),
                authority);
    }
}
