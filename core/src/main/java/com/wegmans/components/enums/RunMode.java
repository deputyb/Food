package com.wegmans.components.enums;

public enum RunMode {
	AUTHOR("author"), //
	PUBLISH("publish");
	
	private String id;
	
	private RunMode(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
