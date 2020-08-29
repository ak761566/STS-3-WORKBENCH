package com.myspringApp.ithakaDao;

import java.util.List;

import com.myspringApp.ithakaModel.Inventory;
import com.myspringApp.ithakaModel.Transaction;

public interface transcationDao {
	
	public List<Transaction> listActivityByID(String setupNo);
	public List<Transaction> listActivityByUid(String UID);
	public List<Transaction> listRevisedActivityByUid(String UID);
	
	public String searchStream(String setupNo);
	
	public List<Transaction> getTaskFromDB(Transaction transcation);
	public void loggTaskInDB(Transaction transcation);
	public void loggRevisedTaskInDB(Transaction transaction);
	
	public void updateActivityStatus(String Status, String SetupNo, String activity);
	public void updateActivityStatusComment(String Status, String SetupNo, String activity, String Comment, String userId);
	
	public List<Transaction> runningActivity(String setupNo);
	
	public List<Transaction> finishedActivity(String userId);
	
	public List<Transaction> finishedRevisedActivity(String userId);
	
	public int calculateElapsedDays(String setupNo);
	
	public List<Transaction> adminReport();
	
	public List<Transaction> adminTextSearchReport(String text);
	

}
