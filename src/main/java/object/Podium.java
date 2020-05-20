package object;

import java.util.ArrayList;
import java.util.List;

public class Podium {
	private Car firstPlace;
	private Car secondPlace;
	private Car thirdPlace;
	
	public Podium(Car firstPlace, Car secondPlace, Car thirdPlace) {
		this.firstPlace = firstPlace;
		this.secondPlace = secondPlace;
		this.thirdPlace = thirdPlace;
	}
	
	public List<Car> getCars() {
		List<Car> cars = new ArrayList<Car>();
		cars.add(firstPlace);
		cars.add(secondPlace);
		cars.add(thirdPlace);
		return cars;
	}

	public String toString() {
		return "1:- " + this.firstPlace.toString() + "\n"
                + "2.- " + this.secondPlace.toString() + "\n"
                + "3.- " + this.thirdPlace.toString();
	}

}
