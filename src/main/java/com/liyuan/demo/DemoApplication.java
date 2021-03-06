package com.liyuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import wechat.servlet.InitServlet;
import wechat.servlet.JSApiServlet;
import wechat.servlet.OAuthServlet;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public ServletRegistrationBean initServlet(){
		return new ServletRegistrationBean(new InitServlet(),"/servlet/InitServlet");
	}

	@Bean
	public ServletRegistrationBean jSApiServlet(){
		return new ServletRegistrationBean(new JSApiServlet(),"/servlet/JSApiServlet");
	}

	@Bean
	public ServletRegistrationBean oAuthServlet(){
		return new ServletRegistrationBean(new OAuthServlet(),"/OAuthServlet");
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
