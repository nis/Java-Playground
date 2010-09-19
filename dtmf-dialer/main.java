public class main {
	public static void main (String[] args) {
		System.out.println("Howdy!");
		
		DialerFrame df = new DialerFrame("Ny titel!");
		
		df.createButtonPanel("Status: ");
		df.setVisible(true);
	}
}