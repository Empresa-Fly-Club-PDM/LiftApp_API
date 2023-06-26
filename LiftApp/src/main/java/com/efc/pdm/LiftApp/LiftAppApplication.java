package com.efc.pdm.LiftApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@SpringBootApplication
public class LiftAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiftAppApplication.class, args);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		List<String> Origins=new ArrayList<String>();
		Origins.add("*");
		List<String> Methods=new ArrayList<String>();
		Methods.add("*");
		List<String> Headers=new ArrayList<String>();
		Headers.add("*");
		List<String> Exposed=new ArrayList<String>();
		Exposed.add("*");

		configuration.setAllowedOrigins(Origins);
		configuration.setAllowedMethods(Methods);
		configuration.setAllowedHeaders(Headers);
		configuration.setExposedHeaders(Exposed);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
