import javax.swing.JDialog;

public class Main {

	public static void main(String[] args) {
		
		Bgm bgm = new Bgm();
		bgm.setDaemon(true); // make song quit when programm exit
        bgm.start();  
        
		Intro intro = new Intro();
		intro.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		intro.setVisible(true);

	}

}
