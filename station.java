public class station extends Segment {
	private String stationName;
	private int stationSize;

	public station(String stationName, int stationSize, int length) {
		super(length);
		this.stationName = stationName;
		this.stationSize = stationSize;

	}

	/**
	 * method that returns size of the station
	 * 
	 * @return stationSize
	 */
	public int getSize() {
		return stationSize;
	}

	/**
	 * method that creates string that reflects current status of segment
	 */
	public String getStatus() {
		String status = null;
		if (capacity != null) {
			status = stationName + " " + capacity.toString();
		} else {
			status = stationName;
		}
		return status;
	}

	

	/**
	 * method to determine is station is full
	 */
	public boolean isFull() {
		if (capacity.size() == stationSize) {
			isFull = true;
		} else if(capacity.size() < stationSize) {
			isFull = false;
		}
		return isFull;
	}

}
