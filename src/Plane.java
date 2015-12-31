import java.util.ArrayList;

public class Plane {
	
	private ArrayList<Body> bodies;
	
	//final double gravityConstant = 6.673e-11;
	private final double gravityConstant = .032;
	private static double timeStep = .25;
	private int bodyId;
	
	public Plane(){
		bodyId = 0; // use this value as the body identifier
	}
	
	public void update(){
		// update the acceleration for each body
		for(int i = 0; i < bodies.size(); i++){
			Body currentBody = bodies.get(i);
			// calculate the current body's new acceleration with respect to all other bodies
			for(int j = i; j < bodies.size(); j++) {
				Body nextBody = bodies.get(j);
				// don't let the current body attract itself
				if(nextBody.getId() != currentBody.getId()){
					Vector force = new Vector(nextBody.getPosition().getX() - currentBody.getPosition().getX()
											,nextBody.getPosition().getY() - currentBody.getPosition().getY());
					// calculate the distance between bodies
					double distance = Vector.distance(currentBody.getPosition(), nextBody.getPosition());
					force.divide(distance); // force is now a unit vector in the direction this force will add
					force.multiply(currentBody.getMass()); // 1 * m
					force.multiply(nextBody.getMass()); // m * M
					force.multiply(gravityConstant); // m * M * G 
					force.divide(distance);
					force.divide(distance); // G * m * M / d / d
					currentBody.getAcceleration().add(force);
					force.multiply(-1); // pulls in opposite direction for other body
					nextBody.getAcceleration().add(force);
				}
			}
			currentBody.update(timeStep); // update the vectors for each body
		}
	}
	
	public void addBody(Body body){
		body.setId(bodyId);
		bodies.add(body);
	}
	
	public ArrayList<Body> getBodies(){
		return bodies;
	}
}
