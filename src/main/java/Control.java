import java.util.ArrayList;
import java.util.List;

import object.Car;
import object.Team;
import race.LapKnockOutRace;
import race.Race;
import race.StandardRace;

public class Control {
	
	public Race createRace() {
		Team peugeot = new Team();
		List<Car> peugeotCars = new ArrayList<Car>();
		
        peugeotCars.add(new Car("Peugeot", "205"));
        peugeotCars.add(new Car("Peugeot", "306"));
        peugeotCars.add(new Car("Peugeot", "405"));
        peugeotCars.add(new Car("Peugeot", "504"));
        peugeot.setName("Peugeot");
        peugeot.setCars(peugeotCars);

        Team mercedes = new Team();
        mercedes.setName("Mercedes");
        List<Car> mercedesCars = new ArrayList<Car>();
        mercedesCars.add(new Car("Mercedes", "SLK"));
        mercedesCars.add(new Car("Mercedes", "SLR"));
        mercedesCars.add(new Car("Mercedes", "SLS"));
        mercedesCars.add(new Car("Mercedes", "SL 500"));
        mercedes.setCars(mercedesCars);

        Team renault = new Team();
        renault.setName("Renault");
        List<Car> renaultCars = new ArrayList<Car>();
        renaultCars.add(new Car("Renault", "Megane"));
        renaultCars.add(new Car("Renault", "R5"));
        renaultCars.add(new Car("Renault", "4L"));
        renaultCars.add(new Car("Renault", "Clio Sport"));
        renault.setCars(renaultCars);

        Team vw = new Team();
        vw.setName("Volkswagen");
        List<Car> vwCars = new ArrayList<Car>();
        vwCars.add(new Car( "VW", "Polo"));
        vwCars.add(new Car("VW", "Passat"));
        vwCars.add(new Car("VW", "Corrado"));
        vwCars.add(new Car("VW", "Golf R32"));
        vw.setCars(vwCars);

        List<Team> riasTeams = new ArrayList<Team>();
        riasTeams.add(vw);
        List<Team> inlandTeams = new ArrayList<Team>();
        inlandTeams.add(renault);
        inlandTeams.add(mercedes);
        inlandTeams.add(peugeot);
        inlandTeams.add(vw);

        Race knockOutRaceVigo = new LapKnockOutRace("LapKnockOutVigo",20);
        Race knockOutRaceLugo = new LapKnockOutRace("LapKnockOutVigo",20);
        Race knockOutRaceSantiago = new LapKnockOutRace("LapKnockOutVigo",20);
        Race standardRaceOurense = new StandardRace("standardRace1", 20, 5);
        Race standardRaceACoruna = new StandardRace("standardRace1", 20, 5);

        List<Race> riasTournamentRaces = new ArrayList<Race>();
        riasTournamentRaces.add(knockOutRaceVigo);
        riasTournamentRaces.add(standardRaceACoruna);

        List<Race> inlandTournamentRaces = new ArrayList<Race>();
        inlandTournamentRaces.add(knockOutRaceLugo);
        inlandTournamentRaces.add(standardRaceOurense);
        inlandTournamentRaces.add(knockOutRaceSantiago);
        
        Tournament riasTournament = new Tournament("RiasTournament",riasTeams,riasTournamentRaces);
        Tournament inlandTournament = new Tournament("Inland Tournament",inlandTeams,inlandTournamentRaces);
        
        riasTournament.celebrateTournament();
        
        System.out.println(riasTournament.toString());
        return null;
	}
	
	public void testRace() {
		Race race = createRace();
	}

}
