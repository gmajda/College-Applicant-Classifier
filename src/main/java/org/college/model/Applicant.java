package org.college.model;

import org.college.enums.State;

public class Applicant {

	private String id;

	private String firstName;

	private String lastName;

	private int age;
	
	private State state;

	private double gpa;

	private double scale;

	private double sat;

	private double act;

	private boolean felonies;

	public Applicant() {

	}

	public Applicant(String id, String firstName, String lastName, int age, State state, double gpa, double scale, double sat,
			double act, boolean felonies) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.state = state;
		this.gpa = gpa;
		this.scale = scale;
		this.sat = sat;
		this.act = act;
		this.felonies = felonies;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getGpa() {
		return gpa;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getSat() {
		return sat;
	}

	public void setSat(double sat) {
		this.sat = sat;
	}

	public double getAct() {
		return act;
	}

	public void setAct(double act) {
		this.act = act;
	}

	public boolean isFelonies() {
		return felonies;
	}

	public void setFelonies(boolean felonies) {
		this.felonies = felonies;
	}

	@Override
	public String toString() {
		return "First Name: " + this.firstName + " Last Name: " + this.lastName + " Age: " + this.age + " "
				+ " State: " + this.state + " GPA: " + this.gpa + " SAT: " + this.sat + " ACT: " + this.act + " Felonies "
				+ this.felonies;

	}
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + this.firstName.hashCode();
		hash = 31 * hash + age;
		hash = 31 * hash + this.lastName.hashCode();
        return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applicant applicant = (Applicant) obj;
		if (id == null) {
			if (applicant.id != null)
				return false;
		} else if (!id.equals(applicant.id))
			return false;
		return true;
	}

}
