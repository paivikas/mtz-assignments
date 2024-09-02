//package com.monetize360.contact_web_using_jwt.util;
//package com.monetize360.contact_web_using_jwt.config;
//
//import com.monetize360.contact_web_using_jwt.filter.JWTAuthenticationFilter;
//import com.monetize360.contact_web_using_jwt.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Autowired
//    private JWTAuthenticationFilter jwtAuthenticationFilter;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/contacts/add", "/api/contacts/update", "/api/contacts/get/{id}", "/api/contacts/delete/{id}", "/api/contacts/all/{field}", "/api/contacts/QRCode/{id}", "/api/contacts/import", "/api/contacts/export", "/api/contacts/import/csv", "/api/contacts/send-email/{id}").authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//}
