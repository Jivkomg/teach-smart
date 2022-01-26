package bg.sofia.uni.fmi.piss.project.tm.config;

import bg.sofia.uni.fmi.piss.project.tm.jwt.JWTAuthenticationFilter;
import bg.sofia.uni.fmi.piss.project.tm.jwt.JWTTokenVerifier;
import bg.sofia.uni.fmi.piss.project.tm.services.DetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Component
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DetailsService detailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
//            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//            .addFilterAfter(new JWTTokenVerifier(), JWTAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers("/registrationForm", "/css/**", "/images/**", "/js/**").permitAll()
            .antMatchers("/index", "/main", "/courses/**", "/myProfile", "/loginForm").permitAll()
            .antMatchers("/user/**", "/userCourses", "/users", "/file/**").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and()
            //wants to authenticate using a form
            .formLogin()
            //use a custom login form - every user that is not authenticated
            .loginPage("/index")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .httpBasic()
            .and()
            .csrf().disable();

        http.headers().frameOptions().disable();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(Long.MAX_VALUE);
        return multipartResolver;
    }
}
