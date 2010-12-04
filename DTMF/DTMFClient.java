public class DTMFClient implements Runnable {
	private DTMFCommunicator dC;
	private Thread runner;
	public boolean threadDone = false;
	
	public static void main (String[] args) {
		DTMFClient dCl = new DTMFClient();
		dCl.DTMFClient();
	}

	public void stop() {
		threadDone = true;
	}
	
	public void DTMFClient () {
		runner = new Thread(this);
		dC = new DTMFCommunicator();
		dC.DTMFCommunicator();
		dC.requestIP();
		runner.start();
	}
	
	public void run () {
		while (true) {
			try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
		}
	}
}