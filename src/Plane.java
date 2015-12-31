import java.util.ArrayList;

public class Plane {
	
	private ArrayList<Body> bodies;
	
	public Plane(){
		
	}
	
	public void update(){
		for(int i = 0; i < bodies.size(); i++){
			if(!bodies.get(i).isRemoved()){
				// update the acceleration for each particle
				bodies.get(i).setAcceleration();
				// update the vectors for each particle
				bodies.get(i).update();
			}
		}
	}
}
