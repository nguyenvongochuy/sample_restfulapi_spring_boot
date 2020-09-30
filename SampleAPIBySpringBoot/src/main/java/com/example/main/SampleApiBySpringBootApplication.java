package com.example.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.JWTAuthorizationFilter;


@SpringBootApplication
//@ComponentScan( basePackageClasses = PostSignUpResponse.class)
@EnableAutoConfiguration
@ComponentScan(basePackages="com.example")
public class SampleApiBySpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApiBySpringBootApplication.class, args);
	}
	
	@Bean
	public MessageSourceAccessor createMessageSourceAccessor() {
	    return new MessageSourceAccessor(messageSource());
	}
	
	@Bean
	public MessageSource messageSource() { 
	     ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	     messageSource.setBasename("resources/messages.properties");
	     return messageSource;
	}
	
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/signin").permitAll()
				.anyRequest().authenticated();
		}
	}

}
