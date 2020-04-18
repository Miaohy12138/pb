package net.miaohy.pb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.request.RequestContextListener;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * 应用启动入口 
 * @author miaohy
 */
@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class PBApplication {

	public static void main(String[] args) {

		log.info("pb server is starting.");
		ConfigurableEnvironment env = SpringApplication.run(PBApplication.class, args).getEnvironment();
		log.info(
				"\n----------------------------------------------------------\n\t"
						+ "Application '{}' is running! Access URLs:\n\t"
						+ "Local: \t\thttp://localhost:{}\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), env.getProperty("server.port"));
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Bean
	public RequestContextListener requestContextListener(){
		return new RequestContextListener();
	}

}
