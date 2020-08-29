package com.myspringApp.ithakaModel;

import java.util.Date;

public class Transaction {
	
	private String setupNo;
	private String streamName;
	private String userName;
	private String userId;
	private Date dueDate;
	private String activity;
	private Date activity_start_date;
	private Date activity_end_date;
	private String activity_comment;
	private int activity_elapsed_time;
	private String activity_status;
	
	private String Stream_Type;
	private String revised_stream;
	
	public String getSetupNo() {
		return setupNo;
	}
	public void setSetupNo(String setupNo) {
		this.setupNo = setupNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Date getActivity_start_date() {
		return activity_start_date;
	}
	public void setActivity_start_date(Date activity_start_date) {
		this.activity_start_date = activity_start_date;
	}
	public Date getActivity_end_date() {
		return activity_end_date;
	}
	public void setActivity_end_date(Date activity_end_date) {
		this.activity_end_date = activity_end_date;
	}
	public String getActivity_comment() {
		return activity_comment;
	}
	public void setActivity_comment(String activity_comment) {
		this.activity_comment = activity_comment;
	}
	public int getActivity_elapsed_time() {
		return activity_elapsed_time;
	}
	public void setActivity_elapsed_time(int activity_elapsed_time) {
		this.activity_elapsed_time = activity_elapsed_time;
	}
	public String getActivity_status() {
		return activity_status;
	}
	public void setActivity_status(String activity_status) {
		this.activity_status = activity_status;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public String getStream_Type() {
		return Stream_Type;
	}
	public void setStream_Type(String stream_Type) {
		Stream_Type = stream_Type;
	}
	public String getRevised_stream() {
		return revised_stream;
	}
	public void setRevised_stream(String revised_stream) {
		this.revised_stream = revised_stream;
	}
	
	
	
}
