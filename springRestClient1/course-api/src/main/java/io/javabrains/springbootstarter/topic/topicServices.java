package io.javabrains.springbootstarter.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class topicServices {
	
	private List<topic> Topics = Arrays.asList(
			new topic("spring", "Spring Framework", "Spring Framework Description"),
			new topic("java", "Core Java", "Core Java Description"),
			new topic("javaScript", "Java Script", "Java Script Description")
			);
	
	public List<topic> getAllTopics(){
		return Topics;
	}
	
	public topic getTopic(String id) {
		//Lambda Expression
		
		return Topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		
	}

}
