public class Timer {
	private long startTime = System.nanoTime();
	
	public Timer () {}
	
	public void restart () {
		startTime = System.nanoTime();
	}
	
	public long timeSinceStartInNS () {
		return System.nanoTime() - startTime;
	}
	
	public float timeSinceStartInSeconds () {
		return (float) this.timeSinceStartInNS() / 1000000000;
	}
}