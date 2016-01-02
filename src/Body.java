/**
 * 
 * @author Marcos Cazulo
 * 
 */

import java.util.Random;

public class Body {
	
	private int id;
	private double mass;
	private Vector pos;
	private Vector vel;
	private Vector acc;
	private int radius;
	
	public Body() {
		Random rand = new Random();
		mass = rand.nextDouble() * 1000;
		pos = new Vector(rand.nextInt(500), rand.nextInt(500));
		vel = new Vector(0,0);
		acc = new Vector(0,0);
		radius = 10;
	}
	
	public Body(Vector initPosition, Vector initVel, double mass){
		this.mass = mass;
		pos = initPosition;
		vel = initVel;
		acc = new Vector(0,0);
		radius = 10;
	}
	
	public void update(double timeStep) {
		acc.divide(mass); // Since F = m * a, a = F / m
		vel.timeStepAdd(acc, timeStep);
		pos.timeStepAdd(vel, timeStep);
		acc.setX(0);
		acc.setY(0);
	}
	
	public Vector getAcceleration(){
		return this.acc;
	}
	
	public Vector getVelocity(){
		return vel;
	}
	
	public Vector getPosition() {
		return pos;
	}
	
	public void setVelocity(Vector vel){
		this.vel = vel;
	}
	
	public void setPosition(Vector vec){
		pos = vec;
	}
	public void setAcceleration(Vector acc){
		this.acc = acc;
	}
	
	public double getMass(){
		return this.mass;
	}
	
	public void setMass(double mass){
		this.mass = mass;
	}

	public void setId(int bodyId) {
		id = bodyId;
	}
	
	public int getId(){
		return id;
	}
	
	public int getRadius(){
		return radius;
	}
	
	public void setRadius(int radius){
		this.radius = radius;
	}
}
