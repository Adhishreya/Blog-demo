package com.postblog.Bloggart.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.postblog.Bloggart.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authProvider());
		auth.userDetailsService(userService);
	}

	// enabling http security
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		antMatchers specfy the endpints that can be accessed
		// by specifying formLogin() a default login page will be generated at the
		// /login endpoint and the default redirection in case of login error would be
		// /login
		// here the endpoint is /user.login and this page is accessable to all
		// to invalidate the session upon logout
		// rememberMe() is a token based concept that retains the scope of the current user throughout the session 
		http.authorizeRequests()
				.antMatchers("/user/login**", "/js/**", "/css/**", "/img/**", "/home**", "/user/register/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.usernameParameter("email").defaultSuccessUrl("/home").failureUrl("/login?error").permitAll().and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling().accessDeniedPage("/403").and().rememberMe().and();
		;
	}
}
