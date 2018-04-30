public class Professor extends Person{
	private int mortgage;
	private boolean tenure;

	public Professor(int mortgage, boolean tenure, String name, int account, int legs){
		this.mortgage = mortgage;
		this.tenure = tenure;
		super(name, account, legs);
	}

	public void getPaid(int money){
		if(mortgage < 0){
			mortgage = 0;
		}

		int twentyfive = money*.25;
		int therest = money*.75;
		mortgage -= twentyfive;
		AccountBalance += therest; 
	}

	public int getMortgage(){return mortgage;}

	public boolean getTenure(){return tenure;}

	public String toString(){
		return "I am an Animal object with " + numlegs + " legs and a Person object whose name is " + name + " and a Professor object with a " + mortgage + "dollar mortgage and " + tenure;
	}
}