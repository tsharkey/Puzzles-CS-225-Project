package Main.Games.ninestones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Main.Games.GamePanel;


@SuppressWarnings("serial")
public class Interface extends GamePanel {
	
	private Scale scale;
	private JTextArea rockSelectInfo;
	private JPanel rockSelect, buttonPanel;
	private JButton btnWeigh, btnBuy, btClearScale;
	
	//
	private JLabel moneyTotal, weighStatus, gameInstruction;
	private JLabel scaleLeftCount, scaleRightCount;
	
	
	private GameLogic game;
	
	public Interface() {
		
		this.game = new GameLogic();
		
		this.setBackground(Color.BLUE);
		this.setLayout(new GridBagLayout());
		
		constructLayoutComponents();
		
		/// Adding to frame
		GridBagConstraints c = new GridBagConstraints();
	    c.weightx = 0.5;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    this.add(this.rockSelectInfo, c);
	 
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 1;
	    c.gridy = 0;
	    this.add(this.rockSelect, c);
	 
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.0;
	    c.gridwidth = 3;
	    c.gridx = 1;
	    c.gridy = 1;
	    this.add(scale, c);
	    
	    c.fill = GridBagConstraints.NONE;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    this.add(this.buttonPanel, c);
		////
		
		this.setSize(Main.Assets.Constants.SCREEN_WIDTH,
				Main.Assets.Constants.SCREEN_HEIGHT);
		this.setVisible(true);
	}
	
	private void constructLayoutComponents() {
		// rock instructions
		this.rockSelectInfo = new JTextArea();
		this.rockSelectInfo.setPreferredSize(new Dimension(70,50));
		this.rockSelectInfo.setLineWrap(true);
		this.rockSelectInfo.setWrapStyleWord(true);
		this.rockSelectInfo.setEditable(false);
		this.rockSelectInfo.setBackground(Color.BLUE);
		this.rockSelectInfo.setText("Drag rocks to the scale to weight them:");
		Font font = new Font("Verdana", Font.BOLD, 12);
		this.rockSelectInfo.setFont(font);
		this.rockSelectInfo.setForeground(Color.RED);
		
		// setting up rocks+gem -> random order
		ArrayList<Rock> rocks = new ArrayList<Rock>();
		for (int i = 0; i < 8; i++) {
			rocks.add(new Rock());
			rocks.get(i).setPreferredSize(new Dimension(60, 60));
		}
		Gem gem = new Gem();
		gem.setPreferredSize(new Dimension(60, 60));
		rocks.add(gem);
		Collections.shuffle(rocks);
		
		// adding to panel
		this.rockSelect = new JPanel();
		this.rockSelect.setPreferredSize(new Dimension(600, 70));
		for (int i = 0; i < 9; i++) {
			rockSelect.add(rocks.get(i));
		}
		rockSelect.setBackground(Color.BLUE);
		
		// constructing scale
		this.scale = new Scale();
		scale.setPreferredSize(new Dimension(800, 600));
		
		// button panel
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(0,1));
		btnWeigh = new JButton("$9 WEIGH");
		btnWeigh.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(btnWeigh);
		btnBuy = new JButton("$9 PURCHASE");
		btnBuy.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(btnBuy);
		btClearScale = new JButton("CLEAR SCALE");
		btClearScale.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(btClearScale);
		this.buttonPanel.setPreferredSize(new Dimension(125, 100));
		this.buttonPanel.setBackground(Color.BLUE);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Inner class that allows for print listening.
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//
		}
	}
}