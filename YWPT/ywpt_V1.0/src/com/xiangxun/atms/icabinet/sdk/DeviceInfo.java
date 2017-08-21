package com.xiangxun.atms.icabinet.sdk;

public class DeviceInfo {
	public static class AcSocket {
		public String id;
		public String name;
		public String samplingTime;
		public String enabled;
		public String currentRegionMin;
		public String currentRegionMax;
		public String currentNormalOrExcept;
		public String currentID;
		public String voltageRegionMin;
		public String voltageRegionMax;
		public String voltageNormalOrExcept;
		public String voltageID;
	}

	public static class AcTerminal {
		public String id;
		public String name;
		public String samplingTime;
		public String enabled;
		public String isPowerOn;
		public String normalOrExcept;
	}
	
	public static class Sensor
	{
		public String id;
		public String enabled;
		public String name;
		public String samplingTime;
		public String type;
		public String maxValue;
		public String minValue;
		public String sensitivity;
		public String unit;
	}
}
