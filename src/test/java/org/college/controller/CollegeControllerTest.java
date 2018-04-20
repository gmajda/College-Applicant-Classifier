package org.college.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.college.model.Applicant;
import org.college.service.CollegeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CollegeController.class, secure = false)
public class CollegeControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CollegeService collegeService;
	
	String applicantJson = "{\"firstName\":\"Richard\",\"lastName\":\"Mathiew\",\"age\":19}";
	
	List<Applicant> applicants;
	 	
	@Before
	public void init(){
		applicants = new ArrayList<Applicant>();
		Applicant firstApplicant = new Applicant();
		firstApplicant.setFirstName("Richard");
		firstApplicant.setLastName("Mathiew");
		firstApplicant.setAge(19);
		
		Applicant secondApplicant = new Applicant();
		secondApplicant.setFirstName("Robert");
		secondApplicant.setLastName("Franck");
		secondApplicant.setAge(23);
		
		applicants.add(firstApplicant);
		applicants.add(secondApplicant);
	}

	@Test
	public void registerApplicants() throws Exception{
		Applicant applicant = new Applicant("1", "Albert", "Jose", 18, 4.5, 5, 1921, 0, false );
		
		Mockito.when(collegeService.addApplicant(Mockito.any(Applicant.class))).thenReturn(applicant);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/college/add/applicant")
				.accept(MediaType.APPLICATION_JSON).content(applicantJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/college/add/applicant/1", response
				.getHeader(HttpHeaders.LOCATION));
		
		
	}
	
	@Test
	public void retrieveAllApplicants() throws Exception{
		Mockito.when(collegeService.retrieveAllApplicant()).thenReturn(applicants);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/college/all/applicants").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "[{\"id\":null,\"firstName\":\"Richard\",\"lastName\":\"Mathiew\",\"age\":19,\"gpa\":0.0,\"scale\":0.0,\"sat\":0.0,\"act\":0.0,\"felonies\":false},{\"id\":null,\"firstName\":\"Robert\",\"lastName\":\"Franck\",\"age\":23,\"gpa\":0.0,\"scale\":0.0,\"sat\":0.0,\"act\":0.0,\"felonies\":false}]";
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
