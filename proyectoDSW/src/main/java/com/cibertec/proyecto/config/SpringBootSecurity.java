//package com.cibertec.proyecto.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SpringBootSecurity {
//
//	@Autowired
//	private UserDetailsService userDetailService;
//
//	@Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests()
//		.antMatchers("/administrador/**").hasRole("ADMIN")
//		.antMatchers("/productos/**").hasRole("ADMIN")
//		.and().formLogin().loginPage("/usuario/login")
//		.permitAll().defaultSuccessUrl("/usuario/acceder");
//	}
//
//}
