package com.example.demo.security;

import com.example.demo.security.jwt.AuthEntryPointJwt;
import com.example.demo.security.jwt.AuthTokenFilter;
import com.example.demo.services.AppUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    // bcrypt appUserService
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bcryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    // open register http security
    @Autowired
    protected void configure(HttpSecurity http) throws Exception {

        // http.cors().and().csrf().disable().authorizeRequests().antMatchers("/api/users/**",
        // "/").permitAll()
        // .anyRequest().authenticated()
        // // login
        // // .and().formLogin().permitAll().defaultSuccessUrl("/api/products")
        // // logout
        // .and().logout().logoutUrl("/logout").clearAuthentication(true).invalidateHttpSession(true)
        // .deleteCookies("JSESSIONID").logoutSuccessUrl("/login");

        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/api/users/**").permitAll().anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
