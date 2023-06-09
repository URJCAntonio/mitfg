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
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	//.antMatchers("/profes","/examen{id}","/addsimple","/addmedio","/addcomplejo","/estadisticas").hasAnyAuthority("PROFESORES")
        	.antMatchers("/signin","/revision").hasAnyAuthority("ADMINISTRADOR","PROFESORES")
        	
        	.antMatchers("/administradores","/listaUsuarios","/banUsuarios","/asignarAulas{nombre}","/asignarAulasSubmit","/perfilAlumno{id}",
        			"/cambiarContra","/crearAula","/crearAulaSubmit","/listaAulas","/gestionarAula{id}","/asignarAlumnosSubmit","/cambiarNombre",
        			"/cambiarCurso","/cambiarAsignatura").hasAnyAuthority("ADMINISTRADOR")
        	
        	.antMatchers("/profes","/crearExamen","/crearExamenSubmit","/simple","/medio","/complejo","/addsimple","/addmedio","/addcomplejo",
        			"/examenes","/borrar","/copiar{id}","/estadisticasAlumno","/estadisticas","/estadisticasGrupales{id}","/rankearAlumnos{id}",
        			"/cambiarCurso","/borrarEjercicios","/borrarEjerciciosSubmit").hasAnyAuthority("PROFESORES")
        	
        	.antMatchers("/modoEvaluacion","/autoevaluacionCasual","/autoevaluacionFormal","/autoevaluacionFormalSubmit","/examinarse","/examinarseSubmit",
        			"/miPerfil","/cambiarMiContra","/misEstadisticas","/miAutoevaluacion").hasAnyAuthority("ALUMNOS")
        	
        	.antMatchers("/css/**","/js/**","/fonts/**","/img/**").permitAll()
            .antMatchers("/","/done","/logout","/login","/contenidos","/autoevaluacion","/registro").permitAll()
/*aux*/     .antMatchers("/corregir", "/autoevaluacionFormalSubmit").permitAll()
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
