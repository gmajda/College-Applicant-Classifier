package org.college.controller;

import static org.junit.Assert.assertEquals;

import org.college.model.Applicant;
import org.college.service.CollegeService;
import org.junit.Test;
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
}
