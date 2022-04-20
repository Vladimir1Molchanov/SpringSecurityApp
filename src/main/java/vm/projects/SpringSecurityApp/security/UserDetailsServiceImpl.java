package vm.projects.SpringSecurityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vm.projects.SpringSecurityApp.model.SecurityUser;
import vm.projects.SpringSecurityApp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SecurityUser> securityUserOpt = userRepository.findByFirstName(username);
        if (securityUserOpt.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        SecurityUser securityUser = securityUserOpt.get();
        GrantedAuthority authority = new SimpleGrantedAuthority(securityUser.getRoles().toString());
        UserDetails userDetails = new User(
                securityUser.getUsername(),
                securityUser.getPassword(),
                Arrays.asList(authority)
        );
        return userDetails;
    }
}
