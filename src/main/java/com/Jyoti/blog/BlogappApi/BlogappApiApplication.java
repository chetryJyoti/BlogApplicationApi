package com.Jyoti.blog.BlogappApi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
public class BlogappApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
//	@Value("${app.message}")
//	String message;
	public static void main(String[] args) {
		SpringApplication.run(BlogappApiApplication.class, args);
		System.out.println("------------------------------Server started------------------------------");
	}

//	@GetMapping("/hello")
//	public String helloMsg(){
//		return message;
//	}

	@Bean
	public ModelMapper modalMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(this.passwordEncoder.encode("jtorids"));
	}
}
