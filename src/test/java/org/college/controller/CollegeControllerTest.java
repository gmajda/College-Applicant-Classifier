package org.college.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.college.enums.Message;
import org.college.enums.State;
import org.college.model.Applicant;
import org.college.model.Evaluation;
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
	Applicant applicantToBeEvaluated;
	Evaluation evaluation;
	 	
	@Before
	public void init(){
		applicants = new ArrayList<Applicant>();

		Applicant firstApplicant = new Applicant();
		firstApplicant.setFirstName("Richard");
		firstApplicant.setLastName("Mathiew");
		firstApplicant.setAge(19);
		firstApplicant.setState(State.CALIFORNIA);
		
		Applicant secondApplicant = new Applicant();
		secondApplicant.setFirstName("Robert");
		secondApplicant.setLastName("Franck");
		secondApplicant.setAge(23);
		secondApplicant.setState(State.CALIFORNIA);
		
		applicants.add(firstApplicant);
		applicants.add(secondApplicant);
	}

	@Test
	public void RegisterApplicants() throws Exception{
		Applicant applicant = new Applicant("1", "Albert", "Jose", 18,State.CALIFORNIA, 4.5, 5, 1921, 0, false );
		
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
	public void RetrieveAllApplicants() throws Exception{
		Mockito.when(collegeService.retrieveAllApplicant()).thenReturn(applicants);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/college/all/applicants").accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "[{\"id\":null,\"firstName\":\"Richard\",\"lastName\":\"Mathiew\",\"age\":19,\"state\":CALIFORNIA,\"gpa\":0.0,\"scale\":0.0,\"sat\":0.0,\"act\":0.0,\"felonies\":false},{\"id\":null,\"firstName\":\"Robert\",\"lastName\":\"Franck\",\"age\":23,\"state\": CALIFORNIA,\"gpa\":0.0,\"scale\":0.0,\"sat\":0.0,\"act\":0.0,\"felonies\":false}]";
		
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void Evaluate_Instant_Reject_Applicant() throws Exception{
		evaluation = new Evaluation(Message.INSTANT_REJECT, "Applicant should be from CALIFORNIA state");
		applicantToBeEvaluated = new Applicant();
		applicantToBeEvaluated.setId("1");
		applicantToBeEvaluated.setFirstName("Mathilde");
		applicantToBeEvaluated.setLastName("Charlez");
		applicantToBeEvaluated.setAge(21);
		applicantToBeEvaluated.setState(State.ALABAMA);
		applicantToBeEvaluated.setGpa(4.5);
		applicantToBeEvaluated.setScale(5);
		applicantToBeEvaluated.setSat(1921);
		Mockito.when(collegeService.evaluateApplicant(applicantToBeEvaluated)).thenReturn(evaluation);
		Mockito.when(collegeService.findApplicantById(applicantToBeEvaluated.getId())).thenReturn(applicantToBeEvaluated);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/college/evaluate/1");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(evaluation.toString(),response
				.getContentAsString());
	}
}
