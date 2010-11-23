public class DTMFCommunicator {
	
	private DTMFDetector dD;
	private DTMFGenerator dG;
	private DetectSegmentRunner sR;
	private Thread sThread;
	
	// Timers
	private Timer segmentTimeoutTimer = new Timer();
	
	// Data
	private boolean segmentStarted = false;
	private String currentSegment = "";
	
	// Setup
	private static final int segmentTimeout = 2; 		// How many seconds before a segment has timed out.
	private static final int segmentDetectorPause = 100; // Hoe many milliseconds between the segment detector is run.
	
	public static void main (String[] args) {
		DTMFCommunicator dC = new DTMFCommunicator();
		dC.DTMFCommunicator();
	}
	
	public void DTMFCommunicator () {
		dD = new DTMFDetector();
		dD.DTMFDetector();
		dD.dCom = this;
		dD.mixerIndex = 1;
		dD.startListener();
		
		dG = new DTMFGenerator();
		dG.soundQueue = "*D#";
		dG.start();
		
		sR = new DetectSegmentRunner();
		sThread = new Thread(sR);
	    sThread.start();
	}
	
	private void segmentDecoder() {
		// Do nothing if segment hasn't started yet.
		if (!segmentStarted) {
			//System.out.println("Hep 1");
			return;
		}
		
		// 1: if timeout is too large and segment unfinished:
		// Clear current segment, segment started and return
		
		if (segmentTimeoutTimer.timeSinceStartInSeconds() > segmentTimeout) {
			segmentStarted = false;
			currentSegment = "";
			//System.out.println("Hep 2");
			return;
		}
		
		// 2: Check for finished segment.
		if (segmentFinished()) {
			System.out.println(currentSegment);
			segmentStarted = true;
			currentSegment = "";
				//System.out.println("Hep 3");
			return;
		}
		
		
			//System.out.println("Hep 4");
		return;
		// 2: Timeout is good and 
	}
	
	private boolean segmentFinished () {
		if (currentSegment.length() < 1 ) {
			return false;
		}
		
		String lastChar = currentSegment.substring(currentSegment.length() - 1);
		if (lastChar.equals("#")) {
			return true;
		}
		return false;
	}
	
	public void newTone (String t) {
		//System.out.println("Tone!: " + t);
		segmentTimeoutTimer.restart();
		segmentStarted = true;
		currentSegment += t;
	}
	
	class DetectSegmentRunner implements Runnable {
		public void run () {
			while (true) {
				segmentDecoder();
				try { Thread.sleep(segmentDetectorPause); } catch (InterruptedException e) { e.printStackTrace();}
			}
		}
	}
}