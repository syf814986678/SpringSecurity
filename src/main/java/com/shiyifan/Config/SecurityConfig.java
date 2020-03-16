package com.shiyifan.Config;


import com.shiyifan.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {   //链式编程
        //首页所有人可以访问，功能页只有有权限的人访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/password").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        //没有权限到登录页面
        http.formLogin().usernameParameter("user")//接收login页面中的<input type="text" placeholder="Username" name="user">
                        .passwordParameter("pwd")//接收login页面中的<input type="password" name="pwd">
                        .loginPage("/toLogin")
                        .loginProcessingUrl("/login");
        //开始注销功能
        http.logout().logoutSuccessUrl("/index").logoutUrl("/tologout");
        //关闭跨站请求攻击
        http.csrf().disable();
        //开启记住我
        http.rememberMe().rememberMeParameter("rememberme");
}

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //认证规则  认证用户的信息（数据库或者内存）
        //密码编码 passwordencoder
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("shiyifan")
//                .password(new BCryptPasswordEncoder().encode("123456"))
//                .roles("vip2","vip3")
//                .and()
//                .withUser("root")
//                .password(new BCryptPasswordEncoder().encode("toor"))
//                .roles("vip1","vip2","vip3")
//                .and()
//                .withUser("guest")
//                .password(new BCryptPasswordEncoder().encode("guest"))
//                .roles("vip1");
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());



    }

}
