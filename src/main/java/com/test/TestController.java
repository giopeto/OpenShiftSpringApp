package com.test;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
	@RequestMapping(
			method = RequestMethod.GET,
			headers="Accept=application/json",
			produces="application/json"
	)
	public List get() {
		ArrayList a = new ArrayList();
		a.add("Hello World");
		return a;
	}
}