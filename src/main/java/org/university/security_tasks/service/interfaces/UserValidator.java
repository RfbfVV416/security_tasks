package org.university.security_tasks.service.interfaces;

import org.university.security_tasks.model.StoreUser;
import org.university.security_tasks.security.UserPrincipal;

public interface UserValidator {
    boolean isUserSender(StoreUser user, UserPrincipal sender);
    boolean isSenderAdmin(UserPrincipal sender);
}
