import java.util.LinkedList;

//express trains and local trains share much functionality and attributes so 
//abstract train class was made. Also general train class does not need to be instantiated so another reason to use abstract class.
public abstract class Train implements Runnable {
	protected int trainSpeed;
	protected int trainNumber;
	protected static int nextTrainNumber = 1; // each train has an incremented train number with the first train being
												// train
												// no. 1 and so on
	protected Segment currentSegment;
	protected Segment nextSegment;
	protected LinkedList<Segment> railway;

	// constructor creates a train object and is added to the first segment
	public Train(LinkedList<Segment> railwayToAdd) {
		this.railway = railwayToAdd;
		trainNumber = nextTrainNumber;
		nextTrainNumber++;
		currentSegment = railway.getFirst();
		nextSegment = currentSegment.getNextSegment();
		currentSegment.enter(this);

	}

	/**
	 * returns the nextsegment
	 * 
	 * @return Segment
	 */
	public Segment getNextSegment() {
		return nextSegment;
	}

	/**
	 * Method that orders train to leave current segment and depart for next segment
	 */
	public void moveTrain() {

		if (nextSegment != null) {
			nextSegment.enter(this); // train enters next segment
			currentSegment.depart(this); //train departs current segment
			currentSegment = nextSegment; 	//moves segment pointers to reflect where train is and needs to be next
			nextSegment = currentSegment.getNextSegment();
		} else {
			currentSegment.depart(this); //if train has reached final station remove train from station

		}

	}

	/**
	 * overridden run method that calls train to move if there is a next segment or
	 * to relinquish the train if it finished at the last station
	 * An express train which has a speed of 500 m/s will get through a track in 2 seconds and a station in .2 seconds
	 * A local train which has a speed of 10 m/s will get through a track in 100 seconds and as station in 10 seconds
	 */
	public void run() {
		int timeAtSegment;

		while (true) {

			try {
				timeAtSegment = ((currentSegment.getLength() / trainSpeed)*1000); // time a train is on a segment in
																					// milliseconds
				if (currentSegment instanceof station) { // if segment is a station add 5000 milliseconds for pickup
															// time
					timeAtSegment += 5000;
				}
				Thread.sleep(timeAtSegment);
				moveTrain();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public String toString() {
		return "" + trainNumber;
	}

}
