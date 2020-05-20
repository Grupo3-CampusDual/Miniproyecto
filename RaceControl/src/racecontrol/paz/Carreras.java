package racecontrol.paz;

import java.io.Serializable;

public class Carreras implements Serializable {

	private String name;
	public Carreras(String name) {
		this.name = name;
	}
	public String getNombre() {
		return name;
	}
	public void setNombre(String nombre) {
		this.name = nombre;
	}
}
