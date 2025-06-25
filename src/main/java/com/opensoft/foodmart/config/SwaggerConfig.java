package com.opensoft.foodmart.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {
	@Value("${spring.profiles.active}")
	String profileActive;
	
	private final PasswordEncoder passwordEncoder;


	public SwaggerConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	OpenAPI openApiInformation() throws Exception {
		Server localServer = new Server().url("http://localhost:8080/opensoft-food")
				.description("Localhost Server URL");
		


		Contact contact = new Contact().email("info.opensoft@gmail.com").name("OpenSoft Inc");
		Info info = new Info().contact(contact).description("OpenSoft Inc. Food Mart API(s)")
				.title("OpenSoft Inc").version("2.0.0")
				.license(new License().name("Apache 2.0").url("http://springdoc.org"));
		// Define custom header here
		Components components = new Components();
		components.addHeaders("X-Custom-Header",
				new Header().description("Description of custom header").schema(new StringSchema()));
		components.addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());
		
        
		Object example_token = "xy......bearertoken";
		var openApi = new OpenAPI();
		openApi.addSecurityItem(new SecurityRequirement().addList("Bearer Authentication")).components(components);
		openApi.info(info).addServersItem(localServer);
		

	

		

		return openApi;
	}
	
	 @Bean
	     OpenApiCustomizer globalResponsesCustomizer() {
	        return openApi -> openApi.getPaths().values().forEach(pathItem ->
	            pathItem.readOperations().forEach(operation -> {
	                ApiResponses responses = operation.getResponses();

	                responses.addApiResponse("100", new ApiResponse().description("Continue"));
	                responses.addApiResponse("101", new ApiResponse().description("Switching Protocols"));
	                responses.addApiResponse("102", new ApiResponse().description("Processing"));

	                responses.addApiResponse("200", new ApiResponse().description("OK - The request has succeeded"));
	                responses.addApiResponse("201", new ApiResponse().description("Created - New resource created"));
	                responses.addApiResponse("202", new ApiResponse().description("Accepted - The request has been accepted for processing"));
	                responses.addApiResponse("203", new ApiResponse().description("Non-Authoritative Information"));
	                responses.addApiResponse("204", new ApiResponse().description("No Content - The server successfully processed the request, no content returned"));
	                responses.addApiResponse("205", new ApiResponse().description("Reset Content"));
	                responses.addApiResponse("206", new ApiResponse().description("Partial Content"));

	                responses.addApiResponse("300", new ApiResponse().description("Multiple Choices"));
	                responses.addApiResponse("301", new ApiResponse().description("Moved Permanently"));
	                responses.addApiResponse("302", new ApiResponse().description("Found"));
	                responses.addApiResponse("303", new ApiResponse().description("See Other"));
	                responses.addApiResponse("304", new ApiResponse().description("Not Modified"));
	                responses.addApiResponse("307", new ApiResponse().description("Temporary Redirect"));
	                responses.addApiResponse("308", new ApiResponse().description("Permanent Redirect"));

	                responses.addApiResponse("400", new ApiResponse().description("Bad Request - Invalid request syntax or parameters"));
	                responses.addApiResponse("401", new ApiResponse().description("Unauthorized - Authentication is required"));
	                responses.addApiResponse("402", new ApiResponse().description("Payment Required"));
	                responses.addApiResponse("403", new ApiResponse().description("Forbidden - Insufficient permissions"));
	                responses.addApiResponse("404", new ApiResponse().description("Not Found - Resource not found"));
	                responses.addApiResponse("405", new ApiResponse().description("Method Not Allowed"));
	                responses.addApiResponse("406", new ApiResponse().description("Not Acceptable"));
	                responses.addApiResponse("407", new ApiResponse().description("Proxy Authentication Required"));
	                responses.addApiResponse("408", new ApiResponse().description("Request Timeout"));
	                responses.addApiResponse("409", new ApiResponse().description("Conflict - Duplicate or invalid state"));
	                responses.addApiResponse("410", new ApiResponse().description("Gone - The resource is no longer available"));
	                responses.addApiResponse("411", new ApiResponse().description("Length Required"));
	                responses.addApiResponse("412", new ApiResponse().description("Precondition Failed"));
	                responses.addApiResponse("413", new ApiResponse().description("Payload Too Large"));
	                responses.addApiResponse("414", new ApiResponse().description("URI Too Long"));
	                responses.addApiResponse("415", new ApiResponse().description("Unsupported Media Type"));
	                responses.addApiResponse("416", new ApiResponse().description("Range Not Satisfiable"));
	                responses.addApiResponse("417", new ApiResponse().description("Expectation Failed"));
	                responses.addApiResponse("418", new ApiResponse().description("I'm a teapot"));
	                responses.addApiResponse("422", new ApiResponse().description("Unprocessable Entity"));
	                responses.addApiResponse("429", new ApiResponse().description("Too Many Requests"));

	                responses.addApiResponse("500", new ApiResponse().description("Internal Server Error - Unexpected condition"));
	                responses.addApiResponse("501", new ApiResponse().description("Not Implemented"));
	                responses.addApiResponse("502", new ApiResponse().description("Bad Gateway"));
	                responses.addApiResponse("503", new ApiResponse().description("Service Unavailable"));
	                responses.addApiResponse("504", new ApiResponse().description("Gateway Timeout"));
	                responses.addApiResponse("505", new ApiResponse().description("HTTP Version Not Supported"));
	            })
	        );
	    }
	
	private SecurityScheme createAPIKeyScheme() {
		return new SecurityScheme().type(SecurityScheme.Type.HTTP).bearerFormat("JWT").scheme("bearer");
	}
	
}