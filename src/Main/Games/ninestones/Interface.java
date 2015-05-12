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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import Main.Games.GamePanel;



@SuppressWarnings("serial")
public class Interface extends GamePanel {
	
	private Scale scale;
	private JTextArea rockSelectInfo, moneyTotal, weightInfo;
	private JPanel rockSelect, buttonPanel, leftScalebtnPanel, rightScalebtnPanel;
	private JButton btnWeigh, btnBuy, btnClear, btnAddLeft, btnAddRight;
	private GameLogic game;
	
	private ArrayList<JRadioButton> rbuttons;
	private ArrayList<Rock> rocks;
	private boolean notWon;
	
	public Interface() {
		
		this.game = new GameLogic();
		
		this.setBackground(Color.GRAY);
		this.setLayout(new GridBagLayout());
		
		constructLayoutComponents();
		
		/// Adding to frame
		GridBagConstraints c = new GridBagConstraints();
	    c.weightx = 1;
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.gridx = 0;
	    c.gridy = 0;
	    this.add(this.rockSelectInfo, c);
	 
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.5;
	    c.gridx = 2;
	    c.gridy = 0;
	    this.add(this.rockSelect, c);
	 
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 2;
	    c.gridy = 1;
	    this.add(scale, c);
	    
	    //scale wight info
	    c.fill = GridBagConstraints.HORIZONTAL;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 2;
	    c.gridy = 2;
	    this.add(weightInfo, c);
	    
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
	    this.add(this.moneyTotal, c);
	    
	    c.fill = GridBagConstraints.NONE;
	    c.anchor = GridBagConstraints.CENTER;
	    c.weightx = 0.0;
	    c.gridwidth = 1;
	    c.gridx = 0;
	    c.gridy = 1;
	    this.add(this.buttonPanel, c);
		
	    //
	    this.setPreferredSize(new Dimension(Main.Assets.Constants.SCREEN_WIDTH, Main.Assets.Constants.SCREEN_HEIGHT));
		this.setVisible(true);
	}
	
	private void constructLayoutComponents() {
		
		
		// constructing scale
		this.scale = new Scale();
		//scale.setPreferredSize(new Dimension(300, 200));
		
		// rock instructions
		this.rockSelectInfo = new JTextArea();
		this.rockSelectInfo.setPreferredSize(new Dimension(70,50));
		this.rockSelectInfo.setLineWrap(true);
		this.rockSelectInfo.setWrapStyleWord(true);
		this.rockSelectInfo.setEditable(false);
		this.rockSelectInfo.setBackground(Color.GRAY);
		this.rockSelectInfo.setText("Select rocks then add them to either side of the scale.\n"
						+"Hit weigh to find out which side is heavier.\n"
							+"Try to find the heaviest rock and buy it.");
		Font font = new Font("Verdana", Font.BOLD, 12);
		this.rockSelectInfo.setFont(font);
		this.rockSelectInfo.setForeground(Color.BLUE);
		
		//weight display
		this.weightInfo = new JTextArea();
		this.weightInfo.setPreferredSize(new Dimension(70,50));
		this.weightInfo.setLineWrap(true);
		this.weightInfo.setWrapStyleWord(true);
		this.weightInfo.setEditable(false);
		this.weightInfo.setBackground(Color.GRAY);
		
		this.weightInfo.setText(this.scale.getInfo());
		this.weightInfo.setFont(font);
		this.weightInfo.setForeground(Color.BLUE);
		
		// setting up rocks+gem -> random order
		rocks = new ArrayList<Rock>();
		rbuttons = new ArrayList<JRadioButton>();
		for (int i = 0; i < 8; i++) {
			rocks.add(new Rock());
			rocks.get(i).setPreferredSize(new Dimension(60, 60));
		}
		Gem gem = new Gem();
		gem.setPreferredSize(new Dimension(60, 60));
		rocks.add(gem);
		Collections.shuffle(rocks);
		
		// adding to panel
		this.rockSelect = new JPanel(new GridLayout(2, 9));
		this.rockSelect.setPreferredSize(new Dimension(200, 70));
		for (int i = 0; i < 9; i++) {
			rockSelect.add(rocks.get(i));
		}
		for (int i = 0; i < 9; i++) {
			rbuttons.add(new JRadioButton());
			rbuttons.get(i).setHorizontalAlignment(SwingConstants.CENTER);
			rockSelect.add(rbuttons.get(i));
		}
		
		rockSelect.setBackground(Color.GRAY);
		

		
		//construct scale buttons
		this.btnAddLeft = new JButton("Add rocks!");
		this.btnAddLeft.addActionListener(new Interface.ButtonListener());
		this.leftScalebtnPanel = new JPanel();
		this.leftScalebtnPanel.add(btnAddLeft);
		
		this.btnAddRight = new JButton("Add rocks!");
		this.btnAddRight.addActionListener(new Interface.ButtonListener());
		this.rightScalebtnPanel = new JPanel();
		this.rightScalebtnPanel.add(btnAddRight);
		
		// money instructions
		this.moneyTotal = new JTextArea();
		this.moneyTotal.setPreferredSize(new Dimension(25,50));
		this.moneyTotal.setLineWrap(true);
		this.moneyTotal.setWrapStyleWord(true);
		this.moneyTotal.setEditable(false);
		this.moneyTotal.setBackground(Color.GRAY);
		this.moneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
		this.moneyTotal.setFont(font);
		this.moneyTotal.setForeground(Color.BLUE);
		
		// button panel
		this.buttonPanel = new JPanel();
		this.buttonPanel.setLayout(new GridLayout(0,1));
		btnClear = new JButton("Clear Scale!");
		btnClear.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(btnClear);
		btnWeigh = new JButton("$9 WEIGH");
		btnWeigh.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(btnWeigh);
		btnBuy = new JButton("$9 PURCHASE");
		btnBuy.addActionListener(new Interface.ButtonListener());
		this.buttonPanel.add(btnBuy);
		this.buttonPanel.setPreferredSize(new Dimension(125, 100));
		this.buttonPanel.setBackground(Color.GRAY);
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			notWon = true;
			if(e.getSource().equals(btnWeigh)){
				getGameInstance().deductMoney();
				moneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
				
				getScaleInstance().updateScale(getGameInstance().weighRocks());
				weightInfo.setText(getScaleInstance().getInfo());
				
				for (int i = 0; i < 9; i++) {
					rbuttons.get(i).setVisible(true);
				}
				getGameInstance().clearScale();
			}else if(e.getSource().equals(btnBuy)){
				int z = 0;
				for (int i = 0; i < 9; i++){
					if(rbuttons.get(i).isSelected()){
						z++;
					}
				}
				if(z != 1){
					weightInfo.setText("Select exactly one rock for purchase!");
				}
				else if(z == 1){
					getGameInstance().deductMoney();
					moneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
					for (int i = 0; i < 9; i++){
						if(rbuttons.get(i).isSelected()){
							if(rocks.get(i).getWeight() == 55){
								notWon = false;
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
			                        	moneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
			                        	weightInfo.setText(getScaleInstance().getInfo());
			                        }
							}
							else{
								weightInfo.setText("You bought the wrong rock!");
							}
						}
				}
				}
			}else if(e.getSource().equals(btnAddLeft)){
				for (int i = 0; i < 9; i++) {
					if(rbuttons.get(i).isSelected()){
						rbuttons.get(i).setSelected(false);
						rbuttons.get(i).setVisible(false);
						getGameInstance().setWeightLeft(rocks.get(i).getWeight());
					}
				}
			}else if(e.getSource().equals(btnAddRight)){
				for (int i = 0; i < 9; i++) {
					if(rbuttons.get(i).isSelected()){
						rbuttons.get(i).setSelected(false);
						rbuttons.get(i).setVisible(false);
						getGameInstance().setWeightRight(rocks.get(i).getWeight());
					}
				}
			}else if(e.getSource().equals(btnClear)){
				for (int i = 0; i < 9; i++) {
					rbuttons.get(i).setVisible(true);
				}
				getGameInstance().clearScale();
			}
			if(getGameInstance().getMoney() == 0 && notWon){
				System.out.println("You lost!");
				 String str = "You lost!\nWould you like to play again?";
                   if (JOptionPane.showConfirmDialog(null, str, "GAME RESULT",
                           JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                	   for (int i = 0; i < 9; i++) {
       					rbuttons.get(i).setVisible(true);
       					rbuttons.get(i).setSelected(false);
       				}
                   	Collections.shuffle(rocks);
                   	getGameInstance().clearScale();
                   	getGameInstance().resetMoney();
                   	getScaleInstance().resetScale();
                   	moneyTotal.setText("MONEY LEFT: " + DecimalFormat.getCurrencyInstance().format(game.getMoney()));
                   	weightInfo.setText(getScaleInstance().getInfo());
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
