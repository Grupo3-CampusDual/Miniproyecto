package racecontrol.paz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MotorEstandar extends Motor{

	public MotorEstandar(Carreras carrera, ArrayList<Coche> carList) {
		super(carrera, carList);
	}
	
	public ArrayList<RunningCar> simulation(ArrayList<RunningCar> car) {
		Random random = new Random();
		boolean acelerar;	
		//Simulamos una carrera de 180 minutos en la que los coches aceleran/ frenan cada 10 minutos
		for(int i=0;i<18;i++) {			
					for(RunningCar coche: car) {
							acelerar = random.nextBoolean();
					if(acelerar)
							coche.acelerar();
						else
								coche.frenar();
						coche.setDistanceTraveled(coche.getDistanceTraveled()+coche.getSpeed());
						}
		}
		Collections.sort(car,this.getCompararDistancia());
		return car;
	}

}
