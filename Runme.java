import java.util.LinkedList;

public class Runme {

	public static void main(String[] args) {

		station station1 = new station("Kelvinhall", 3, 100); // creation of segment objects. can be a station or a
																// track
		track track1 = new track(1000);
		station station2 = new station("Hillhead", 2, 100);
		track track2 = new track(1000);
		station station3 = new station("KelvinBridge", 2, 100);
		LinkedList<Segment> railway = new LinkedList<Segment>(); // segments are added to a railway linkedlist

		railway.add(station1); // segments are connected to each other
		station1.setNextSegment(track1);

		railway.add(track1);
		track1.setNextSegment(station2);

		railway.add(station2);
		station2.setNextSegment(track2);

		railway.add(track2);
		track2.setNextSegment(station3);

		railway.add(station3);
		station3.setNextSegment(null);

		trainMaker trainMaker1 = new trainMaker(railway); // trainmaker object that makes trains
		
		Thread t1 = new Thread(trainMaker1); // trainmaker is set to its own thread
		t1.start();

		trackStatus trackStatus1 = new trackStatus(railway); // track status object that is responsible for printing
															// status of the rails	
		boolean isRunning = true; // prints status of rail simulation every second
		while (isRunning) {
			try {
				Thread.sleep(1000);
				trackStatus1.printStatus();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
