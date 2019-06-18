package com.exam.domain;

import lombok.Data;

@Data
public class PebyGateVO {
	private String gate;
	private int byMaintenance;
	private int byTransport;
}
