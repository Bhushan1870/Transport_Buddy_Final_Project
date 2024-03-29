
package com.bezkoder.spring.hibernate.onetomany.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bezkoder.spring.hibernate.onetomany.services.CustomUserDetailsService;

@Configuration
@EnableGlobalMethodSecurity(
    // securedEnabled = true,
    // jsr250Enabled = true,
    prePostEnabled = true)
public class MySecurityConfig {


	  @Autowired
	  CustomUserDetailsService userDetailsService;

	  @Autowired
	  private JwtAuthenticationEntryPoint unauthorizedHandler;

	  @Bean
	  public JwtAuthenticationFilter authenticationJwtTokenFilter() {
	    return new JwtAuthenticationFilter();
	  }

	//  @Override
	//  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//	    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	//  }
	  
//	  @Bean
//	  public DaoAuthenticationProvider authenticationProvider() {
//	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	       
//	      authProvider.setUserDetailsService(userDetailsService);
//	      authProvider.setPasswordEncoder( /*passwordEncoder() */);
//	   
//	      return authProvider;
//	  }

	//  @Bean
	//  @Override
	//  public AuthenticationManager authenticationManagerBean() throws Exception {
//	    return super.authenticationManagerBean();
	//  }
	  
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	  
	  @Bean
	    public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }

//	  @Bean
//	  public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	  }

	//  @Override
	//  protected void configure(HttpSecurity http) throws Exception {
//	    http.cors().and().csrf().disable()
//	      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//	      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//	      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//	      .antMatchers("/api/test/**").permitAll()
//	      .anyRequest().authenticated();
	//
//	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	//  }
	  
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
	        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .authorizeRequests().antMatchers("/api/auth/**").permitAll()
	        .antMatchers("/api/test/**").permitAll()
	        .antMatchers("/api/vehicles").permitAll()
	        .antMatchers("/api/**").permitAll()
	        .anyRequest().authenticated();
	    
//	    http.authenticationProvider(authenticationProvider());

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }

}
