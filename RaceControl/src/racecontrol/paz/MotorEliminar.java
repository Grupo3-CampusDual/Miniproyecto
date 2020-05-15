package racecontrol.paz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MotorEliminar extends Motor {
	
	public MotorEliminar (Carreras carrera, ArrayList<Coche> carList) {
		super(carrera,carList);
	}
	
	@Override
	public ArrayList<RunningCar> simulation(ArrayList<RunningCar> car) {
		ArrayList<RunningCar> podio = new ArrayList<RunningCar>();
		Random random = new Random();
		boolean acelerar;
		int vueltas = car.size()-1;
		for(int i =0; i<vueltas;i++) {
			for(int j=0;j<5;j++) {
				for(RunningCar rc : car) {
					acelerar = random.nextBoolean();
					
					if(acelerar) {
						rc.acelerar();}
					else {
						rc.frenar();}
					rc.setDistanceTraveled(rc.getDistanceTraveled()+rc.getSpeed());
				}
			}
			Collections.sort(car,this.getCompararDistancia());
			if (car.size()<=4) {
				podio.add(car.get(car.size()-1));
			}
			car.remove(car.size()-1);
		}
		Collections.reverse(podio);
		return podio;
	}
}

