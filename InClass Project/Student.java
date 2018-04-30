public class Student extends Person{
	private double gpa;
	private int studentLoans; 

	public Student(double gpa, int studentLoans, String name, int account, int legs){
		super(name, account, legs);
		this.gpa = gpa;
		this.studentLoans = studentLoans;
	}

	public void getPaid(int amount){
		studentLoans -= .1*amount;
		if(studentLoans < 0){
			studentLoans = 0;
		}
		accountBalance += .9*amount;

	}

	public String toString(){
		return "I am an Animal object with " + numlegs + " legs and a Person object whose name is " + name + "and a Student object with a " + gpa +" gpa and " + studentLoans + " dollars of students loans "
	}
}