package tistory.edit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.MapUtils;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tistory.edit.api.connectionApi;

@SpringBootTest
class TistoryEditorApplicationTests {
//	@Value("${tistory.token}")
//	private String token;
//	@Value("${tistory.url}")
//	private String baseUrl;
//
//	@Test
//	void contextLoads() {
//		String serviceUrl = this.baseUrl+"blog"+"?"
//						+ "access_token="+this.token;
//		System.out.println(serviceUrl);
//	}

	
	@Autowired
	private connectionApi connect;
	
	@Test
	public void simpleTest() {
		JSONObject responseBody = connect.getContent("GET", "post/read", "&output=json&blogName=hellorecord98&postId=60");
		JSONObject tistory = (JSONObject) responseBody.get("tistory");
		JSONObject item = (JSONObject) tistory.get("item");
		
		String content = MapUtils.getString(item, "content");
		String before = "	<div\n"
				+ "		style=\"color: #ecaa00; flex: 0 0 auto; height: 24px; margin-right: 16px; width: 24px; align-self: center;\">\n"
				+ "		<script>\n"
				+ "			document.write(\"<i class=\\\"fa-solid fa-circle-exclamation\\\"></i>\")\n"
				+ "		</script>\n"
				+ "	</div>";
		String after = "<div style=\"color: #ffffff; flex: 0 0 auto; height: 24px; margin-right: 16px; width: 24px; align-self: center; background: #ecaa00; border-radius: 12px; line-height: 1.5;\">!</div>";
//		
//		String content = "<script>\n"
//				+ "			document.write(\"<i class=\\\"fa-solid fa-circle-exclamation\\\"></i>\")\n"
//				+ "		</script>";
//		;
//		String result = content.replaceAll("document\\.write\\(\"", "");
		String result = content;
		while(result.indexOf(before) > 0) {
			result = result.replace(before, after);
		}
		System.out.println("\n\n"+result);
	}
}
