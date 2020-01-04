package com.kimberlysupport.security;


import com.kimberlysupport.model.User;
import com.kimberlysupport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Let people login with either email or mobile
        UserPrincipal userPrincipal = null;
        Optional<User> user = null;

        user = userRepository.findByEmail(username);
        if (user.isPresent())
            userPrincipal = UserPrincipal.createWithEmail(user.get());
        else {
            user = userRepository.findByMobile(username);
            if (user.isPresent())
                userPrincipal = UserPrincipal.createWithMobile(user.get());
        }

        if(userPrincipal==null)
            throw new UsernameNotFoundException("User not found with username: " + username);
        else
            return userPrincipal;

    }

}
