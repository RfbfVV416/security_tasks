package org.university.security_tasks.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.university.security_tasks.dto.UserDto;
import org.university.security_tasks.exceptions.WrongPasswordException;
import org.university.security_tasks.model.StoreUser;
import org.university.security_tasks.repository.UserRepository;
import org.university.security_tasks.security.CustomUserService;
import org.university.security_tasks.security.UserPrincipal;
import org.university.security_tasks.service.interfaces.UserService;
import org.university.security_tasks.service.interfaces.UserValidator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public final UserValidator userValidator;
    public final UserRepository userRepository;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;
    public final CustomUserService customUserService;

    @Override
    public void register(UserDto userDto){

        Optional<StoreUser> userOptional = userRepository.findByEmail(userDto.getEmail());
        if (userOptional.isEmpty()){
            customUserService.registerNewUser(userDto);
        }
        else if (!bCryptPasswordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
            throw new WrongPasswordException("Wrong password entered");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        UserDetails userDetails = customUserService.loadUserByUsername(userDto.getEmail());
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    @Override
    public List<StoreUser> getAll(){
        return userRepository.findAll();
    }

    @Override
    public StoreUser getById(StoreUser user, UserPrincipal sender){
        if (!userValidator.isUserSender(user, sender) && !userValidator.isSenderAdmin(sender)) {
            throw new AccessDeniedException("You can't get this user's data.");
        }
        return userRepository.getById(user.getId());
    }
}
