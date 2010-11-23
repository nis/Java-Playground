public class Timer {
	private static final long startTime = System.nanoTime();
	
	public Timer () {
	}
	
	public long timeSinceStartInNS () {
		return System.nanoTime() - startTime;
	}
	
	public float timeSinceStartInSeconds () {
		return (float) this.timeSinceStartInNS() / 1000000000;
	}
}