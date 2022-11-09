package tistory.edit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class AppConst {
	@Value("${api.url}")
	private String baseUrl;

	@Value("${token.client_id}")
	private String clientId;

	@Value("${token.secret_key}")
	private String secretKey;

	@Value("${token.redirect_uri}")
	private String redirectUri;

	@Value("${home.url}")
	private String homeUrl;
}
