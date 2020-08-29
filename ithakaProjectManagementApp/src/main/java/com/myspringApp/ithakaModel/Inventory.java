package com.myspringApp.ithakaModel;

import java.util.Date;

public class Inventory {

	private String setupNo;
	private String streamName;
	private String streamType;
	private String complexity;
	
	private int batchCount;
	private Date kickOffDate;
	private Date dueDate;
	private Date deliveryDate;
	private String streamStatus;
	private int iterations;
	private int elapsedDays;
	private int monthlyElapsedDays;
	
	private String userId;
	
	public String getSetupNo() {
		return setupNo;
	}
	public void setSetupNo(String setupNo) {
		this.setupNo = setupNo;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public int getBatchCount() {
		return batchCount;
	}
	public void setBatchCount(int batchCount) {
		this.batchCount = batchCount;
	}
	public Date getKickOffDate() {
		return kickOffDate;
	}
	public void setKickOffDate(Date kickOffDate) {
		this.kickOffDate = kickOffDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getStreamStatus() {
		return streamStatus;
	}
	public void setStreamStatus(String streamStatus) {
		this.streamStatus = streamStatus;
	}
	public int getIterations() {
		return iterations;
	}
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public String getComplexity() {
		return complexity;
	}
	public void setComplexity(String complexity) {
		this.complexity = complexity;
	}
	public int getElapsedDays() {
		return elapsedDays;
	}
	public void setElapsedDays(int elapsedDays) {
		this.elapsedDays = elapsedDays;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getMonthlyElapsedDays() {
		return monthlyElapsedDays;
	}
	public void setMonthlyElapsedDays(int monthlyElapsedDays) {
		this.monthlyElapsedDays = monthlyElapsedDays;
	}
	
	
	
}
