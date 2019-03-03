public class track extends Segment {
	final private String segmentName = "Track";
	final private int trackSize = 1;

	public track(int length) {
		super(length);
		this.length = length;
	}

	/**
	 * method to determine if track is full
	 * 
	 * @return boolean
	 */
	public boolean isFull() {
		if (capacity.size() == trackSize) {
			isFull = true;
		} else if (capacity.size()< trackSize){
			isFull = false;
		}
		return isFull;
	}

	public String getStatus() {
		String status = null;
		if (capacity != null) {
			status = segmentName + " " + capacity.toString();
		} else {
			status = segmentName;
		}
		return status;
	}


}
