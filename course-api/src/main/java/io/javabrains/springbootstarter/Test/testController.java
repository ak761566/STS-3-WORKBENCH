package io.javabrains.springbootstarter.Test;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.springbootstarter.topic.topic;

@RestController
public class testController {

@RequestMapping("/test")
public String tesRestMethod() {
	
	return "test";
}
	
}
