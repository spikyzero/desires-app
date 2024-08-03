package ua.com.desires.app.webtoken;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.desires.app.exception.UserNotFoundException;
import ua.com.desires.app.model.User;
import ua.com.desires.app.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .map(userObj -> org.springframework.security.core.userdetails.User.builder()
                        .username(userObj.getEmail())
                        .password(userObj.getPassword())
                        .roles(getRoles(userObj))
                        .build())
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }

}