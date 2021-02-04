package br.com.ntconsult.DevBackNTSicredi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.ntconsult.DevBackNTSicredi")
public class DevBackNtSicrediApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevBackNtSicrediApplication.class, args);
	}

}
