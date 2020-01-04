package com.kimberlysupport.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kimberlysupport.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserPrincipal implements UserDetails, Serializable {

    private long id;
    private String  uniqueId;

    private String name;
    private String nikname;
    private String email;
    private String mobile;

    private String profilePicSrc;
    private String wallPicSrc;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(long id, String uniqueId, String loggedInName, String profilePicSrc, String username, String email, String mobile, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.name =loggedInName;
        this.profilePicSrc=profilePicSrc;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal createWithEmail(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new UserPrincipal(
                user.getId(),
                user.getUniqueId(),
                user.getName(),
                user.getProfilePicSrc(),
                user.getEmail(),
                user.getEmail(),
                user.getMobile(),
                user.getPassword(),
                authorities
        );
    }
    public static UserPrincipal createWithMobile(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return new UserPrincipal(
                user.getId(),
                user.getUniqueId(),
                user.getName(),
                user.getProfilePicSrc(),
                user.getMobile(),
                user.getEmail(),
                user.getMobile(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

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
