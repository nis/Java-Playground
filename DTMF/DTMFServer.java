public class DTMFServer implements Runnable {
	private DTMFCommunicator dC;
	private Thread runner;
	public boolean threadDone = false;
	
	public static void main (String[] args) {
		DTMFServer dS = new DTMFServer();
		dS.DTMFServer();
	}
	
	public void stop() {
		threadDone = true;
	}
	
	public void DTMFServer () {
		dC = new DTMFCommunicator();
		dC.DTMFCommunicator();
		dC.listenForIPRequest("192.168.0.16");
		//dC.cleanIP();
		runner = new Thread(this);
		runner.start();
	}
	
	public void run () {
		while (!threadDone) {
			try { Thread.sleep(5); } catch (InterruptedException e) { e.printStackTrace();}
		}
	}
}