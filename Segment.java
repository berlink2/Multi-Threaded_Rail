import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//tracks and stations share attributes and functionality so abstract segment class that they inherit
//from is made. Also general segment class does not need to be instantiated so another reason to use abstract class.
public abstract class Segment {
	protected Segment nextSegment;
	protected int length;
	protected boolean isFull;
	protected ArrayList<Train> capacity = new ArrayList<Train>();
	protected ReentrantLock lock = new ReentrantLock();
	protected Condition cond = lock.newCondition();

	public Segment(int length) {
		this.length = length;
	}

	public boolean getIsFull() {
		return isFull;
	}


	/**
	 * returns an arraylist that contains the trains in a particular segment
	 * 
	 * @return ArrayList<Train>
	 */
	public ArrayList<Train> getCapacity() {
		return capacity;
	}

	/**
	 * method that returns the next segment
	 * 
	 * @return Segment
	 */
	public Segment getNextSegment() {
		return nextSegment;
	}

	/**
	 * method that sets the next segment
	 * 
	 * @param nextSegment
	 */
	public void setNextSegment(Segment nextSegment) {
		this.nextSegment = nextSegment;
	}

	/**
	 * method that returns the length of the segment
	 * 
	 * @return int
	 */
	public int getLength() {
		return length;
	}

	/**
	 * method for train to enter station if station is not full checks to see if
	 * current segment is full. if it is it waits, if it is not it enters the next
	 * segment
	 * 
	 * @param train
	 */
	public void enter(Train train) {
		isFull = isFull();
		lock.lock();
		try {
			while (isFull == true) {
				cond.await();
			}
			isFull = false;
			capacity.add(train);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * method for train to depart station if next segment is not full checks to see
	 * if next segment is full, if it is not it departs the last station
	 * 
	 * @param train
	 */
	public void depart(Train train) {
		boolean isNextFull = false;
		if (nextSegment != null) {
			isNextFull = nextSegment.getIsFull();
		} 
		lock.lock();
		try {
			while (isNextFull == true) {
				cond.await();
			}
			capacity.remove(train);
			isFull = false;
			cond.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	/**
	 * method that returns status of rail as a string
	 * @return String
	 */
	public abstract String getStatus();
	/**
	 * method that creates a string for printing purposes
	 * @return String
	 */
	public String createSegment() {
		String track = "|----" + getStatus() + "----|";
		return track;
	}

	/**
	 * method for determining if a segment is full
	 * 
	 * @return boolean
	 */
	public abstract boolean isFull();

}
