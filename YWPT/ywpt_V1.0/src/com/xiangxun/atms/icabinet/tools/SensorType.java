package com.xiangxun.atms.icabinet.tools;

public enum SensorType {
	temperature(1, "temperature", "温度传感器"), humidity(2, "humidity", "湿度传感器");

	private String name, title;
	private int id;

	SensorType(int id, String name, String title) {
		this.id = id;
		this.name = name;
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public int getID() {
		return id;
	}

}
