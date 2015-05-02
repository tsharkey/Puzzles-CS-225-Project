package Main.Games.ninestones;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Games.GamePanel;


@SuppressWarnings("serial")
public class Interface extends GamePanel {
	
	private ArrayList<Rock> rocks;
	private Gem gem;
	private JLabel rockDragInfo, moneyTotal, weighStatus, gameInstruction;
	private JLabel scaleLeftCount, scaleRightCount;
	private JButton btnWeigh, btnBuy, btClearScale;
	//private BufferedImage scale;
	private JPanel gamePanel;
	private Scale scale;
	private GameLogic game;
	
	public Interface() {
		this.game = new GameLogic();
		
		this.setBackground(Color.BLUE);
		
		setLayout(new BorderLayout());
		
		this.gamePanel = new JPanel();
		// randomizing order of rocks and gold
		ArrayList<Point> order = new ArrayList<Point>();
		for (int i = 0, x = 90; i < 9; i++, x+=70) {
			order.add(new Point(x, 50));
		}
		Collections.shuffle(order);
		
	//	gamePanel.setSize(Main.Assets.Constants.SCREEN_WIDTH,
	//			Main.Assets.Constants.SCREEN_HEIGHT);
		
		this.rocks = new ArrayList<Rock>();
		for (int i = 0; i < 8; i++) {
			rocks.add(new Rock(order.get(i)));
			rocks.get(i).setPreferredSize(new Dimension(60, 60));
			gamePanel.add(rocks.get(i));
		}
		this.gem = new Gem(order.get(8));
		gem.setPreferredSize(new Dimension(60, 60));
		gamePanel.add(gem);
		
		this.scale = new Scale();
		scale.setPreferredSize(new Dimension(800, 600));
		//scale.setLocation(0, 0);
		this.add(scale, BorderLayout.SOUTH);
		
		JLabel timerLabel = new JLabel("Time Remaining 300 seconds");
		timerLabel.setLocation(0, 0);
		timerLabel.setPreferredSize(new Dimension(10,0));
		System.out.println(timerLabel.getPreferredSize().width);
		System.out.println(timerLabel.getPreferredSize().height);

		gamePanel.add(timerLabel);
		gamePanel.setBackground(Color.BLUE);
		gamePanel.setVisible(true);
		
		this.add(gamePanel, BorderLayout.NORTH);
		
		this.setSize(Main.Assets.Constants.SCREEN_WIDTH,
				Main.Assets.Constants.SCREEN_HEIGHT);
		
		this.setVisible(true);
	}
	
	public JPanel getGamePanel() {
		return this.gamePanel;
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
}
