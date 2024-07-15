package pai.dev.app.resume_portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ResumePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumePortalApplication.class, args);
	}

}
