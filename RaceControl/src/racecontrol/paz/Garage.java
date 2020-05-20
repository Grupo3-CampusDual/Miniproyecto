package racecontrol.paz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;

public class Garage {
	
	private String team;
	private ArrayList<Coche> carList;
	public String getTeam() {
		return team;
	}
	public void setTeam(String team1) {
		team = team1;
	}
	
	public ArrayList<Coche> getCarList() {
		return carList;
	}
	
	public void setCarList(ArrayList<Coche> carList) {
		this.carList = carList;
	}
	
	public Garage(String team1, ArrayList<Coche> carList) {
		super();
		team = team1;
		this.carList = carList;
	}

	public void fillGarage (File file) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream obs = new ObjectInputStream(fis);
		Coche car = new Coche("","",0);
		try {
			while((car=(Coche)obs.readObject())!=null) {
				if(car!=null)
					this.carList.add(car);
			}
		} catch(java.io.EOFException eof) {}
			
		fis.close();	
		obs.close();
	}
	
	public void fillGarageRandom() throws IOException, ClassNotFoundException {
		File father = new File("Teams");
		File teams [] = father.listFiles();
		for(File f : teams) {
			ArrayList<Coche> list = new ArrayList<Coche>();
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream obs = new ObjectInputStream(fis);
			Coche car = new Coche("","",0);
			try {
				while((car=(Coche)obs.readObject())!=null) {
					if(car!=null)
					list.add(car);
				}
			} catch(java.io.EOFException eof) {}
			fis.close();
			obs.close();
			Random random = new Random();
			this.carList.add(list.get(random.nextInt(list.size()-1)));
			
		}
	}
	
	
}
