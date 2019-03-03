import java.util.LinkedList;
import java.util.Random;

//class that creates train objects
public class trainMaker implements Runnable {
	private LinkedList<Segment> railway;

	public trainMaker(LinkedList<Segment> railway) {
		this.railway = railway;

	}

	/**
	 * Method that makes a local train or an express train randomly
	 *
	 * @return Train
	 */
	public Train makeTrain() {

		Train newTrain = null;

		Random random = new Random();
		int randomTrain = random.nextInt(100);
		if (randomTrain % 2 == 0) {

			newTrain = new expressTrain(railway);

		} else {

			newTrain = new localTrain(railway);

		}

		return newTrain;
	}

	@Override
	/**
	 * Run method that makes trains indefinitely. Sleeps for random time between 0
	 * and 3 seconds to give delay between each train creation. Each train object is
	 * set to a new thread.
	 * 
	 */
	public void run() {
		Random random = new Random();
		int randomInt = random.nextInt(3000);
		while (true) {
			try {
				Thread.sleep(randomInt);
				Train train = makeTrain();
				Thread t = new Thread(train);
				t.start();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
