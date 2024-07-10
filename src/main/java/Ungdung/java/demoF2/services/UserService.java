package Ungdung.java.demoF2.services;

import Ungdung.java.demoF2.constants.Provider;
import Ungdung.java.demoF2.constants.Role;
import Ungdung.java.demoF2.entities.User;
import Ungdung.java.demoF2.repositories.IRoleRepository;
import Ungdung.java.demoF2.repositories.IUserRepository;
import Ungdung.java.demoF2.validators.annotations.ValidUsername;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Slf4j
@NoArgsConstructor
@ValidUsername
public class UserService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    @Transactional(isolation = Isolation.SERIALIZABLE,
            rollbackFor = {Exception.class, Throwable.class})
    public void save(@NotNull User user) {
        user.setPassword(new BCryptPasswordEncoder() .encode(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) throws
            UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,
            rollbackFor = {Exception.class, Throwable.class})
    public void Save(@NotNull User user) {
        userRepository.save(user);
    }


    @Transactional
    public void saveOauthUser(String email, @NotNull String username) {
        if(userRepository.findByUsername(username) == null)
            return;
        var user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(username));
        user.setProvider(Provider.GOOGLE.value);
        user.getRoles().add(roleRepository.findRoleById(Role.USER.value));
        userRepository.save(user);
    }
    public void setDefaultRole(String username){
        userRepository.findByUsername(username).getRoles()
                .add(roleRepository
                        .findRoleById(Role.USER.value));
    }
}