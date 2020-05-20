package racecontrol.paz;

import java.io.Serializable;

public class Coche implements Serializable {
	
	private String brand;
	private String model;
	private int maxSpeed = 100;
	
	public Coche(String brand, String model, int maxSpeed) {
		super();
		this.brand = brand;
		this.model = model;
		this.maxSpeed = maxSpeed;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
	public String imprimir() {
		String print = brand+" "+model;
		return print;
	}

}
