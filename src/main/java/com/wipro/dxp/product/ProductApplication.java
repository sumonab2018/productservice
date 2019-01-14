package com.wipro.dxp.product;

import java.io.FileReader;
import java.io.IOException;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	
	
	@Bean
	 public Docket swaggerApi() {
	  return new Docket(DocumentationType.SWAGGER_2)
	   .select()
	   .apis(RequestHandlerSelectors.basePackage("com.wipro.dxp.product.controller"))
	   .paths(PathSelectors.any())
	   .build()
	   .apiInfo(new ApiInfoBuilder().version("1.0").title("Product API").description("Documentation Product API v1.0").build());
	 }
}
