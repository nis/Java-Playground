public class DTMFCommunicator {
	
	// Classes
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
	public boolean listenForCom = false;
	public boolean server = false;
	public String serverIP = "";
	
	public static void main (String[] args) {
		DTMFCommunicator dC = new DTMFCommunicator();
		dC.DTMFCommunicator();
	}
	
	public void DTMFCommunicator () {
		dD = new DTMFDetector();
		dD.DTMFDetector();
		dD.dCom = this;
		dD.mixerIndex = 2;
		dD.startListener();
		
		dG = new DTMFGenerator();
		dG.start();
		
		sR = new DetectSegmentRunner();
		sThread = new Thread(sR);
	    sThread.start();
		
		//listenForIPRequest("192.168.0.16");
		
		//requestIP();
	}
	
	public void listenForIPRequest (String ip) {
		System.out.println("Server starting listening.");
		server = true;
		serverIP = ip;
		listenForCom = true;
	}
	
	public void requestIP () {
		server = false;
		System.out.println("Sending IP request.");
		dG.soundQueue = "*A#";
		while (dG.isPLaying) {
			// Do nothing
		}
		listenForCom = true;
	}
	
	public String cleanIP () {
		String parts[] = serverIP.split("\\.");
		//serverIP.replaceAll("\\.", "")
		for (int i = 0; i < parts.length; i++) {
			while (parts[i].length() < 3) {
				parts[i] = "0" + parts[i];
			}
		}
		//System.out.println("Parts: " + parts[0] + ", " + parts[1] + ", " + parts[2] + ", " + parts[3] + ".");
		return parts[0] +  parts[1] + parts[2] + parts[3];
	}
	
	private void sendIP () {
		dG.soundQueue = "*" + cleanIP() + "#";
		System.out.println("Sending : *" + cleanIP() + "#");
	}
	
	private void segmentDecide(String s) {
		//System.out.println(s);
		if (server) {
			listenForCom = false;
			if (s.equals("*A#")) {
				System.out.println("IP request received. Sending IP.");
				//dG.soundQueue = "*" + serverIP.replaceAll(".", "") + "#";
				sendIP(); 
				try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace();}
			}

			if (s.equals("*D#")) {
				System.out.println("NAK received. Sending IP.");
				dG.soundQueue = serverIP;
				try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace();}
			}
			listenForCom = true;
		}
		
		if (!server && s.length() == 14) {
			serverIP = s.substring(1, 4) + "." + s.substring(4, 7) + "." + s.substring(7, 10) + "." + s.substring(10, 13);
			serverIP = serverIP.replaceAll("\\.0", ".");
			serverIP = serverIP.replaceAll("\\.0", ".");
			System.out.println("IP received: " + serverIP);
			listenForCom = false;
		}
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
			segmentDecide(currentSegment);
			//System.out.println(currentSegment);
			segmentStarted = false;
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
		if (currentSegment.substring(currentSegment.length() - 1).equals("#")) {
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
				if (listenForCom) {
					segmentDecoder();
				}
				try { Thread.sleep(segmentDetectorPause); } catch (InterruptedException e) { e.printStackTrace();}
			}
		}
	}
}