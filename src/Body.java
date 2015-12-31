import java.util.ArrayList;
import java.util.Random;

public class Body {
	//final double G = 6.673e-11;
	private final double G = .032;
	private static int classId = 0;
	private static double timeStep = .25;
	
	private int id;
	private boolean removed;
	private double mass;
	private Vector pos;
	private Vector vel;
	private Vector acc;
	private ArrayList<Body> allBodies;
	
	public Body() {
		id = classId++;
		removed = false;
		Random rand = new Random();
		mass = rand.nextDouble() * 1000;
		pos = new Vector(rand.nextInt(500), rand.nextInt(500));
		vel = new Vector(0,0);
		acc = new Vector(0,0);
		allBodies = new ArrayList<Body>();
	}
	
	public Body(int xPos, int yPos, Vector initVel, double mass){
		id = classId++;
		removed = false;
		this.mass = mass;
		pos = new Vector(xPos, yPos);
		vel = initVel;
		acc = new Vector(0,0);
		allBodies = new ArrayList<Body>();
	}
	
	public Vector getPos() {
		return pos;
	}
	
	public int getId() {
		return id;
	}
	
	public void setAcceleration() {
		for(int i = id; i < allBodies.size(); i++) {
			// don't attract yourself
			if(i != id && !allBodies.get(i).isRemoved()){
				Vector vec = new Vector(allBodies.get(i).getPos().getX() - pos.getX()
										,allBodies.get(i).getPos().getY() - pos.getY());
				double dis = Vector.distance(pos, allBodies.get(i).getPos());
				vec.divide(dis); // vec is now a unit vector in the direction this force will add
				vec.multiply(mass); // 1 * m
				vec.multiply(allBodies.get(i).mass); // m * M
				vec.multiply(G); // m * M * G 
				vec.divide(dis);
				vec.divide(dis); // G * m * M / d / d
				acc.add(vec);
				vec.multiply(-1); // pulls in opposite direction for other particle
				allBodies.get(i).getAcceleration().add(vec);
			}
		}
	}
	
	public void update() {
		acc.divide(mass); // Since F = m * a, a = F / m
		vel.timeStepAdd(acc, timeStep);
		pos.timeStepAdd(vel, timeStep);
		acc.setX(0);
		acc.setY(0);
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	public void remove(){
		removed = true;
	}
	
	public void unRemove(){
		removed = false;
	}
	
	public void setAllBodies(ArrayList<Body> allBodies){
		this.allBodies = allBodies; 
	}
	
	public Vector getAcceleration(){
		return this.acc;
	}
	
	public void setVelocity(Vector vel){
		this.vel = vel;
	}
	
	public Vector getVelocity(){
		return vel;
	}
	public void setPosition(Vector vec){
		pos = vec;
	}
	public void setAcceleration(Vector acc){
		this.acc = acc;
	}
	
	public ArrayList<Body> getAllBodies(){
		return this.allBodies;
	}
	
	public double getMass(){
		return this.mass;
	}
	
	public void setMass(double mass){
		this.mass = mass;
	}
	
	public static void setTimeStep(double step){
		timeStep = step;
	}
	
	public static double getTimeStep(){
		return timeStep;
	}
}
