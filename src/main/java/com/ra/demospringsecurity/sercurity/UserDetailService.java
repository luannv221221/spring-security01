package com.ra.demospringsecurity.sercurity;

import com.ra.demospringsecurity.model.User;
import com.ra.demospringsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        List<SimpleGrantedAuthority> grantedAuthoritySet = user.getRoles().
                stream().map(item->new SimpleGrantedAuthority(item.getName())).toList();
        return new UserPrinciple(user.getId(),user.getUserName(),user.getPassword(),grantedAuthoritySet);
    }
}
