package br.com.ntconsult.DevBackNTSicredi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "br.com.ntconsult.DevBackNTSicredi")
@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class DevBackNtSicrediApplication {

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Brazil/East"));
		System.out.println("No construtor");
		System.out.println(new Date());
	}

	public static void main(String[] args) {
		SpringApplication.run(DevBackNtSicrediApplication.class, args);
	}

}
