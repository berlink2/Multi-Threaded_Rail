import java.util.LinkedList;
//Class that continually prints out the status of the railway
public class trackStatus {
	private LinkedList<Segment> railway;
	
	public trackStatus(LinkedList<Segment> railway) { //constructor takes in LinkedList with all segments of the railway
		this.railway = railway;
	}
	
	/**
	 * method that concatenates all of the segments in the railway and prints it together
	 */
	public void printStatus() {
		String railwayStatus = "";
		for (Segment segments:railway) {
			railwayStatus += segments.createSegment();
		}
		System.out.println(railwayStatus);
	}
	
}
