import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;

public class Intro extends JFrame {

	private JPanel contentPane;
	private JLabel startTitle;
	private JPanel selectPanel;
	private JButton girlBtn;
	private JButton boyBtn;
	
	private int characterSex = -1; // for storing character type

	public int getCharacterSex() {
		return characterSex;
	}

	public void setCharacterSex(int characterSex) {
		this.characterSex = characterSex;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intro frame = new Intro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Intro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// set title label
		startTitle = new JLabel("");
		startTitle.setIcon(new ImageIcon("img/startTitle.PNG"));
		contentPane.add(startTitle, BorderLayout.NORTH);
		
		selectPanel = new JPanel();
		selectPanel.setBackground(new Color(0, 90, 0));
		contentPane.add(selectPanel, BorderLayout.CENTER);
		
		// set girl button
		girlBtn = new JButton("Myeongnyun");
		girlBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCharacterSex(0); // set character as Myeongnyun
				dispose(); //close the intro page
				
				try {
					FileOutputStream fileObject =new FileOutputStream("data.txt",false);
					
					PrintWriter x = new PrintWriter(fileObject);
					
					x.println(getCharacterSex() + "");
					x.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				GameStart game = new GameStart(); //start game
				game.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				game.setVisible(true);
			}
		});
		girlBtn.setBackground(Color.WHITE);
		girlBtn.setIcon(new ImageIcon("img/chooseMyung.png"));
		selectPanel.add(girlBtn);
		
		boyBtn = new JButton("Yuljeon");
		boyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCharacterSex(1); // set character as Yuljeon
				dispose(); //close the intro page
				
				try {
					FileOutputStream fileObject =new FileOutputStream("data.txt",false);
					
					PrintWriter x = new PrintWriter(fileObject);
					
					x.println(getCharacterSex() + "");
					x.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				GameStart game = new GameStart(); //start game
				game.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				game.setVisible(true);
			}
		});
		boyBtn.setBackground(Color.WHITE);
		boyBtn.setIcon(new ImageIcon("img/chooseYule.png"));
		selectPanel.add(boyBtn);
	}

}
