package org.university.security_tasks.service.impl;

import org.springframework.stereotype.Service;
import org.university.security_tasks.model.StoreUser;
import org.university.security_tasks.model.UserRolesProvider;
import org.university.security_tasks.security.UserPrincipal;
import org.university.security_tasks.service.interfaces.UserValidator;

@Service
public class UserValidatorImpl implements UserValidator {

    @Override
    public boolean isUserSender(StoreUser user, UserPrincipal sender) {
        return user != null &&
                sender != null &&
                user.getEmail().equals(sender.getUsername());
    }

    @Override
    public boolean isSenderAdmin(UserPrincipal sender){
        return sender.getAuthorities().stream()
                .anyMatch((a) -> a.getAuthority().equals(UserRolesProvider.ROLE_ADMIN.toString()));
    }
}
