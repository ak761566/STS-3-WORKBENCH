package io.javabrains.springbootstarter.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class topicController {

	/*
	 * GET /topics get all topics 
	 * GET /topics/id gets all topics 
	 * POST /topics Create new topics 
	 * PUT /topics/id Update the topic 
	 * DELETE /topics/id DELETE the topic
	 */
	
	@Autowired
	private topicServices topicservice;
	
	@RequestMapping("/topics")
	public List<topic> testRestMethod(){
		return topicservice.getAllTopics();
		
	}
	/*
	 * public String getAllTopic() { return "All Topics"; }
	 */
	
	@RequestMapping("/topics/{id}")
	public topic getTopic(@PathVariable String id) {
		return topicservice.getTopic(id);
	}
}
