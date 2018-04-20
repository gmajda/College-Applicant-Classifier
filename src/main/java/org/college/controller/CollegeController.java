package org.college.controller;

import java.net.URI;
import java.util.List;

import org.college.model.Applicant;
import org.college.model.Evaluation;
import org.college.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CollegeController {

	@Autowired
	private CollegeService collegeService;

	@PostMapping("/college/add/applicant")
	public ResponseEntity<Void> registerApplicant(@RequestBody Applicant a){
		
		if(a == null)
			return ResponseEntity.noContent().build();
		
		Applicant applicant = collegeService.addApplicant(a);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
						.buildAndExpand(applicant.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/college/all/applicants")
	public List<Applicant> retrieveAllApplicants(){
		return collegeService.retrieveAllApplicant();
	}
	
	@PostMapping("/college/evaluate/{applicantId}")
	public String evaluateApplicant(@PathVariable String applicantId){
		Applicant applicant = collegeService.findApplicantById(applicantId);
		if(applicant == null)
			return null;

		 Evaluation evaluation = collegeService.evaluateApplicant(applicant);
		 return evaluation.toString();
	}
}
