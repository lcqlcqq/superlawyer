package com.lcq.config;

import com.lcq.pojo.Permission;
import com.lcq.pojo.User;
import com.lcq.security.MyUserDetail;
import com.lcq.service.AuthUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    AuthUserService userService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();    //使用表单登录
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            String pwd = userService.getPWDByUsername(username);
            System.out.println(pwd);
            if (pwd != null) {
                List<Permission> permissionList = userService.getUserPermissions(username);
                for (Permission p : permissionList){
                    System.out.println(p.getUri());
                }
                User user = new User();
                user.setUsername(username);
                user.setPassword(pwd);
                return new MyUserDetail(user, permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    //@Bean
    //public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
    //    return new JwtAuthenticationTokenFilter();
    //}

    @Bean   //这里需要将AuthenticationManager注册为Bean，在OAuth配置中使用
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public JwtAccessTokenConverter tokenConverter(){  //Token转换器，将其转换为JWT
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("lbwnb");   //这个是对称密钥，一会资源服务器那边也要指定为这个
        return converter;
    }

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter converter){  //Token存储方式现在改为JWT存储
        return new JwtTokenStore(converter);  //传入刚刚定义好的转换器
    }
}