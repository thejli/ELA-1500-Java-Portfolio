import java.awt.Color;


public class Triangle {

	public static void main(String[] args) {
		World myWorld = new World(900,900,Color.WHITE);
		Turtle bob = new Turtle(myWorld);
		bob.setDelay(0);
		drawTriangle(bob, 4, -200,-100,405,-100,100,350.75);
		

	}

	public static void drawTriangle(Turtle t, int depth, double x1, double y1, double x2,double y2, double x3, double y3){
		
		if(depth == 0){
			t.pickPenUp();
			t.goTo(x1, y1);
			t.putPenDown();
			t.goTo(x2, y2);
			t.goTo(x3,y3);
			t.goTo(x1,y1);
			return;
		}
		
		drawTriangle(t,depth-1,x1,y1,(x2+x1)/2,(y2+y1)/2,(x3+x1)/2,(y3+y1)/2);
		drawTriangle(t,depth-1,x2,y2,(x2+x1)/2,(y2+y1)/2,(x3+x2)/2,(y3+y2)/2);
		drawTriangle(t,depth-1,x3,y3,(x2+x3)/2,(y2+y3)/2,(x3+x1)/2,(y3+y1)/2);
		
	}
	
	
}
