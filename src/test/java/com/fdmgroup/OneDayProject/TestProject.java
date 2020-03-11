package com.fdmgroup.OneDayProject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class TestProject {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnHome() throws Exception {
		if (mockMvc == null) 
			System.out.println("mock mvc is null");
			mockMvc.perform(get("/home")).andExpect(status().isOk()).andExpect(view().name("home"));
	}
	
	@Test
	public void shouldReturnHomeWithSlash() throws Exception {
		if (mockMvc == null)
			System.out.println("mock mvc is null");
			mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"));
	}
	
	@Test
	public void shouldReturnItems() throws Exception {
		if (mockMvc == null)
			System.out.println("mock mvc is null");
			mockMvc.perform(get("/items")).andExpect(status().isOk()).andExpect(view().name("items"));
	}
}



// test mapping is working
// test navigating to views
