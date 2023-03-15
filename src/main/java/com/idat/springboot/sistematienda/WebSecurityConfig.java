package com.idat.springboot.sistematienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.idat.springboot.sistematienda.service.JpaUserDetailsService;
import com.idat.springboot.sistematienda.service.UsuarioServicio;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder);
		return auth;
	}
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		auth.userDetailsService(userDetailsService);
	}
	
	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**","/icons/**","/uploads/**"
    };
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers(resources).permitAll()
		.antMatchers("/producto").hasAnyRole("ADMIN", "RECEPCIONISTA", "CAJERO", "REPONEDOR")
		.antMatchers("/productos").hasAnyRole("ADMIN", "RECEPCIONISTA", "CAJERO")
		.antMatchers("/cliente/**").hasAnyRole("ADMIN", "RECEPCIONISTA", "CAJERO")
		.antMatchers("/clientes/**").hasAnyRole("ADMIN", "CAJERO")
		.antMatchers("/proveedor").hasAnyRole("ADMIN", "RECEPCIONISTA")
		.antMatchers("/proveedores").hasAnyRole("ADMIN", "RECEPCIONISTA")
		.antMatchers("/categoria").hasAnyRole("ADMIN", "REPONEDOR", "RECEPCIONISTA")
		.antMatchers("/categorias").hasAnyRole("ADMIN", "REPONEDOR", "RECEPCIONISTA")
		.antMatchers("/empleado").hasAnyRole("ADMIN")
		.antMatchers("/empleados").hasAnyRole("ADMIN")
		.antMatchers("/ventas/").hasAnyRole("ADMIN", "CAJERO")
		.antMatchers("/vender/").hasAnyRole("ADMIN", "CAJERO")
		.antMatchers("/metodo").hasAnyRole("ADMIN", "CAJERO")
		.antMatchers("/metodos").hasAnyRole("ADMIN")
		.antMatchers("/usuario").hasAnyRole("ADMIN")
		.antMatchers("/usuarios").hasAnyRole("ADMIN")
		.antMatchers("/rol").hasAnyRole("ADMIN")
		.antMatchers("/roles").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/")
			.and()
		.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1440)
			.and()
		.logout()
			.permitAll()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/login?logout")
			.and();
	}
	
}