package tistory.edit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class editorApp {
	public static void main(String[] args) {
//		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		SpringApplication.run(editorApp.class, args);
	}
}
