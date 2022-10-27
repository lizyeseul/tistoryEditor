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
//	private Boolean token_valid = false;
//	private String access_token;
	private Boolean token_valid = true;
	private String access_token = "c8b4389e06653bfb631f89189683c128_7e013a3472851e068233a2ecd1dd6e5e";
	private String start_time;
	private String end_time;
	
	public Token() {}
}
