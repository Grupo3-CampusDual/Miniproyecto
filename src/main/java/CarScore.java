import object.Car;

public class CarScore {
	
	private Car car;
	private int score;
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString() {
		return "Car: " + car.toString() + "->" + score;
	}

}
