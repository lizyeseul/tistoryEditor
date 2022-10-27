package tistory.edit.token;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("unused")
@Component
public class Token {
	private Boolean token_valid = false;
	private String access_token;
	private String start_time;
	private String end_time;
	
	public Token() {}
}
