package br.com.ntconsult.DevBackNTSicredi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.ntconsult.DevBackNTSicredi")
@EnableSwagger2
public class DevBackNtSicrediApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevBackNtSicrediApplication.class, args);
	}

}
