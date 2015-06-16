package me.eduproard.MCItaliaServerInfo.Networking;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class JSONReader {
	private static String urlAPI = "https://api.minecraft-italia.it/server-info/";
	
	public static Object getPosition(String serverName) throws ParseException {
		JSONObject jsonObject;
		String strJson;
		URL u;
		try {
			u = new URL(urlAPI + serverName);
			strJson = IOUtils.toString(u);
			jsonObject = (JSONObject)JSONValue.parseWithException(strJson);
		} catch (IOException e) {
			return null;
		}
		return jsonObject.get("position");
	}
	public static Object getVotes(String serverName) throws ParseException {
		JSONObject jsonObject;
		String strJson;
		URL u;
		try {
			u = new URL(urlAPI + serverName);
			strJson = IOUtils.toString(u);
			jsonObject = (JSONObject)JSONValue.parseWithException(strJson);
		} catch (IOException e) {
			return null;
		}
		return jsonObject.get("votes");
	}
	public static Object getVotes_Today(String serverName) throws ParseException {
		JSONObject jsonObject;
		String strJson;
		URL u;
		try {
			u = new URL(urlAPI + serverName);
			strJson = IOUtils.toString(u);
			jsonObject = (JSONObject)JSONValue.parseWithException(strJson);
		} catch (IOException e) {
			return null;
		}
		return jsonObject.get("votes_today");
	}
	public static boolean existServer(String serverName) {
		URL u;
		String strJson;
		try {
			u = new URL(urlAPI + serverName);
			strJson = IOUtils.toString(u);
		} catch (IOException e) {
			return false;
		}
		if(strJson.contains("error")) {
			return false;
		} else {
			return true;
		}
   }
}