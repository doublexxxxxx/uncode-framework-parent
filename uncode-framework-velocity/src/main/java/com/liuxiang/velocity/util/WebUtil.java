package com.liuxiang.velocity.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.liuxiang.velocity.filter.ActionFilter;


public class WebUtil {
	
	public static void sendResponseObject(Object object) {
		PrintWriter writer = getResponseWriter();
		JSONObject jo = JSONObject.fromObject(object);
		writer.write(jo.toString());
		writer.close();
	}
	
	public static void sendResponseArray(Object array) {
		PrintWriter writer = getResponseWriter();
		JSONArray ja = JSONArray.fromObject(array);
		writer.write(ja.toString());
		writer.close();
	}
	
	public static void sendMessage(String message) {
		PrintWriter writer = getResponseWriter();
		writer.write(message);
		writer.close();
	}
	private static PrintWriter getResponseWriter() {
		try {
			HttpServletResponse response = (HttpServletResponse) ActionFilter.threadLocalResponse.get();
			response.setContentType("text/plain");
			response.setCharacterEncoding("utf-8");
			return response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
