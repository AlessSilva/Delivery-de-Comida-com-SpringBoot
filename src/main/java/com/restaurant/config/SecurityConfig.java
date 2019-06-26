package com.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.restaurant.security.UserDetailsServiceImplement;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsServiceImplement udsi;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
	   http.csrf().disable().authorizeRequests()
	  
	  .antMatchers("/").permitAll()
	  .antMatchers("/cliente/cadastro").not().authenticated()
	  .antMatchers("/cliente/salvar").not().authenticated()
	  .antMatchers("/prato/listar").permitAll()
	  .antMatchers("/entrar").permitAll()
	  .antMatchers("/cliente/listar").hasRole("ADMIN")
	  .antMatchers("/pedido/listar").hasRole("USER")
	  .antMatchers("/pedido/confirmar").hasRole("USER")
	  .antMatchers("/pedido/listarTodos").hasRole("ADMIN")
	  .antMatchers("/carrinho/**").hasRole("USER")
	  .antMatchers("/prato/cadastro").hasRole("ADMIN")
	  .antMatchers("/prato/salvar").hasRole("ADMIN")
	  .antMatchers("/prato/excluir/**").hasRole("ADMIN")
	  .antMatchers("/prato/atualizar/**").hasRole("ADMIN")
	  
	  .anyRequest().authenticated()
	  
	  .and()
	  .formLogin().loginPage("/entrar")
	  .permitAll()
	  
	  .and()
	  .logout()
	  .logoutSuccessUrl("/entrar?logout")
	  .permitAll();
	  
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(udsi).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/CSS/**", "/JS/**", "/images/**");
	}
	
}
