package org.university.security_tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.university.security_tasks.model.StoreUser;
import org.university.security_tasks.model.UserRolesProvider;
import org.university.security_tasks.repository.UserRepository;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) {
        userRepository.saveAll(createUsers());
    }


    public List<StoreUser> createUsers(){
        return List.of(
                createUser("Алёна", "Бебешко", UserRolesProvider.ROLE_USER, "bebeshkoA@gmail.com", "juY5*vKl"),
                createUser("Alyona", "Bebeshko", UserRolesProvider.ROLE_USER, "bebeshkomail@mail.ru", "1ADj8.4v5")
        );
    }

    public StoreUser createUser(String firstName, String lastName, UserRolesProvider role, String email, String password){
        StoreUser user = new StoreUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return user;
    }

}


