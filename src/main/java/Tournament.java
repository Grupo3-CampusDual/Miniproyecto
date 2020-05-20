import java.util.ArrayList;
import java.util.List;

import object.Car;
import object.Podium;
import object.Team;
import race.Race;

public class Tournament {
    private String name;
    private List<Team> teams;
    private List<Race> races;
    private List<Car> cars;
    private List<CarScore> carScores;

    public Tournament(String name, List<Team> teams, List<Race> races) {
        this.name = name;
        this.teams = teams;
        this.races = races;
        this.races = assignRaces(teams);
        this.cars = assignCars(teams);
        this.carScores = new ArrayList<CarScore>();
        this.carScores = resetCarScores(this.cars);
        for (Race race : this.races) {
            race.setCars(cars);
        }
    }

    public String toString() {
        return "Tournament: " + name + "\n" +
                "Teams: " + teams.toString() + "\n" +
                "Races: " + races.toString() + "\n" +
                "Cars: " + cars.toString() + "\n" +
                "CarScores: " + carScores.toString() + "\n" ;
    }

    private List<Race> assignRaces(List<Team> teams) {
        for (Race race : races) {
            race.setTeams(teams);
        }
        return races;
    }

    private List<Car> assignCars(List<Team> teams) {
        if (teams.size() == 1) {
            return teams.get(0).getCars();
        } else {
            return randomlySelectCars(teams);
        }
    }

    private List<Car> randomlySelectCars(List<Team> teams) {
        List <Car> randomlyChosenCars = new ArrayList<Car>();
        for (Team team : teams) {
            randomlyChosenCars.add(getRandomCar(team));
        }
        return randomlyChosenCars;
    }

    private Car getRandomCar(Team team) {
        int teamTotalCars = team.getCars().size();
        int randomlySelectedPosition = new Double(Math.random() * teamTotalCars).intValue();
        return team.getCars().get(randomlySelectedPosition);
    }

    private void setCarScore(Car car, int score) {
        for (CarScore carScore : carScores) {
            if (carScore.getCar() == car) {
                carScore.setScore(carScore.getScore() + score);
            }
        }
    }

    public Podium celebrateTournament() {
        for (Race race : races) {
            Podium racePodium = race.runRace();
            List<Car> podiumCars = racePodium.getCars();
            for (Car car : podiumCars) {
                setCarScore(car, podiumCars.size() - podiumCars.indexOf(car));
            }
        }
        Podium podium = new Podium(null, null, null);
        return podium;
    }

    public List<CarScore> resetCarScores(List<Car> cars) {
        for(Car car : cars) {
            CarScore carScore = new CarScore();
            carScore.setCar(car);
            carScore.setScore(0);
            this.carScores.add(carScore);
        }
        return this.carScores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> garages) {
        this.teams = garages;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }

    public List<CarScore> getCarScores() {
        return carScores;
    }

    public void setCarScores(List<CarScore> carScores) {
        this.carScores = carScores;
    }

}