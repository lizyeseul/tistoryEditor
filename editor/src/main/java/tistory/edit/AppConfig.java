package tistory.edit;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	public AppConst appConst() {
		return new AppConst();
	}
}
