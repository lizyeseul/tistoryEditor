package tistory.edit;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.junit.jupiter.api.Test;

public class simpleTest {
	@Test
	public void simpleTest() {
		Map<String, String> m = new HashMap<>();
		m.put("a", "1");
		m.put("b", "2");
		m.put("c", "3");
		System.out.println(MapUtils.getIntValue(m, "a", 0));
		System.out.println(MapUtils.getInteger(m, "b", 0));
		System.out.println(MapUtils.getIntValue(m, "a"));
		System.out.println(MapUtils.getInteger(m, "b"));
	}
}
