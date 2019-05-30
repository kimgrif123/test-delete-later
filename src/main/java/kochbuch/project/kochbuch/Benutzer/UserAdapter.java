package kochbuch.project.kochbuch.Benutzer;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserAdapter implements UserDetails
{
    /*
    TODO COMMENT: class - UserAdapter
    The following class is required to integrate the spring security framework
    and handles the interactions of the security framework with the User object.

     */
    private final User user;

    public UserAdapter(User user)
    {
        this.user = user;
    }

    public User getUser() {return user;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    public String getPassword()
    {
        return user.getPassword();
    }
    public String getUsername() {return user.getUsername();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
