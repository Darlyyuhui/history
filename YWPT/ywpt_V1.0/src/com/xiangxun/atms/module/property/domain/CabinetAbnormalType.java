package com.xiangxun.atms.module.property.domain;

import org.apache.commons.lang3.StringUtils;

public enum CabinetAbnormalType {
	UnKnown(0, "anknown"), //
	ACSocket(1, "ACchazuo_Status"), //
	ACterminal(2, "ACterminal_Status"), //
	humidity(4, "humidity_Status"), //
	shock(8, "shock_Status"), //
	smoke(16, "smoke_Status"), //
	temperature(32, "temperature"), //
	power(64, "power_Status"), //
	network(128, "network_Status");

	private int id;
	private String name;

	private CabinetAbnormalType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static CabinetAbnormalType getByName(String name) {
		if (StringUtils.equalsIgnoreCase(name, "ACchazuo_Status"))
			return ACSocket;
		if (StringUtils.equalsIgnoreCase(name, "ACterminal_Status"))
			return ACterminal;
		if (StringUtils.equalsIgnoreCase(name, "humidity_Status"))
			return humidity;
		if (StringUtils.equalsIgnoreCase(name, "shock_Status"))
			return shock;
		if (StringUtils.equalsIgnoreCase(name, "smoke_Status"))
			return smoke;
		if (StringUtils.equalsIgnoreCase(name, "temperature"))
			return temperature;
		if (StringUtils.equalsIgnoreCase(name, "cabinetpower_Status"))
			return power;
		if (StringUtils.equalsIgnoreCase(name, "cabinetnet_Status"))
			return network;
		return UnKnown;
	}
}
