import javax.sound.sampled.*;

public class MySound implements Runnable {
		float[] frekvenserx = new float[]{1209.0F, 1336.0F, 1477.0F, 1633.0F};
		float[] frekvensery = new float[]{697.0F, 770.0F, 852.0F, 941.0F};
		
		String sQ = "";
		Boolean playing = false;
		public Boolean playQueue = false;
		String lastPlayed;
		
		public void run() {
			while (true) {
				if (!playQueue) {
					try { Thread.sleep(25); } catch (InterruptedException e) { }
				} else {
					if (sQ.length() > 0) {
						playNext();
					} else {
						playQueue = false;
					}
				}
			}
		}
		
		public static void MySound (String[] args) {
			
		}
		
		public void addToQueue(String s) {
			sQ = sQ + s;
		}
		
		public void playQueue() {
			playQueue = true;
			// if (!playing && sQ.length() > 0) {
			// 	playing = true;
			// 	playNext();
			// 	
			// 	playQueue();
			// }
			// 
			// if (sQ.length() < 1) {
			// 	playing = false;
			// }
		}
		
		public void playNext() {
			char s = sQ.charAt(0);
			lastPlayed = Character.toString(s);
			sQ = sQ.substring(1);
			
			switch (s) {
				case '0': s0(100, 100); break;
		        case '1': s1(100, 100); break;
		        case '2': s2(100, 100); break;
		        case '3': s3(100, 100); break;
		        case '4': s4(100, 100); break;
		        case '5': s5(100, 100); break;
		        case '6': s6(100, 100); break;
		        case '7': s7(100, 100); break;
		        case '8': s8(100, 100); break;
		        case '9': s9(100, 100); break;
		        case '#': sHash(100, 100); break;
		        case '*': sStar(100, 100); break;
		        case 'a': 
		        case 'A': sA(100, 100); break;
		        case 'b': 
		        case 'B': sB(100, 100); break;
		        case 'c': 
		        case 'C': sC(100, 100); break;
		        case 'd': 
		        case 'D': sD(100, 100); break;
			}
			
			try {
			Thread.sleep(500);
			} catch (InterruptedException e) {
			e.printStackTrace();
			}
		}
		
		public void s0(int l, int v) {
			try { generateTones(frekvensery[3], frekvenserx[1], l, v); } catch(Exception e) { }
		}
		
		public void s1(int l, int v) {
			try { generateTones(frekvensery[0], frekvenserx[0], l, v); } catch(Exception e) { }
		}
		
		public void s2(int l, int v) {
			try { generateTones(frekvensery[0], frekvenserx[1], l, v); } catch(Exception e) { }
		}
		
		public void s3(int l, int v) {
			try { generateTones(frekvensery[0], frekvenserx[2], l, v); } catch(Exception e) { }
		}
		
		public void s4(int l, int v) {
			try { generateTones(frekvensery[1], frekvenserx[0], l, v); } catch(Exception e) { }
		}
		
		public void s5(int l, int v) {
			try { generateTones(frekvensery[1], frekvenserx[1], l, v); } catch(Exception e) { }
		}
		
		public void s6(int l, int v) {
			try { generateTones(frekvensery[1], frekvenserx[2], l, v); } catch(Exception e) { }
		}
		
		public void s7(int l, int v) {
			try { generateTones(frekvensery[2], frekvenserx[0], l, v); } catch(Exception e) { }
		}
		
		public void s8(int l, int v) {
			try { generateTones(frekvensery[2], frekvenserx[1], l, v); } catch(Exception e) { }
		}
		
		public void s9(int l, int v) {
			try { generateTones(frekvensery[2], frekvenserx[2], l, v); } catch(Exception e) { }
		}
		
		public void sA(int l, int v) {
			try { generateTones(frekvensery[0], frekvenserx[3], l, v); } catch(Exception e) { }
		}
		
		public void sB(int l, int v) {
			try { generateTones(frekvensery[1], frekvenserx[3], l, v); } catch(Exception e) { }
		}
		
		public void sC(int l, int v) {
			try { generateTones(frekvensery[2], frekvenserx[3], l, v); } catch(Exception e) { }
		}
		
		public void sD(int l, int v) {
			try { generateTones(frekvensery[3], frekvenserx[3], l, v); } catch(Exception e) { }
		}
		
		public void sStar(int l, int v) {
			try { generateTones(frekvensery[3], frekvenserx[0], l, v); } catch(Exception e) { }
		}
		
		public void sHash(int l, int v) {
			try { generateTones(frekvensery[3], frekvenserx[2], l, v); } catch(Exception e) { }
		}
		
		public void generateTones(float hz1,float hz2, int msecs, int volume) throws Exception {
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
		        for (int i = 0; i < msecs * frequency / 1000; i++)
		        {
		            double angle = i / (frequency / hz1)* ttpi;
					double angle2 = i / (frequency / hz2) * ttpi;
					buf[0] = (byte) (((Math.sin(angle)) + (Math.sin(angle2)))*10);
					sdl.write(buf, 0, 1);
		        }
		        sdl.drain();
		        sdl.stop();
		        sdl.close();
		    }
}