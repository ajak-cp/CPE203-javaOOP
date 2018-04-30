public class Animal{
	protected int numLegs;
	protected Animal(int legs) {
		this.numbers = legs;
	}

	public String toString(){
		return "I am an Animal Object with " + numLegs + " legs";
	}

	public int getLegs(){return legs;}


	public boolean equals(Object other){
		if(this == other){return True;}
		return other != null
			&& other.getClass() == getClass()
			&& this.legs == other.getLegs()
			&& super.equals(other);
	}
}