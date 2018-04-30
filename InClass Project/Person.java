public class Person extends Animal{
	protected String name;
	protected int accountBalance;
	protected int getAccountBallance(){ return accountBalance;}

	public Person(String name, int accountBalance, int legs){
		super(legs);
		this.name = name;
		this.accountBalance = accountBalance;
	}

	
	public boolean equals(Object other){
		if(this == other){return True;}
		return other != null
			&& other.getClass() == getClass()
			&& numlegs == other.getLegs()
			&& this.accountBalance = other.accountBalance
			&& this.name = other.name;
	}

	public void getPaid(int money){
		this.accountBalance += money;
	}

	public String getName(){
		return name;
	}

	public String getAccountBalance(){
		return accountBalance;
	}

	public String toString(){
		return "I am an Animal Object with " + getLegs() + "legs and a Person object whose name is " + name;
	}


	}



