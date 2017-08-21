package com.xiangxun.atms.icabinet.statusserver.protocal.param;

/**
 * 机柜的系统参数（主要是正常区间参数）
 * 
 * @author szf
 *
 */
public class CabinetRangeParam {
	private float temperatureMin, temperatureMax, humidityMin, humidityMax;

	public CabinetRangeParam(String temperatureMin, String temperatureMax, String humidityMin, String humidityMax) {
		this.temperatureMin = Float.parseFloat(temperatureMin);
		this.temperatureMax = Float.parseFloat(temperatureMax);
		this.humidityMin = Float.parseFloat(humidityMin);
		this.humidityMax = Float.parseFloat(humidityMax);
	}

	public float getTemperatureMin() {
		return temperatureMin;
	}

	public float getTemperatureMax() {
		return temperatureMax;
	}

	public float getHumidityMin() {
		return humidityMin;
	}

	public float getHumidityMax() {
		return humidityMax;
	}

	@Override
	public String toString() {
		return String.format("temperatureMin:%f,temperatureMax:%f,humidityMin:%f,humidityMax:%f", temperatureMin, temperatureMax, humidityMin, humidityMax);
	}

	public boolean isValidTemperature(float temperature) {
		return temperature >= temperatureMin && temperature <= temperatureMax;
	}

	public boolean isValidHumidity(float humidity) {
		return humidity >= humidityMin && humidity <= humidityMax;
	}
}
