package racecontrol.paz;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Motor {
	private Carreras carrera;
	private ArrayList<Coche> carList;
	private Comparator<RunningCar> distanceCompare = new Comparator<RunningCar>() {
		@Override
		public int compare(RunningCar rc1, RunningCar rc2) {
			return rc1.getDistanceTraveled() - rc2.getDistanceTraveled();
		}
	};
	
	public Motor(Carreras carrera, ArrayList<Coche> carList ) {
		super();
		this.carrera = carrera;
		this.carList = carList;
	}
	
	public ArrayList<Coche> simulaCarrera() {
		ArrayList<Coche> podio = new ArrayList<Coche>();
		ArrayList<RunningCar> parrilla= new ArrayList<RunningCar>();
		for(Coche coche: this.carList) {
			RunningCar rc = new RunningCar(coche.getBrand(),coche.getModel(),coche.getMaxSpeed(),10,0);
			parrilla.add(rc);
		}		
		parrilla = this.simulation(parrilla);
		for(int i = 0;i<3;i++) {
			podio.add(parrilla.get(i));
		}
		return podio;	
	}
	
public abstract ArrayList<RunningCar> simulation(ArrayList<RunningCar> car);
	
	public Carreras getCarrera() {
		return carrera;
	}
	
	public void setCarrera(Carreras carrera) {}
	public ArrayList<Coche> getListaCoches() {
		return carList;
	}
	public void setListaCoches(ArrayList<Coche> carList) {
		this.carList = carList;
	}
	
	public Comparator<RunningCar> getCompararDistancia() {
		return distanceCompare;
	}
	
	public void setCompararDistancia(Comparator<RunningCar> distanceCompare) {
		this.distanceCompare = distanceCompare;
	}
}
