package com.harpy.hag.vm;

import java.util.ArrayList;

import com.harpy.hag.utils.JsonUtil;


public class VMHidden extends VM {

	public static final String delimiter = "_____";			
	
	public String hiddenJson;

	public VMHidden() {
		super();
	}
	public VMHidden(String pageTitle) {
		super(pageTitle);
	}
	
	public String getHiddenJson() {
		return hiddenJson;
	}
	public void setHiddenJson(String hiddenJson) {
		this.hiddenJson = hiddenJson;
	}
	
	public static String generateHiddenJson(ArrayList<Object> hiddenObjects) {
		String hiddenJson;
		
		if (hiddenObjects == null) {
			hiddenJson = "";
		} else if (hiddenObjects.size() == 0) {
			hiddenJson = "";
		} else if (hiddenObjects.size() == 1) {
			hiddenJson = JsonUtil.objToJson(hiddenObjects.get(0));
		} else {
			hiddenJson = JsonUtil.objToJson(hiddenObjects.get(0));
			for (int i = 1; i < hiddenObjects.size(); i++) {
				hiddenJson = hiddenJson + delimiter + JsonUtil.objToJson(hiddenObjects.get(i));
			}
		}		
		return hiddenJson;
	}

	
}
