import java.applet.AudioClip;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
/** <p>An object implementing {@link java.applet.AudioClip java.applet.AudioClip} with the data from DTMFTone.au hard-coded into it.</p>
 * <p>Created with the <a href="http://stephengware.com/projects/soundtoclass">SoundToClass tool</a>, by Stephen G. Ware.</p>
 * @author Stephen G. Ware */
public class DTMFTone implements AudioClip {
	public byte[] data;
	private AudioFormat format;
	private DataLine.Info lineInfo = null;
	private PlayThread playThread = null;
	private LoopThread loopThread = null;
	private static final long serialVersionUID = 1602;
	// public byte[] data0(){ return new byte[] {0,0,97,124,117,124,50,124,-43,-124,-90,-124,-61,-124,-3,-12,32,124,21,-4,1,84,6,-100,34,124,44,124,4,-36,-66,-124,-102,-124,-57,-124,42,124,117,124,105,124,12,-68,-90,-124,-118,-124,-57,-124,30,-4,77,124,54,124,6,92,-19,4,-9,-60,6,92,-10,-60,-47,-124,-59,-124,-14,68,65,124,109,124,69,124,-33,-124,-118,-124,-114,-124,-25,4,77,124,109,124,60,124,-19,4,-63,-124,-47,-124,-8,68,8,-68,-6,36,-16,4,8,-68,54,124,73,124,24,-4,-63,-124,-118,-124,-82,-124,19,-4,109,124,117,124,36,124,-63,-124,-102,-124,-63,-124,9,60,48,124,36,124,6,92,0,24,19,-4,27,-4,-6,-92,-63,-124,-86,-124,-37,-124,56,124,121,124,93,124,-8,68,-102,-124,-118,-124,-45,-124,50,124,93,124,60,124,-2,116,-35,-124,-24,4,-3,-76,-7,-92,-33,-124,-41,-124,-1,108,65,124,97,124,50,124,-49,-124,-122,-124,-102,-124,-6,-92,93,124,117,124,52,124,-37,-124,-82,-124,-55,-124,-4,100,21,-4,10,-68,-5,100,9,-68,44,124,54,124,8,-68,-66,-124,-106,-124,-63,-124,38,124,117,124,109,124,17,-4,-82,-124,-110,-124,-57,-124,24,-4,65,124,48,124,6,92,-11,68,3,76,13,-68,-11,68,-55,-124,-70,-124,-19,4,65,124,113,124,77,124,-28,4,-114,-124,-114,-124,-29,4,69,124,105,124,58,124,-15,-60,-53,-124,-39,-124,-7,-28,1,84,-17,4,-25,4,8,60,62,124,81,124,30,-4,-63,-124,-122,-124,-90,-124,15,60,109,124,117,124,38,124,-57,-124,-94,-124,-59,-124,5,92,38,124,25,-4,2,-20,5,-36,30,-4,36,124,-4,-108,-70,-124,-94,-124,-45,-124,54,124,121,124,97,124,-3,-44,-98,-124,-118,-124,-49,-124,42,124,81,124,54,124,0,-44,-25,4,-14,-60,2,-20,-11,-60,-43,-124,-51,-124,-5,-28,69,124,105,124,56,124,-47,-124,-122,-124,-106,-124,-11,68,85,124,113,124,52,124,-30,4,-70,-124,-49,-124,-5,100,13,60,-1,76,-12,-60,12,60,54,124,65,124,13,-68,-70,-124,-114,-124,-70,-124,34,124,117,124,113,124,22,-4,-74,-124,-106,-124,-57,-124,18,-4,56,124,38,124,5,92,-4,-12,14,-68,21,-4,-12,-60,-63,-124,-78,-124,-25,4,65,124,117,124,81,124,-23,4,-114,-124,-114,-124,-33,-124,62,124,97,124,56,124,-10,68,-43,-124,-28,4,-4,-76,-6,-28,-29,4,-35,-124,7,28,69,124,89,124,36,124,-61,-124,-122,-124,-94,-124,9,-68,101,124,113,124,42,124,-49,-124,-86,-124,-55,-124,2,76,28,-4,15,-4,-2,116,10,-68,42,124,46,124,-1,92,-74,-124,-102,-124,-51,-124,52,124,121,124,101,124,3,44,-94,-124,-114,-124,-49,-124,36,124,73,124,48,124,2,108,-16,4,-2,52,8,-68,-14,68,-53,-124,-61,-124,-9,-60,73,124,109,124,65,124,-43,-124,-122,-124,-110,-124,-16,4,81,124,105,124,52,124,-24,4,-61,-124,-41,-124,-5,-92,4,-36,-13,-60,-19,4,13,60,62,124,73,124,18,-4,-70,-124,-118,-124,-78,-124,29,-4,113,124,113,124,26,-4,-70,-124,-98,-124,-55,-124,13,-68,46,124,29,-4,3,76,3,-36,25,-4,29,-4,-11,68,-70,-124,-86,-124,-31,4,65,124,121,124,85,124,-17,4,-110,-124,-114,-124,-37,-124,54,124,89,124,52,124,-6,100,-33,-124,-18,4,0,104,-11,68,-41,-124,-43,-124,5,28,73,124,97,124,44,124,-59,-124,-126,-124,-98,-124,4,92,97,124,113,124,44,124,-41,-124,-78,-124,-49,-124,0,0,18,-4,4,92,-7,36,14,60,52,124,56,124,2,-20,-78,-124,-110,-124,-57,-124,48,124,121,124,105,124,8,60,-86,-124,-106,-124,-49,-124,28,-4,62,124,40,124,2,-20,-8,-28,9,-68,15,-4,-16,4,-63,-124,-70,-124,-13,68,73,124,117,124,69,124,-37,-124,-118,-124,-110,-124,-20,4,73,124,101,124,50,124,-18,4,-49,-124,-31,4,-4,-44,-3,84,-24,4,-27,4,13,-68,69,124,85,124,24,-4,-70,-124,-122,-124,-82,-124,24,-4,113,124,109,124,29,-4,-61,-124,-90,-124,-51,-124,9,60,34,124,19,-4,0,104,10,60,38,124,38,124,-10,-60,-74,-124,-98,-124,-37,-124,65,124,125,124,93,124,-12,68,-102,-124,-110,-124,-39,-124,46,124,77,124,46,124,-3,84,-22,4,-7,36,5,28,-16,-60,-51,-124,-53,-124,2,44,77,124,105,124,50,124,-55,-124,-126,-124,-102,-124,-2,-4,93,124,105,124,44,124,-35,-124,-66,-124,-41,-124,-2,-84,9,60,-8,-28,-14,-60,16,-4,60,124,65,124,7,28,-78,-124,-114,-124,-63,-124,44,124,121,124,105,124,13,60,-78,-124,-102,-124,-49,-124,22,-4,52,124,32,124,2,-116,1,20,20,-4,22,-4,-17,4,-70,-124,-82,-124,-18,4,73,124,121,124,73,124,-33,-124,-114,-124,-110,-124,-24,4,65,124}; }
	// public byte[] data1(){ return new byte[] {93,124,48,124,-14,-60,-39,-124,-21,4,-1,12,-10,68,-35,-124,-35,-124,13,60,77,124,93,124,29,-4,-70,-124,-126,-124,-86,-124,18,-4,105,124,109,124,32,124,-53,-124,-82,-124,-47,-124,5,-100,24,-4,9,60,-4,-108,15,-4,48,124,48,124,-7,36,-82,-124,-106,-124,-43,-124,60,124,125,124,93,124,-7,-92,-98,-124,-106,-124,-41,-124,40,124,69,124,40,124,-1,108,-12,68,4,-100,10,60,-19,4,-61,-124,-63,-124,-2,116,81,124,113,124,56,124,-51,-124,-126,-124,-102,-124,-7,-28,85,124,101,124,44,124,-28,4,-57,-124,-33,-124,-2,84,0,-76,-19,4,-20,4,18,-4,69,124,73,124,11,-68,-78,-124,-122,-124,-70,-124,38,124,117,124,105,124,16,-4,-74,-124,-94,-124,-47,-124,16,-4,42,124,22,-4,1,52,8,-68,32,124,30,-4,-17,4,-78,-124,-90,-124,-24,4,73,124,125,124,81,124,-27,4,-110,-124,-110,-124,-27,4,58,124,85,124,44,124,-9,68,-28,4,-12,-60,2,44,-16,4,-47,-124,-45,-124,11,-68,81,124,101,124,36,124,-66,-124,-126,-124,-90,-124,13,-68,101,124,105,124,34,124,-45,-124,-74,-124,-41,-124,2,-52,14,-68,-3,-12,-9,-60,19,-4,58,124,56,124,-4,36,-86,-124,-110,-124,-51,-124,56,124,125,124,97,124,-2,-20,-90,-124,-102,-124,-41,-124,32,124,58,124,34,124,0,-108,-3,84,15,-4,16,-4,-21,4,-70,-124,-70,-124,-6,36,81,124,117,124,62,124,-47,-124,-122,-124,-102,-124,-11,68,77,124,93,124,42,124,-22,4,-45,-124,-24,4,-2,-36,-8,-92,-30,4,-27,4,19,-4,77,124,85,124,16,-4,-78,-124,-126,-124,-74,-124,34,124,113,124,105,124,20,-4,-66,-124,-86,-124,-43,-124,12,60,30,-4,13,60,-2,-36,15,60,44,124,38,124,-16,4,-82,-124,-98,-124,-30,4,73,124,125,124,85,124,-21,4,-106,-124,-106,-124,-30,4,50,124,73,124,40,124,-6,-92,-17,4,-1,-120,6,28,-21,4,-57,-124,-53,-124,9,60,85,124,105,124,42,124,-66,-124,-126,-124,-94,-124,8,60,93,124,101,124,36,124,-39,-124,-63,-124,-35,-124,0,-28,4,-36,-14,-60,-14,68,22,-4,69,124,65,124,-1,-16,-86,-124,-118,-124,-57,-124,52,124,125,124,97,124,3,-36,-82,-124,-98,-124,-41,-124,25,-4,48,124,25,-4,0,-60,5,-36,26,-4,23,-4,-23,4,-78,-124,-82,-124,-11,68,81,124,121,124,69,124,-41,-124,-118,-124,-102,-124,-15,68,69,124,85,124,40,124,-16,4,-35,-124,-16,-60,0,104,-15,68,-41,-124,-35,-124,19,-4,85,124,93,124,22,-4,-78,-124,-126,-124,-78,-124,28,-4,109,124,101,124,23,-4,-57,-124,-78,-124,-39,-124,8,60,19,-4}; }
	// private byte[] getData(){
	// 	byte[] data = new byte[1602];
	// 	System.arraycopy(data0(), 0, data, 0, 1024);
	// 	System.arraycopy(data1(), 0, data, 1024, 578);
	// 	return data;
	// }
	//public byte[] data = new byte[1602];
	/** Constructs a new AudioClip with the data from DTMFTone.au. */
	public DTMFTone(byte[] d){
		data = d;
		format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, (float)(8012.0), 16, 1, 2, (float)(8012.0), true);
		lineInfo = new DataLine.Info(SourceDataLine.class, format, AudioSystem.NOT_SPECIFIED);
		try{ AudioSystem.getLine(lineInfo); }
		catch(IllegalArgumentException ex){ lineInfo = null; }
		catch(LineUnavailableException e){}
	}
	/** A separate thread for playing DTMFTone.au. */
	private class PlayThread extends Thread {
		private byte[] data;
		private AudioFormat format;
		private DataLine.Info lineInfo;
		private SourceDataLine line = null;
		private boolean playing = true;
		public PlayThread(byte[] d, AudioFormat f, DataLine.Info i){ data = d; format = f; lineInfo = i; }
		public void run(){
			try{
				line = (SourceDataLine) AudioSystem.getLine(lineInfo);
				line.open(format, AudioSystem.NOT_SPECIFIED);
				line.start();
				int written = 0;
				int available;
				while(written < data.length && playing){
					available = Math.min(line.available(), data.length - written);
					line.write(data, written, available);
					written += available;
				}
				int frames = data.length / format.getFrameSize();
				while(line.getFramePosition() < frames && playing) Thread.sleep(0);
			}
			catch(InterruptedException ex){ playing = false; }
			catch(LineUnavailableException ex){}
			if(line != null){ line.stop(); line.flush(); line.close(); }
			playing = false;
		}
		public void interrupt(){ playing = false; }
		public boolean isPlaying(){ return playing; }
	}
	/** A separate thread for looping play of DTMFTone.au. */
	private class LoopThread extends Thread {
		private DTMFTone clip;
		private boolean looping = true;
		public LoopThread(DTMFTone c){ clip = c; }
		public void run(){
			while(looping){
				clip.play();
				while(clip.isPlaying() && looping){
					try{ Thread.sleep(0); }
					catch(InterruptedException ex){ looping = false; break; }
				}
			}
			if(!clip.isLooping()) clip.stop();
		}
		public void interrupt(){ looping = false; }
		public boolean isLooping(){ return looping; }
	}
	/** Plays DTMFTone.au from the beginning, even if it is already playing or looping. */
	public void play(){ if(lineInfo == null) return; doPlay(); }
	private synchronized void doPlay(){
		doStopPlay();
		playThread = new PlayThread(data, format, lineInfo);
		playThread.start();
	}
	/** Plays DTMFTone.au continuously until stopped. */
	public void loop(){ if(lineInfo == null) return; doLoop(); }
	private synchronized void doLoop(){
		doStopLoop();
		loopThread = new LoopThread(this);
		loopThread.start();
	}
	/** Stops play and looping of DTMFTone.au. */
	public void stop(){ if(lineInfo == null) return; doStop(); }
	private synchronized void doStop(){
		doStopPlay();
		doStopLoop();
	}
	private void doStopPlay(){
		if(playThread == null) return;
		if(playThread.isPlaying()) playThread.interrupt();
		playThread = null;
	}
	private void doStopLoop(){
		if(loopThread == null) return;
		if(loopThread.isLooping()) loopThread.interrupt();
		loopThread = null;
	}
	/** Tests if DTMFTone.au is currently playing or looping.
	 * @return <tt>true</tt> if playing or looping, <tt>false</tt> otherwise */
	public boolean isPlaying(){ if(lineInfo == null) return false; return doIsPlaying(); }
	private synchronized boolean doIsPlaying(){
		if(loopThread == null && playThread == null) return false;
		else if(loopThread == null) return playThread.isPlaying();
		else if(playThread == null) return loopThread.isLooping();
		else return loopThread.isLooping() && playThread.isPlaying();
	}
	/** Tests if DTMFTone.au is currently looping.
	 * @return <tt>true</tt> if looping, <tt>false</tt> otherwise */
	public boolean isLooping(){ if(lineInfo == null) return false; return doIsLooping(); }
	private synchronized boolean doIsLooping(){
		if(loopThread == null) return false;
		else return loopThread.isLooping();
	}
}