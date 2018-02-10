package me.leking.web.sample.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by jinlei on 2017/4/21.
 */
@Service
public class AdminUserDetailsService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!username.equals("admin")) {
            throw new UsernameNotFoundException(username + " not found");
        }

        UserDetails user = new User("admin",
            "admin",
            AuthorityUtils.createAuthorityList("ADMIN"));

        return user;
    }
}
