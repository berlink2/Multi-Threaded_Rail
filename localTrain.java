import java.util.LinkedList;

public class localTrain extends Train  {
	
	//local train constructor that takes in railway linked list
	// to pass to abstract train class and sets train speed to 10.
	public localTrain(LinkedList<Segment> railway) {
		super(railway);
		trainSpeed = 100;
			
	}
	
	
	
}
