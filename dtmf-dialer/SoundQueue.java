import javax.sound.sampled.*;

public class SoundQueue implements Runnable {
	float[] frekvenserx = new float[]{1209.0F, 1336.0F, 1477.0F, 1477.0F};
	float[] frekvensery = new float[]{697.0F, 770.0F, 852.0F, 941.0F};
	String sQ = "";
	
	static final int pauseBetweenTones = 0;
	static final int pauseBetweenQueueChecks = 10;
	static final int toneLength = 200;
	static final int toneVolume = 15;
	
	public SoundQueue() {
		
	}
	
	public SoundQueue(String q) {
		sQ = q;
	}
	
	public void run () {
		while (true) {
			if (sQ.length() > 0) {
				int sL = sQ.length();
				for (int i = 0; i < sL; i++) {
					playNext();
					try { Thread.sleep(pauseBetweenTones); } catch (InterruptedException e) { e.printStackTrace(); }
				}
			} else {
				try { Thread.sleep(pauseBetweenQueueChecks); } catch (InterruptedException e) { e.printStackTrace(); }
			}
		}
	}
	
	public void addToQueue (String s) {
		sQ = sQ + s;
	}
	
	private void playNext() {
		char s = sQ.charAt(0);
		sQ = sQ.substring(1);
		try {
			switch (s) {
				case '0': generateTones(frekvensery[3], frekvenserx[1]); break;
	    	    case '1': generateTones(frekvensery[0], frekvenserx[0]); break;
	    	    case '2': generateTones(frekvensery[0], frekvenserx[1]); break;
	    	    case '3': generateTones(frekvensery[0], frekvenserx[2]); break;
	    	    case '4': generateTones(frekvensery[1], frekvenserx[0]); break;
	    	    case '5': generateTones(frekvensery[1], frekvenserx[1]); break;
	    	    case '6': generateTones(frekvensery[1], frekvenserx[2]); break;
	    	    case '7': generateTones(frekvensery[2], frekvenserx[0]); break;
	    	    case '8': generateTones(frekvensery[2], frekvenserx[1]); break;
	    	    case '9': generateTones(frekvensery[2], frekvenserx[2]); break;
	    	    case '#': generateTones(frekvensery[3], frekvenserx[2]); break;
	    	    case '*': generateTones(frekvensery[3], frekvenserx[0]); break;
	    	    case 'a': 
	    	    case 'A': generateTones(frekvensery[0], frekvenserx[3]); break;
	    	    case 'b': 
	    	    case 'B': generateTones(frekvensery[1], frekvenserx[3]); break;
	    	    case 'c': 
	    	    case 'C': generateTones(frekvensery[2], frekvenserx[3]); break;
	    	    case 'd': 
	    	    case 'D': generateTones(frekvensery[3], frekvenserx[3]); break;
			}
		} catch(Exception e) { }
	}
	
	private void generateTones(float hz1,float hz2) throws Exception {
		float frequency = 44100.0F;
		int samplesize = 8;
		int channels;
		boolean signed = true;
		boolean bigendian = false;
		byte[] buf;
		double ttpi = (2.0 * Math.PI);
		AudioFormat format;
		//buf = new byte[2];
		buf = new byte[1];
		//channels = 2;
		channels = 1;
		format = new AudioFormat(frequency, samplesize, channels, signed,
		                         bigendian);
		SourceDataLine sdl = AudioSystem.getSourceDataLine(format);
		sdl.open(format);
		sdl.start();
		for (int i = 0; i < toneLength * frequency / 1000; i++)
		{
		    double angle = i / (frequency / hz1)* ttpi;
			double angle2 = i / (frequency / hz2) * ttpi;
			buf[0] = (byte) (((Math.sin(angle)) + (Math.sin(angle2)))*toneVolume);
			sdl.write(buf, 0, 1);
		}
		sdl.drain();
		sdl.stop();
		sdl.close();
	}
	
}