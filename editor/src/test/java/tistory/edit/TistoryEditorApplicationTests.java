package tistory.edit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TistoryEditorApplicationTests {
	@Value("${tistory.token}")
	private String token;
	@Value("${tistory.url}")
	private String baseUrl;

	@Test
	void contextLoads() {
		String serviceUrl = this.baseUrl+"blog"+"?"
						+ "access_token="+this.token;
		System.out.println(serviceUrl);
	}

}
