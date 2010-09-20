import javax.sound.sampled.*;

public class MySound {
		float[] frekvenserx = new float[]{1209.0F, 1336.0F, 1477.0F, 1477.0F};
		float[] frekvensery = new float[]{697.0F, 770.0F, 852.0F, 941.0F};
		
		public static void MySound (String[] args) {
			//frekvenserx[0] = 1209.0F;
			//frekvenserx[1] = 1336.0F;
			//frekvenserx[2] = 1477.0F;
			//frekvenserx[3] = 1477.0F;
			//
			//frekvensery[0] = 697.0F;
			//frekvensery[1] = 770.0F;
			//frekvensery[2] = 852.0F;
			//frekvensery[3] = 941.0F;
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