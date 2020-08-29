package com.myspringApp.ithakaDao;

import java.util.List;

import com.myspringApp.ithakaModel.Inventory;

public interface projectDao {

	public void addNewProject(Inventory inventory);
	public Inventory getProjectById(String setupNo);
	public List<Inventory> listProjects();
	public List<Inventory> completedStreams(String userId);
	public List<Inventory> completedStreams();
	public List<Inventory> inCompleteStreams();
	
	public List<Inventory> CurrentMonthCompletedStreams();
	
	
	public Inventory markStreamAsDeliver(String userId, String setupNo, String streamName, int elapsedDays);

	
	
}
