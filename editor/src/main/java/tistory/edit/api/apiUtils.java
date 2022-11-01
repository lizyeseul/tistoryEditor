package tistory.edit.api;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

@Component
public class apiUtils {
	public String makeUrlParam(Map<String, String> param) {
		String result = "";
		for(Entry<String, String> entry : param.entrySet()) {
			result += "&"+entry.getKey()
					+ "="+String.valueOf(entry.getValue());
		}
		return result;
	}
	
	public Boolean validParam(List<String> paramList, Map<String, String> param) {
		for(String p : paramList) {
			if("".equals(MapUtils.getString(param, p, ""))) {
				return false;
			}
		}
		return true;
	}
}
