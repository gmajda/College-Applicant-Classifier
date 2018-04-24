package org.college.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.college.enums.Message;
import org.college.enums.State;
import org.college.model.Applicant;
import org.college.model.Evaluation;
import org.springframework.stereotype.Component;

@Component
public class CollegeService {
	private static List<Applicant> applicants = new ArrayList<Applicant>();
	private SecureRandom random = new SecureRandom();

	public List<Applicant> retrieveAllApplicant() {
		return applicants;
	}

	public Applicant addApplicant(Applicant a) {
		String randomId = new BigInteger(130, random).toString(32);
		a.setId(randomId);
		applicants.add(a);
		return a;
	}

	public Evaluation evaluateApplicant(Applicant a){
		String restFirstName = a.getFirstName().substring(0, a.getFirstName().length());
		String restLastName = a.getLastName().substring(0, a.getFirstName().length());
		if(a.isFelonies()){
			return new Evaluation(Message.INSTANT_REJECT, "Applicant has 1 or more felonies");
		}
		if(a.getState().equals(State.CALIFORNIA)){
		if(a.getAge()>17 && a.getAge()<26){
			if(a.getGpa()/a.getScale() >= 0.9) {
				if(a.getSat() > 1920 || a.getAct() > 27){
					if(Character.isUpperCase(a.getFirstName().charAt(0)) && !restFirstName.equals(restFirstName.toUpperCase())
						&& Character.isUpperCase(a.getLastName().charAt(0)) && !restLastName.equals(restLastName.toUpperCase())){
					return new Evaluation(Message.INSTANT_ACCEPT, null);
				}else{
					return new Evaluation(Message.INSTANT_REJECT, "Applicant’s first and/or last name are not in the form of first letter capitalized, the rest lower case.");
				}}
			}else if(a.getGpa()/a.getScale() <=0.7){
				return new Evaluation(Message.INSTANT_REJECT, "Applicant has a GPA below 70%");
			}
		}else if (a.getAge()<0){
			return new Evaluation(Message.INSTANT_REJECT, "Applicant claimed to be a negative age");
		}}else{
			return new Evaluation(Message.INSTANT_REJECT, "Applicant should be from CALIFORNIA state");
		}
		
		
		return new Evaluation(Message.FURTHER_REVIEW, null);
	}

	public Applicant findApplicantById(String applicantId) {
		for (Applicant applicant : applicants) {
			if (applicant.getId().equals(applicantId)) {
				return applicant;
			}
		}
		return null;
	}
}
