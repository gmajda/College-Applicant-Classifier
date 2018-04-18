package org.college.model;

public class Applicant {
	
	private String id;
	
	private String firstName;
	
	private String lastName;
	
	private int age;
	
	private double gpa;
	
	private double scale;
	
	private double sat;
	
	private double act;
	
	private boolean felonies;
	
	public Applicant(){
		
	}

	public Applicant(String id,String firstName, String lastName, int age, double gpa, double scale, double sat, double act,
			boolean felonies) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
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
	
	
}
