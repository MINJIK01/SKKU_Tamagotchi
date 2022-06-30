import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameStart extends JFrame {

	private JPanel contentPane;
	private JPanel actionList;
	private JButton foodBtn; // if Button pressed, character gets energy
	private JButton sleepBtn; // if Button pressed, character gets energy
	private JButton playBtn; // if Button pressed, character looses energy and gets exp
	private JButton trainBtn; // if Button pressed, character looses energy and gets exp
	private JPanel statusPanel;
	private JProgressBar energyBar; // show how much energy that character has
	private JProgressBar expBar; // show how much exp that character has
	private JLabel energyLbl;
	private JLabel expLbl;
	private JLabel levelLbl; // show what level character is
	private JPanel infoPanel;
	private JButton infoBtn; // if Button pressed, show character's current info
	private JPanel endPanel;
	private JButton exitBtn;// if Button pressed, game end
	private JLabel grassLbl;
	private JPanel centerPanel;
	private JLabel characterLbl;
	private JLabel schoolLogo;
	private JLabel leafLbl;

	private int character = -1; // determine which character user chooses
	private JTextArea status; // show the status and dialog of character
	private int levelUpTrue = 0; // determine if level up or not
	
	private int keep = 1; // determine whether quit or not. 1 means quit and 0 means coninue

	// get set function
	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}

	public int getLevelUpTrue() {
		return levelUpTrue;
	}

	public void setLevelUpTrue(int levelUpTrue) {
		this.levelUpTrue = levelUpTrue;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameStart frame = new GameStart();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Character tama = new Character(); //constructor of character;

	/**
	 * Create the frame.
	 */
	public GameStart() {

		// get the character type through File I/O
		FileInputStream fileObject;
		try {
			fileObject = new FileInputStream("data.txt");

			Scanner x = new Scanner(fileObject);

			while (x.hasNext()) {
				String sex = x.nextLine();
				character = Integer.parseInt(sex);
			}
			x.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Polymorphism
		if (getCharacter() == 0) {
			tama = new Girl();
		} else if (getCharacter() == 1) {
			tama = new Boy();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 675);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// make top menu bannar
		actionList = new JPanel();
		actionList.setBackground(new Color(0, 90, 0));
		contentPane.add(actionList, BorderLayout.NORTH);
		actionList.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// make food button
		foodBtn = new JButton("FOOD");
		foodBtn.setFont(new Font("Elephant", Font.PLAIN, 12));
		foodBtn.setForeground(new Color(204, 204, 51));
		foodBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start(25);
			}
		});
		foodBtn.setBackground(new Color(0, 90, 0));
		foodBtn.setIcon(new ImageIcon("img\\rice.png"));
		foodBtn.setBounds(10, 10, 10, 30);
		foodBtn.setBorder(new RoundedBorder(10));
		actionList.add(foodBtn);

		// make sleep button
		sleepBtn = new JButton("SLEEP");
		sleepBtn.setFont(new Font("Elephant", Font.PLAIN, 12));
		sleepBtn.setForeground(new Color(204, 204, 51));
		sleepBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start(10);
			}
		});
		sleepBtn.setBackground(new Color(0, 90, 0));
		sleepBtn.setIcon(new ImageIcon("img\\sleep.png"));
		sleepBtn.setBounds(10, 10, 30, 30);
		sleepBtn.setBorder(new RoundedBorder(10));
		actionList.add(sleepBtn);

		// make play button
		playBtn = new JButton("PLAY");
		playBtn.setFont(new Font("Elephant", Font.PLAIN, 12));
		playBtn.setForeground(new Color(204, 204, 51));
		playBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start(30);
			}
		});
		playBtn.setBackground(new Color(0, 90, 0));
		playBtn.setIcon(new ImageIcon("img\\play.png"));
		playBtn.setBounds(10, 10, 30, 30);
		playBtn.setBorder(new RoundedBorder(10));
		actionList.add(playBtn);

		// make train button
		trainBtn = new JButton("STUDY");
		trainBtn.setFont(new Font("Elephant", Font.PLAIN, 12));
		trainBtn.setForeground(new Color(204, 204, 51));
		trainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start(20);
			}
		});
		trainBtn.setBackground(new Color(0, 90, 0));
		trainBtn.setIcon(new ImageIcon("img\\train.png"));
		trainBtn.setBounds(10, 10, 30, 30);
		trainBtn.setBorder(new RoundedBorder(10));
		actionList.add(trainBtn);

		// make level lebel
		levelLbl = new JLabel("LEVEL " + tama.getLevel());
		levelLbl.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 30));
		levelLbl.setBackground(new Color(0, 90, 0));
		levelLbl.setForeground(new Color(204, 204, 51));
		levelLbl.setOpaque(true);
		levelLbl.setBorder(new LineBorder(new Color(204, 204, 153), 3, true));
		actionList.add(levelLbl);

		// make left status banner
		statusPanel = new JPanel();
		statusPanel.setBackground(new Color(0, 90, 0));
		contentPane.add(statusPanel, BorderLayout.WEST);
		GridBagLayout gbl_statusPanel = new GridBagLayout();
		gbl_statusPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_statusPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_statusPanel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_statusPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		statusPanel.setLayout(gbl_statusPanel);

		// make energy bar
		energyBar = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		energyBar.setForeground(new Color(204, 204, 0));
		energyBar.setBackground(new Color(0, 90, 0));
		GridBagConstraints gbc_energyBar = new GridBagConstraints();
		gbc_energyBar.gridheight = 16;
		gbc_energyBar.insets = new Insets(0, 0, 5, 5);
		gbc_energyBar.fill = GridBagConstraints.BOTH;
		gbc_energyBar.gridx = 0;
		gbc_energyBar.gridy = 0;
		energyBar.setValue(tama.getEnergy()); // set initial value
		statusPanel.add(energyBar, gbc_energyBar);

		// make exp bar
		expBar = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
		expBar.setForeground(new Color(204, 204, 51));
		expBar.setBackground(new Color(0, 90, 0));
		GridBagConstraints gbc_expBar = new GridBagConstraints();
		gbc_expBar.fill = GridBagConstraints.BOTH;
		gbc_expBar.gridheight = 16;
		gbc_expBar.insets = new Insets(0, 0, 5, 0);
		gbc_expBar.gridx = 1;
		gbc_expBar.gridy = 0;
		expBar.setValue(tama.getExp()); // set initial value
		statusPanel.add(expBar, gbc_expBar);

		// make energy label
		energyLbl = new JLabel("Energy");
		energyLbl.setForeground(new Color(204, 204, 102));
		energyLbl.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		energyLbl.setBackground(Color.WHITE);
		GridBagConstraints gbc_energyLbl = new GridBagConstraints();
		gbc_energyLbl.gridheight = 4;
		gbc_energyLbl.insets = new Insets(0, 0, 0, 5);
		gbc_energyLbl.gridx = 0;
		gbc_energyLbl.gridy = 16;
		statusPanel.add(energyLbl, gbc_energyLbl);

		// make exp label
		expLbl = new JLabel("EXP");
		expLbl.setFont(new Font("Century Schoolbook", Font.BOLD, 12));
		expLbl.setForeground(new Color(204, 204, 102));
		GridBagConstraints gbc_expLbl = new GridBagConstraints();
		gbc_expLbl.gridheight = 4;
		gbc_expLbl.insets = new Insets(0, 0, 5, 0);
		gbc_expLbl.gridx = 1;
		gbc_expLbl.gridy = 16;
		statusPanel.add(expLbl, gbc_expLbl);

		// make right banner
		infoPanel = new JPanel();
		infoPanel.setBackground(new Color(0, 90, 0));
		contentPane.add(infoPanel, BorderLayout.EAST);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] { 69, 0 };
		gbl_infoPanel.rowHeights = new int[] { 29, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_infoPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_infoPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		infoPanel.setLayout(gbl_infoPanel);

		// make information button
		infoBtn = new JButton(" Information ");
		infoBtn.setFont(new Font("Elephant", Font.BOLD, 12));
		infoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Information info = new Information(tama.getEnergy(), tama.getExp(), tama.getLevel());
				info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				info.setVisible(true);
			}
		});
		infoBtn.setForeground(new Color(204, 204, 51));
		infoBtn.setBackground(new Color(0, 90, 0));
		infoBtn.setIcon(new ImageIcon("img\\info.png"));
		infoBtn.setBounds(10, 10, 30, 30);
		infoBtn.setBorder(new RoundedBorder(5));
		GridBagConstraints gbc_infoBtn = new GridBagConstraints();
		gbc_infoBtn.insets = new Insets(0, 0, 5, 0);
		gbc_infoBtn.anchor = GridBagConstraints.NORTHWEST;
		gbc_infoBtn.gridx = 0;
		gbc_infoBtn.gridy = 0;
		infoPanel.add(infoBtn, gbc_infoBtn);

		// make status area
		status = new JTextArea();
		status.setBackground(Color.WHITE);
		if (getCharacter() == 0) { // if character is 0 print below text
			status.setText("명륜이 상태\n\n안녕~ 반가워~\n앞으로 잘부탁해~\n");
		} else if (getCharacter() == 1) { // if character is 1 print below text
			status.setText("율전이 상태\n\n안녕~ 반가워~\n앞으로 잘부탁해~\n");
		}
		GridBagConstraints gbc_status = new GridBagConstraints();
		gbc_status.gridheight = 17;
		gbc_status.fill = GridBagConstraints.BOTH;
		gbc_status.gridx = 0;
		gbc_status.gridy = 1;
		status.setEditable(false); // make text area uneditabel
		JScrollPane scrollPane = new JScrollPane(status);
		infoPanel.add(scrollPane, gbc_status);

		// make bottom banner
		endPanel = new JPanel();
		endPanel.setBackground(new Color(0, 90, 0));
		contentPane.add(endPanel, BorderLayout.SOUTH);
		endPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		

		//make schoolLogo label
		schoolLogo = new JLabel("");
		schoolLogo.setIcon(new ImageIcon("img\\schoolLogo.png"));
		schoolLogo.setBackground(new Color(0, 90, 0));
		endPanel.add(schoolLogo);

		/**
		//make leaf label
		leafLbl = new JLabel("");
		leafLbl.setIcon(new ImageIcon("img/leaf.png"));
		endPanel.add(leafLbl);*/

		// make end button
		exitBtn = new JButton("EXIT");
		exitBtn.setFont(new Font("Elephant", Font.BOLD, 12));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				while(keep == 1) {
					int result = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Confirm",	JOptionPane.YES_NO_OPTION);
					try {
					if(result == JOptionPane.CLOSED_OPTION)
						throw new wholeException("ERROR");
					else if(result == JOptionPane.YES_OPTION)
						System.exit(1);
					else
						keep = 0;
					}catch(wholeException eror) {
						JOptionPane.showMessageDialog(null, "You have to choose answer", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						
					}
				}
				keep = 1;
			}
		});
		
		exitBtn.setHorizontalAlignment(SwingConstants.LEFT);
		exitBtn.setForeground(new Color(204, 204, 51));
		exitBtn.setBackground(new Color(0, 90, 0));
		exitBtn.setBounds(10, 10, 30, 30);
		exitBtn.setBorder(new RoundedBorder(5));
		endPanel.add(exitBtn);

		// make center banner
		centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		contentPane.add(centerPanel, BorderLayout.CENTER);

		// make character label
		characterLbl = new JLabel("");
		characterLbl.setHorizontalAlignment(SwingConstants.CENTER);
		characterLbl.setBackground(Color.WHITE);
		if (getCharacter() == 0) { // if character is 0 show Myeongnyun
			characterLbl.setIcon(new ImageIcon("img\\myung.png"));
		} else if (getCharacter() == 1) { // if character is 0 show Yuljeon
			characterLbl.setIcon(new ImageIcon("img\\yule.png"));
		}
		centerPanel.add(characterLbl, BorderLayout.CENTER);
	}

	private class Girl extends Character {

		private String name = "myung";

		public String getName() {
			return name;
		}

		private int energy = 30; // re - set energy different from Character

		// get set fucntion
		public int getEnergy() {
			return energy;
		}

		public void setEnergy(int energy) {
			this.energy = energy;
		}

		// overide function & if exp is greater than 80 then change the lavel
		public void levelUp() {
			if (getExp() > 80) {
				setExp(getExp() - 80); // subtract 80 at the exp
				expBar.setValue(getExp()); // re-set the exp progressbar
				setLevel(getLevel() + 1); // set level
				characterLbl.setIcon(new ImageIcon("img\\" + getName() + "LevelUp.png")); // show the level up image
				levelLbl.setText("LEVEL " + getLevel()); // set the level label
				setLevelUpTrue(1); // change the level change status
			}

		}

		public void graduate() {
			characterLbl.setIcon(new ImageIcon("img\\" + getName() + "Graduate.png")); // show the graduate image
		}

		public void expel() {
			characterLbl.setIcon(new ImageIcon("img\\" + getName() + "Expel.png")); // show the expel image
		}
	}

	private class Boy extends Character {

		private String name = "yule";

		public String getName() {
			return name;
		}

		// overide function & if exp is greater than 80 then change the lavel
		public void levelUp() {
			if (getExp() > 80) {
				setExp(getExp() - 80); // subtract 80 at the exp
				expBar.setValue(getExp()); // re-set the exp progressbar
				setLevel(getLevel() + 1); // set level
				characterLbl.setIcon(new ImageIcon("img\\" + getName() + "LevelUp.png")); // show the level up image
				levelLbl.setText("LEVEL " + getLevel()); // set the level label
				setLevelUpTrue(1); // change the level change status

			}

		}

		public void graduate() {
			characterLbl.setIcon(new ImageIcon("img\\" + getName() + "Graduate.png")); // show the graduate image
		}

		public void expel() {
			characterLbl.setIcon(new ImageIcon("img\\" + getName() + "Expel.png")); // show the expel image
		}
	}

	// class for round border of button
	private static class RoundedBorder implements Border {

		private int radius;

		RoundedBorder(int radius) {
			this.radius = radius;
		}

		@Override
		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		}

	}

	// increase and decrease energy or exp state + show their change throw progress
	// bar
	private void start(int eventCase) {

		SwingWorker worker = new SwingWorker<Integer, String>() {

			@Override
			// Note: do not update the GUI from within doInBackground.
			protected Integer doInBackground() throws Exception {
				// Simulate useful work

				// make buttons blocked
				foodBtn.setEnabled(false);
				sleepBtn.setEnabled(false);
				playBtn.setEnabled(false);
				trainBtn.setEnabled(false);

				if (eventCase == 10 || eventCase == 25) {
					for (int i = 1; i < eventCase + 1; i++) {
						Thread.sleep(50);
						tama.setEnergy(tama.getEnergy() + 1);

						if (eventCase == 10) {
							characterLbl.setIcon(new ImageIcon("img\\" + tama.getName() + "Sleep.png"));
							publish("잠자는 중입니다...\n로딩시간 : " + i); // show message of sleep
						} else if (eventCase == 25) {
							characterLbl.setIcon(new ImageIcon("img\\" + tama.getName() + "Food.png"));
							publish("밥을 먹는 중입니다...\n로딩시간 : " + i); // show message of food
						}

						energyBar.setValue(tama.getEnergy());
					}
				} else {
					for (int i = 1; i < eventCase + 1; i++) {
						if (tama.getExp() > 85 || tama.getEnergy() < 0) {
							break;
						}
						Thread.sleep(50);
						tama.setEnergy(tama.getEnergy() - 1);
						tama.setExp(tama.getExp() + 1);

						if (eventCase == 20) {
							characterLbl.setIcon(new ImageIcon("img\\" + tama.getName() + "Train.png"));
							publish("훈련받는 중입니다...\n로딩시간 : " + i); // show message of train
						} else if (eventCase == 30) {
							characterLbl.setIcon(new ImageIcon("img\\" + tama.getName() + "Play.png"));
							publish("노는 중입니다...\n로딩시간 : " + i); // show message of play
						}

						energyBar.setValue(tama.getEnergy());

						expBar.setValue(tama.getExp());
					}
				}

				// check if character level up or not
				tama.levelUp();
				if (getLevelUpTrue() == 1) // if character level up then show dialog message
				{
					JOptionPane.showMessageDialog(null, "Congraturation!!! you LEVEL UP!!!!", "level up",
							JOptionPane.INFORMATION_MESSAGE);
					Thread.sleep(500);
					setLevelUpTrue(0); // re-set level change status
				}

				if (tama.getLevel() > 4) {
					tama.graduate();
					JOptionPane.showMessageDialog(null,
							"Congratulation!!! Your charater graduates from Sungkyunkwan!!!\nGood Job!!!", "Graduate",
							JOptionPane.INFORMATION_MESSAGE);
					Thread.sleep(1000);
					System.exit(1);
				}

				// if energy less than 0 then quit the system
				if (tama.getEnergy() < 0) {
					tama.expel();
					JOptionPane.showMessageDialog(null, "Your charater is expelled from Sungkyunkwan..Sorry", "Expel",
							JOptionPane.INFORMATION_MESSAGE);
					Thread.sleep(1000);
					System.exit(1);
				}

				return 0;
			}

			String all = status.getText();

			@Override
			// update the GUI here.
			protected void process(List<String> chunks) {
				String componant = chunks.get(chunks.size() - 1); // get the string
				String[] contentsList = new String[eventCase];
				contentsList[chunks.size() - 1] = componant; // save one component into the array
				for (int i = 0; i < chunks.size(); i++) // update array through the loop
				{
					all = all + contentsList[i] + "\n";
				}
				status.setText(all); // set the text at the text Area

			}

			@Override
			// this is the case of thread finishes and update GUI here.
			protected void done() {
				all = status.getText();
				all = all + "끝!\n활동을 마치고\n돌아왔어요!\n";
				status.setText(all); // set the text at the text Area
				characterLbl.setIcon(new ImageIcon("img\\" + tama.getName() + ".png"));

				// make buttons enable
				foodBtn.setEnabled(true);
				sleepBtn.setEnabled(true);
				playBtn.setEnabled(true);
				trainBtn.setEnabled(true);
			}
		};
		worker.execute();
	}

}
