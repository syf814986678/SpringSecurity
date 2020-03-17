package com.shiyifan.Service;

import com.shiyifan.Controller.JdbcController;
import com.shiyifan.UserInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private JdbcController jdbcController;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = jdbcController.password(username);

        String role = userInfo.getRole();
        System.out.println("role"+role);
        // 角色集合
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
        authorities.add(new SimpleGrantedAuthority(role));
        System.out.println(authorities);

        return new User(userInfo.getUsername(),passwordEncoder.encode(userInfo.getPassword()), authorities);
    }
}
