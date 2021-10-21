package org.university.security_tasks.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.university.security_tasks.dto.UserDto;
import org.university.security_tasks.exceptions.NoSuchEntityException;
import org.university.security_tasks.model.StoreUser;
import org.university.security_tasks.repository.UserRepository;
import java.util.Optional;
import static org.university.security_tasks.model.UserRolesProvider.ROLE_ADMIN;
import static org.university.security_tasks.model.UserRolesProvider.ROLE_USER;

@Service
@AllArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<StoreUser> userOptional = Optional.ofNullable(userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NoSuchEntityException("There is no user with email " + email)));

        return UserPrincipal.create(userOptional.get());
    }

    public void registerNewUser(UserDto userDto){

        String email = userDto.getEmail();
        StoreUser user = new StoreUser();
        user.setEmail(email);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));


        if (System.getenv("ADMIN_EMAIL").equals(email)) {
            user.setRole(ROLE_ADMIN);
        }
        else {
            user.setRole(ROLE_USER);
        }
        userRepository.save(user);
    }

}
