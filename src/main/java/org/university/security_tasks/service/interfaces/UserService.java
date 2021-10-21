package org.university.security_tasks.service.interfaces;

import org.university.security_tasks.dto.UserDto;
import org.university.security_tasks.model.StoreUser;
import org.university.security_tasks.security.UserPrincipal;
import java.util.List;

public interface UserService {

    void register(UserDto userDto);

    List<StoreUser> getAll();

    StoreUser getById(StoreUser user, UserPrincipal sender);
}
