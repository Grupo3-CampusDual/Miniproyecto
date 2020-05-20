package race;

import java.util.List;

import object.Car;
import object.Podium;

public class LapKnockOutRace extends Race {

	private int lapsAmount;
	
	public LapKnockOutRace(String raceName, int length) {
        super(raceName, length);
        this.lapsAmount = cars.size();
    }

    public LapKnockOutRace(String raceName, int length, List<Car> cars) {
        this(raceName,length);
        this.cars = cars;
    }

    protected List<Car> lap() {
        setCarsDistance();
        sortCars();
        deleteLastCar();
        System.out.println(cars.toString());
        return cars;
    }

    private void deleteLastCar() {
        Car lastCar = cars.get(cars.size()-1);
        int lastCarCurrentLap = lastCar.getOdometer() / this.length;
        if (lastCarCurrentLap == this.currentLap && lastCarCurrentLap > 0) {
            cars.remove(cars.size()-1);
            this.currentLap ++;
        }
    }

    public Podium runRace() {
        while (cars.size() > 3) {
            lap();
        }
        this.podium = new Podium(cars.get(FIRST_POSITION), cars.get(SECOND_POSITION), cars.get(THIRD_POSITION));
        return this.podium;
    }
	
}
