package com.mk.backend.assignement.banking.configuration;

import com.mk.backend.assignement.banking.jwt.JwtTokenFilter;
import com.mk.backend.assignement.banking.navigation.Navigation;
import com.mk.backend.assignement.banking.repositories.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    private final UserRepository  userRepository  ;

    private JwtTokenFilter jwtTokenFilter  ;



    public WebSecurityConfig(UserRepository userRepository , JwtTokenFilter jwtTokenFilter) {
        this.userRepository  = userRepository  ;
        this.jwtTokenFilter  = jwtTokenFilter ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("Username :"+username+" Not found")));


    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().disable().csrf().disable();
        // Set session management to stateless
                http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();
        http.authorizeRequests().antMatchers(Navigation.TRANSACTION_API+"/**").authenticated().and()
                .authorizeRequests().antMatchers(Navigation.ACCOUNT_API+"/**").authenticated().and()
                .authorizeRequests().antMatchers(Navigation.CUSTOMER_API+"/**").authenticated().and()
                .authorizeRequests().antMatchers(Navigation.LOGIN_API+"/**").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.addFilterBefore(
                jwtTokenFilter,
                UsernamePasswordAuthenticationFilter.class
        );
        http.httpBasic().disable();
        http.headers().frameOptions().disable();

    }




}
