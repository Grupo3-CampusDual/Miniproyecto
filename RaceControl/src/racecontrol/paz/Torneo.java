package racecontrol.paz;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Torneo {
	private ArrayList<Carreras> carrerasList;
	String tipo;
	public Torneo(ArrayList<Carreras> carrerasList, String tipo) {
		super();
		this.carrerasList = carrerasList;
		this.tipo = tipo;
	}
	
	public void organizaTorneo() {
		if(this.carrerasList.size()<10) {
			int loop = 10-this.carrerasList.size();
			for (int i = 0;i<loop;i++) {
				this.carrerasList.add(this.carrerasList.get(i));
			} 
		}
		else {
			Collections.shuffle(this.carrerasList);
			ArrayList<Carreras> aux = new ArrayList<Carreras>();
			for(int i =0;i<10;i++) {
				aux.add(this.carrerasList.get(i));
			}
			this.carrerasList.clear();
			this.carrerasList = aux;
			}
		}
	
	public ArrayList<Carreras> getListaCarreras() {
		return getListaCarreras();
	}

	public void setcarrerasList(ArrayList<Carreras> carrerasList) {
		this.carrerasList = carrerasList;
	}
	
	public void comenzarTorneo(Garage garage) throws IOException {
		ArrayList<RunningCar> parrilla = new ArrayList<RunningCar>();
		ArrayList<Coche> podio = new ArrayList<Coche>();
		for(Coche car : garage.getCarList()) {
			RunningCar rc = new RunningCar(car.getBrand(),car.getModel(),car.getMaxSpeed(),10,0);
			parrilla.add(rc);
		}
		
		System.out.println("Torneo "+this.tipo);
		for(Carreras r : this.carrerasList) {
			System.out.println("Bienvenido a "+r.getNombre());
			Motor me;
			if(this.tipo.equals("Eliminatorio")) {
				 me = new MotorEliminar(r,garage.getCarList());}
			else {
				me = new MotorEstandar(r,garage.getCarList());}
				podio = me.simulaCarrera();
				int i = 1;
				for(Coche car :podio) {
					for(RunningCar rc : parrilla) {
						if(rc.getBrand().equals(car.getBrand()) && rc.getModel().equals(car.getModel())) {
							rc.setPoints(rc.getPoints()+3-podio.indexOf(car));
						}
					}
				System.out.println(i+"."+car.imprimir());
				i++;
				}
		}
		
		System.out.println("Torneo finalizado!");
		File f = new File("tourneyHistory.txt");
		FileWriter fw = new FileWriter(f,true);
		LocalDateTime date = LocalDateTime.now();
		fw.write("Torneo "+this.tipo+" "+date.toString()+"\n");
		int i = 1;
		Comparator<RunningCar> compararPuntuacion = new Comparator<RunningCar>() {
			@Override
			public int compare(RunningCar rc1, RunningCar rc2) {			
				return rc1.getPoints()-rc2.getPoints();
			}
				
		};
		Collections.sort(parrilla, compararPuntuacion);
		Collections.reverse(parrilla);
		for(RunningCar rc : parrilla) {
			System.out.println(i+"."+rc.imprimir()+" "+rc.getPoints());
			fw.write(i+"."+rc.imprimir()+" "+rc.getPoints()+"\n");
			i++;
		}
		fw.close();
	}
}


