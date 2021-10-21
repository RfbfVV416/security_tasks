package org.university.security_tasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.university.security_tasks.dto.UserDto;
import org.university.security_tasks.model.StoreUser;
import org.university.security_tasks.security.UserPrincipal;
import org.university.security_tasks.service.interfaces.UserService;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/auth")
    public void register(@RequestBody @Valid UserDto user) {
        userService.register(user);

    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<StoreUser> getAll(){
        return userService.getAll();
    }

    @GetMapping("{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public StoreUser getById(@PathVariable("userId") StoreUser user, @AuthenticationPrincipal UserPrincipal sender) {
        return userService.getById(user, sender);
    }
}
