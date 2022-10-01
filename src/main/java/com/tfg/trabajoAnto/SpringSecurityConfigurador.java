package com.tfg.trabajoAnto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurador extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserDetailsService userDetails;
	
     
    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    protected InMemoryUserDetailsManager userDetailsService2() {
        UserDetails user = User
                .withUsername("administrador")
                .password(passwordEncoder().encode("administrador"))
                .roles("ADMIN")
                .build();      
         
        return new InMemoryUserDetailsManager(user);
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
        auth.userDetailsService(userDetailsService2());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/profes","/examen{id}","/simple","/medio","/complejo","/addsimple","/addmedio","/addcomplejo","/estadisticas").hasAnyAuthority("PROFESORES")
        	.antMatchers("/css/**","/js/**","/fonts/**","/img/**").permitAll()
            .antMatchers("/","/done","/signin","/logout","/login","/contenidos","/autoevaluacion","/registro","/estadisticas").permitAll()
/*aux*/     .antMatchers("/corregir").permitAll()
            ;
     // Login form
     		http.formLogin().loginPage("/login");
     		http.formLogin().usernameParameter("usuario");
     		http.formLogin().passwordParameter("contra");
     		http.formLogin().defaultSuccessUrl("/");
     		http.formLogin().failureUrl("/login");
     		
     	// Logout
            http.logout().logoutUrl("/logout");
            http.logout().logoutSuccessUrl("/");
    }
}
