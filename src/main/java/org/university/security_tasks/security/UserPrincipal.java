package org.university.security_tasks.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.university.security_tasks.model.StoreUser;
import org.university.security_tasks.model.UserRolesProvider;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.university.security_tasks.model.UserRolesProvider.ROLE_ADMIN;
import static org.university.security_tasks.model.UserRolesProvider.ROLE_USER;


public class UserPrincipal implements UserDetails {

    private final Long id;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Collection<? extends GrantedAuthority> authorities, Long id, String email) {
        this.authorities = authorities;
        this.id = id;
        this.email = email;

    }

    public static UserPrincipal create(StoreUser user){
        List<GrantedAuthority> authorities;
        UserRolesProvider userRole = user.getRole();

        if (ROLE_ADMIN.equals(userRole)) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority(ROLE_ADMIN.toString()));
        } else {
            authorities = Collections.singletonList(new SimpleGrantedAuthority(ROLE_USER.toString()));
        }

        return new UserPrincipal(
                authorities,
                user.getId(),
                user.getEmail()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { return true; }
}
