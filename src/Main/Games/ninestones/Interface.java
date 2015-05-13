/**
 * Interface.java
 * 
 * Main class that handles constructing GUI and interfacing with other
 * game classes.
 * 
 */

package Main.Games.ninestones;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import Main.Games.GamePanel;

@SuppressWarnings("serial")
public class Interface extends GamePanel {
	// game instance variables
	private Scale scale;
	private GameLogic game;
	private boolean hasWon;
	private ArrayList<Rock> rocks; // 8 rocks + 1 gem
	// UI variables
	private JTextArea tfRockSelectInfo, tfMoneyTotal, tfGameInfo;
	private JButton bnWeigh, bnBuy, bnClear, bnAddLeft, bnAddRight;
	private Color bkgColor = Color.GRAY;
	
	private JTextPane tpWeightInfo;
	
	
	
	private JPanel pInfoPanel1, pRockSelect, pTop, pScale;
	
	private JPanel  buttonPanel, leftScalebtnPanel, rightScalebtnPanel;
	
	private ArrayList<JRadioButton> rbuttons;
	private ArrayList<JTextPane> rLetters;
	
	/**
	 * Default constructor
	 */
	
	public Interface() {
		// initializing game variables
		this.game = new GameLogic();
		this.scale = new Scale();
		this.hasWon = false;
		// setting up rocks+gem -> random order
		this.rocks = new ArrayList<Rock>();
		for (int i = 0; i < 8; i++) {
			this.rocks.add(new Rock());
			rocks.get(i).setPreferredSize(new Dimension(20, 20));
		}
		Gem gem = new Gem();
		gem.setPreferredSize(new Dimension(20, 20));
		this.rocks.add(gem);
		Collections.shuffle(this.rocks);
		
		// initializing GUI components
		constructLayoutComponents();
		
		// constructing layout and adding to frame
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(Main.Assets.Constants.SCREEN_WIDTH, Main.Assets.Constants.SCREEN_HEIGHT));
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		// construct scale panel
		pScale.add(this.scale, BorderLayout.NORTH);
		pScale.add(this.tpWeightInfo, BorderLayout.CENTER);
		pScale.setBackground(bkgColor);
		JPanel scaleButton = new JPanel(new BorderLayout());
		scaleButton.add(bnAddLeft, BorderLayout.WEST);
		scaleButton.add(bnAddRight, BorderLayout.EAST);
		scaleButton.setBackground(bkgColor);
		pScale.add(scaleButton, BorderLayout.SOUTH);
		JPanel midPanel = new JPanel(new BorderLayout());
		midPanel.add(buttonPanel, BorderLayout.WEST);
		midPanel.add(pScale, BorderLayout.CENTER);
		midPanel.setBackground(bkgColor);
		
		//money and info
		JPanel moneyInfo = new JPanel(new GridLayout(2,0));
		moneyInfo.add(tfRockSelectInfo);
		moneyInfo.add(tfMoneyTotal);
		moneyInfo.setAlignmentY(BOTTOM_ALIGNMENT);
		
		//construct rock panel
		//pRockSelect
		pTop = new JPanel(new FlowLayout());
		pTop.add(moneyInfo);
		pTop.add(pRockSelect);
		pTop.setBackground(bkgColor);
		
		
		// add scale to frame
		//GridBagConstraints c = new GridBagConstraints();
		//c.gridy = 1;
		//c = new GridBagConstraints();
		this.add(midPanel, BorderLayout.CENTER);
		
		//rocks
		//c.gridy = 0;
		this.add(pTop, BorderLayout.NORTH);
		
		//money
		//c.gridy = 3;
		this.add(pInfoPanel1, BorderLayout.SOUTH);
		
		/*
	    c.weightx = 1;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    this.add(this.tfRockSelectInfo, c);
	 
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 2;
	    c.gridy = 0;
	    this.add(this.rockSelect, c);
	    */

	    /*
	    //scale wight info
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 2;
	    c.gridy = 2;
	    this.add(tfWeightInfo, c);
	    
	    //scale left
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 1;
	    c.gridy = 2;
	    this.add(leftScalebtnPanel, c);
	    //scale right
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 3;
	    c.gridy = 2;
	    this.add(rightScalebtnPanel, c);
	    
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.anchor = GridBagConstraints.NORTH;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    this.add(this.tfMoneyTotal, c);
	    
	    c.fill = GridBagConstraints.NONE;
	    c.anchor = GridBagConstraints.CENTER;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    this.add(this.buttonPanel, c);
		*/
	    //
	    
	}
	
	private void constructLayoutComponents() {
		this.pScale = new JPanel(new BorderLayout());
		
		this.pRockSelect = new JPanel(new GridLayout(3,9));
		
		this.pInfoPanel1 = new JPanel();
		
		this.tfGameInfo = new JTextArea();
		this.tfGameInfo.setText("With 18 dollars you can buy just two measurements with 9 dollars left over to buy one of the nine stones. What measurements "
									+ "can you make to identify the valuable gem with absolute certainty?");
		this.tfGameInfo.setLineWrap(true);
		this.tfGameInfo.setWrapStyleWord(true);
		this.tfGameInfo.setEditable(false);
		this.tfGameInfo.setBackground(Color.GRAY);
		this.tfGameInfo.setForeground(Color.BLUE);
		Border b = BorderFactory.createLineBorder(Color.BLACK);
		this.tfGameInfo.setBorder(b);
		Font font3 = new Font("Verdana", Font.BOLD, 12);
		this.tfGameInfo.setFont(font3);
		this.tfGameInfo.setPreferredSize(new Dimension(700,35));
		
		this.pInfoPanel1.add(tfGameInfo);
		this.pInfoPanel1.setBackground(bkgColor);
		
		// constructing scale
		
		//scale.setPreferredSize(new Dimension(300, 200));
		
		// rock instructions
		this.tfRockSelectInfo = new JTextArea();
		this.tfRockSelectInfo.setPreferredSize(new Dimension(150,150));
		this.tfRockSelectInfo.setLineWrap(true);
		this.tfRockSelectInfo.setWrapStyleWord(true);
		this.tfRockSelectInfo.setEditable(false);
		this.tfRockSelectInfo.setBackground(Color.GRAY);
		this.tfRockSelectInfo.setText("Select stones then add them to either side of the scale.\n"
						+"Hit weigh to find out which side is heavier.\n"
							+"Try to find the heaviest rock and buy it.");
		Font font = new Font("Verdana", Font.BOLD, 12);
		this.tfRockSelectInfo.setFont(font);
		this.tfRockSelectInfo.setBorder(b);
		this.tfRockSelectInfo.setForeground(Color.BLUE);
		
		// weight display
		tpWeightInfo = new JTextPane();
		SimpleAttributeSet attribs = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs , StyleConstants.ALIGN_CENTER);
		StyleConstants.setBold(attribs, true);
		tpWeightInfo.setParagraphAttributes(attribs,true);
		tpWeightInfo.setText(this.scale.getInfo());
		//tpWeightInfo.setFont(font);
		tpWeightInfo.setBackground(Color.RED);
		
		
		rbuttons = new ArrayList<JRadioButton>();
		rLetters = new ArrayList<JTextPane>();
		
		// adding to panel
		this.pRockSelect.setPreferredSize(new Dimension(600, 200));
		for (int i = 0; i < 9; i++) {
			pRockSelect.add(rocks.get(i));
		}
		for (int i = 0; i < 9; i++) {
			rbuttons.add(new JRadioButton());
			rbuttons.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			rbuttons.get(i).setBackground(bkgColor);
			pRockSelect.add(rbuttons.get(i));
		}
		
		SimpleAttributeSet attribs2 = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribs2 , StyleConstants.ALIGN_CENTER);
		StyleConstants.setBold(attribs2, true);
		StyleConstants.setFontSize(attribs2, 36);
		
		for(int i = 0; i<9; i++){
			rLetters.add(new JTextPane());
			rLetters.get(i).setParagraphAttributes(attribs2,true);
			rLetters.get(i).setForeground(Color.BLACK);
			rLetters.get(i).setEditable(false);
			rLetters.get(i).setBackground(Color.RED);
			pRockSelect.add(rLetters.get(i));
		}
		
		pRockSelect.setBackground(Color.GRAY);
		

		
		//construct scale buttons
		this.bnAddLeft = new JButton("Add rocks!");
		this.bnAddLeft.addActionListener(new Interface.ButtonListener());
		this.leftScalebtnPanel = new JPanel();
		this.leftScalebtnPanel.add(bnAddLeft);
		
		this.bnAddRight = new JButton("Add rocks!");
		this.bnAddRight.addActionListener(new Interface.ButtonListener());
		this.rightScalebtnPanel = new JPanel();
		this.rightScalebtnPanel.add(bnAddRight);
		
		// money instructions
		
		Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
		fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font boldUnderline = new Font("Serif",Font.BOLD, 14).deriveFont(fontAttributes);
		
		this.tfMoneyTotal = new JTextArea();
		this.tfMoneyTotal.setPreferredSize(new Dimension(25,50));
		this.tfMoneyTotal.setLineWrap(true);
		this.tfMoneyTotal.setWrapStyleWord(true);
		this.tfMoneyTotal.setEditable(false);
		this.tfMoneyTotal.setBackground(Color.GRAY);
		this.tfMoneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
		this.tfMoneyTotal.setFont(boldUnderline);
		this.tfMoneyTotal.setForeground(Color.GREEN);
		
		
		// button panel
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(0,1));
		bnClear = new JButton("Clear Scale!");
		bnClear.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(bnClear);
		bnWeigh = new JButton("$9 WEIGH");
		bnWeigh.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(bnWeigh);
		bnBuy = new JButton("$9 PURCHASE");
		bnBuy.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(bnBuy);
		this.buttonPanel.setPreferredSize(new Dimension(125, 100));
		this.buttonPanel.setBackground(Color.GRAY);
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			hasWon = true;
			if(e.getSource().equals(bnWeigh)){
				getGameInstance().deductMoney();
				tfMoneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
				
				getScaleInstance().updateScale(getGameInstance().weighRocks());
				tpWeightInfo.setText(getScaleInstance().getInfo());
				
				for (int i = 0; i < 9; i++) {
					rbuttons.get(i).setVisible(true);
					rLetters.get(i).setText("");
				}
				getGameInstance().clearScale();
			}else if(e.getSource().equals(bnBuy)){
				int z = 0;
				for (int i = 0; i < 9; i++){
					if(rbuttons.get(i).isSelected()){
						z++;
					}
				}
				if(z != 1){
					tpWeightInfo.setText("Select exactly one rock for purchase!");
				}
				else if(z == 1){
					getGameInstance().deductMoney();
					tfMoneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
					for (int i = 0; i < 9; i++){
						if(rbuttons.get(i).isSelected()){
							if(rocks.get(i).getWeight() == 55){
								hasWon = false;
								rbuttons.get(i).setSelected(false);
								System.out.println("You won!");
								 String str = "You won!\nWould you like to play again?";
			                        if (JOptionPane.showConfirmDialog(null, str, "GAME RESULT",
			                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			                        	for (int q = 0; q < 9; q++) {
			            					rbuttons.get(q).setVisible(true);
			            					rbuttons.get(q).setSelected(false);
			            				}
			                        	Collections.shuffle(rocks);
			                        	getGameInstance().clearScale();
			                        	getGameInstance().resetMoney();
			                        	getScaleInstance().resetScale();
			                        	tfMoneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
			                        	tpWeightInfo.setText(getScaleInstance().getInfo());
			                        }
			                        else{
			                        	System.exit(0);
			                        }
							}
							else{
								tpWeightInfo.setText("You bought the wrong rock!");
							}
						}
				}
				}
			}else if(e.getSource().equals(bnAddLeft)){
				for (int i = 0; i < 9; i++) {
					if(rbuttons.get(i).isSelected()){
						rbuttons.get(i).setSelected(false);
						rbuttons.get(i).setVisible(false);
						rLetters.get(i).setText("L");
						getGameInstance().setWeightLeft(rocks.get(i).getWeight());
					}
				}
			}else if(e.getSource().equals(bnAddRight)){
				for (int i = 0; i < 9; i++) {
					if(rbuttons.get(i).isSelected()){
						rbuttons.get(i).setSelected(false);
						rbuttons.get(i).setVisible(false);
						rLetters.get(i).setText("R");
						getGameInstance().setWeightRight(rocks.get(i).getWeight());
					}
				}
			}else if(e.getSource().equals(bnClear)){
				for (int i = 0; i < 9; i++) {
					rbuttons.get(i).setVisible(true);
					rLetters.get(i).setText("");
				}
				getGameInstance().clearScale();
			}
			if(getGameInstance().getMoney() == 0 && hasWon){
				System.out.println("You lost!");
				 String str = "You lost!\nWould you like to play again?";
                   if (JOptionPane.showConfirmDialog(null, str, "GAME RESULT",
                           JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                	   for (int i = 0; i < 9; i++) {
       					rbuttons.get(i).setVisible(true);
       					rbuttons.get(i).setSelected(false);
       					rLetters.get(i).setText("");
       				}
                   	Collections.shuffle(rocks);
                   	getGameInstance().clearScale();
                   	getGameInstance().resetMoney();
                   	getScaleInstance().resetScale();
                   	tfMoneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
                   	tpWeightInfo.setText(getScaleInstance().getInfo());
                   }
                   else{
                	   System.exit(0);
                   }
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {}
	
	GameLogic getGameInstance() { return this.game; }
	Scale getScaleInstance() { return this.scale; }
}
