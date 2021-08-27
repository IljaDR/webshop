package be.petshop.webshop.auth;

import be.petshop.webshop.daos.UserDAO;
import be.petshop.webshop.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userDAO.findByUsername(s);
        if (user.isPresent()){
            List<SimpleGrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority("USER"));
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getHash(), grantedAuthorities);
        }
        else {
            throw new UsernameNotFoundException("No user with this username exists.");
        }
    }
}
