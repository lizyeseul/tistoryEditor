package tistory.edit.api;

import org.json.simple.JSONObject;

public interface tistoryApi {
	public void setConnection();
	public Boolean validateParam(JSONObject param);
	public void makeBody(JSONObject param);
	public void getContent();
}
