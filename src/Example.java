
public class Example {

	public static void main(String[] args) {
		Plane plane = new Plane();
		
		Body firstBody = new Body(new Vector(0,0), new Vector(0,0), 1000);
		Body secondBody = new Body(new Vector(100,50), new Vector(0,0), 100);
		
		plane.addBody(firstBody);
		plane.addBody(secondBody);
		
		while(true){
			try {
			    Thread.sleep(100); //in milliseconds
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			
			plane.update();
			System.out.println("*--------------------------------------------*");
			for(int i = 0; i < plane.getBodies().size(); i++){
				System.out.println(i +" Body position: " + plane.getBodies().get(i).getPosition().getX() + ", " + plane.getBodies().get(i).getPosition().getY());
			}
		}
	}
}
