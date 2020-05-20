package race;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import object.Car;
import object.Podium;
import object.Team;

public abstract class Race {
	protected static final int FIRST_POSITION = 0;
    protected static final int SECOND_POSITION = 1;
    protected static final int THIRD_POSITION = 2;

    protected  String raceName;
    protected int length;
    protected List<Team> teams;
    protected List<Car> cars;
    protected Podium podium;
    protected int currentLap;

    protected abstract List<Car> lap();

    public Race (String raceName, int length) {
        this.currentLap = 1;
        this.raceName = raceName;
        this.length = length;
        this.teams = new ArrayList<Team>();
        this.cars = new ArrayList<Car>();
    }

    public String toString() {
        return "RaceName : " + raceName + "\n\t" +
                "Race Length: " + length + "\n\t" +
                "Teams: " + teams.toString() + "\n\t" +
                "Cars : " + cars.toString() + "\n\t";
    }

    public Race (String raceName, int length, List<Car> cars) {
        this(raceName,length);
        this.cars.addAll(cars);
    }

    public void setTeams(List<Team> teams) {
        this.teams.addAll(teams);
    }

    public void setCars(List<Car> cars) {
        this.cars.addAll(cars);
    }


    private Comparator compareByOdometer = new Comparator<Car>() {
        public int compare(Car car1, Car car2) {
            return (car1.getOdometer() - car2.getOdometer()) * -1;
        }
    };

    protected void sortCars() {
        Collections.sort(cars,compareByOdometer);
    }

    protected void setCarsDistance() {
        for(Car car : cars) {
            car.drive();
        }
    }

    public abstract Podium runRace();

    public int getTotalLaps () {
        return this.currentLap;
    }

}
