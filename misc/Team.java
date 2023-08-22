// compile: javac Team.java
// run:     java Team
import java.awt.Robot;
import java.util.Random;

public class Team {
	// WAIT -> interval for mouse movement {
		public static final int WAIT = 10000;
	// Maximum X position mouse can move
	public static final int Y = 400;
	// Maximum Y position mouse can move
	public static final int X = 400;
	
	public static void main(String... args) throws Exception {
		Robot robot = new Robot();
		Random random = new Random();
		while (true) {
			// move mouse to random x and y position
			int x = random.nextInt(X);
			int y = random.nextInt(Y);
			System.out.println("Mouse move to x="+x+" and y="+y+" position.");
			robot.mouseMove(x,y);
			Thread.sleep(WAIT);
		}
	}
}
