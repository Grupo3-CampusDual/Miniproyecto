package racecontrol.paz;

public class RunningCar extends Coche {
	
	private int speed;
	private int distanceTraveled;
	private int points=0;
	public RunningCar(String brand, String model, int maxSpeed, int speed, int distanceTraveled) {
		super(brand, model, maxSpeed);
		this.speed = speed;
		this.distanceTraveled = distanceTraveled;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getDistanceTraveled() {
		return distanceTraveled;
	}
	
	public void setDistanceTraveled(int distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void acelerar() {
		if(this.speed<this.getMaxSpeed()) {
		this.speed = this.speed+10;		
		}
	}
	
	public void frenar() {
		if(this.speed>0) {
			this.speed = this.speed-10;
		}
	}
	
}
