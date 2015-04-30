package Main.Games.ninestones;

import Main.Games.ninestones.Interface;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Tester extends JFrame {

	private JFrame frame;
	private Interface game;

	// size of window
	private final int WIDTH = 800, HEIGHT = 600;

	public Tester() {
		frame = new JFrame("Tester");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(this.WIDTH, this.HEIGHT);
		frame.setLayout(null); // no layout
		game = new Interface();
		frame.add(game.getGamePanel());
		frame.setVisible(true);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Tester tester = new Tester();
	}

}
