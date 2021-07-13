package us.peaksoft.springbootmvcsecurity.security_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import us.peaksoft.springbootmvcsecurity.security_config.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(@Qualifier("userDetailServiceImpl") UserDetailsService userDetailsService,
                          LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .formLogin()
                .successHandler(loginSuccessHandler)
                .loginPage("/login")
                .successHandler(new LoginSuccessHandler())
                .loginProcessingUrl("/login")
                .permitAll();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/signUp").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/saveUser").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/edit/{id}").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/update/{id}").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/delete/{id}").access("hasAnyRole('ROLE_ADMIN')")
                .anyRequest()
                .authenticated();

//        http.formLogin()
//                .permitAll()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .successHandler(loginSuccessHandler);
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}