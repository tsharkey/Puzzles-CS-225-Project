/**
 * Rock.java
 * 
 * Create a Rock object that contains various attributes such as weight
 * and image.
 * 
 */

package Main.Games.ninestones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Rock extends JPanel {
	private int weight = 50; // default weight
	private int weighStatus = 0; // none (0), left (1), right (2)
	private Color background; // background color
	private BufferedImage img;

	/**
	 * Override to default constructor to set weight
	 * 
	 * @param weight
	 */
	public Rock(int weight) {
		this();
		this.weight = weight;
	}

	/**
	 * Default constructor
	 */
	public Rock() {
		this.background = Color.BLUE;
		setPreferredSize(new Dimension(60, 60));
		try {
			img = ImageIO.read(getClass().getResourceAsStream(
					"Resources/rock.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get weight
	 * 
	 * @return
	 */
	int getWeight() {
		return this.weight;
	}

	/**
	 * Get weighing status
	 * 
	 * @return
	 */
	int getWeighing() {
		return this.weighStatus;
	}

	/**
	 * Set weighing status
	 * 
	 * @param weighing
	 */
	void setWeighing(int weighing) {
		this.weighStatus = weighing;
	}

	/**
	 * Drawing image of rock
	 */
	void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		if (img != null) {
			int x = (getWidth() - img.getWidth()) / 2;
			int y = (getHeight() - img.getHeight()) / 2;
			g2d.drawImage(img, x, y, this);
		}
		g2d.dispose();
	}

	/**
	 * paintComponent to handle drawing rock image
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(this.background);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		this.draw(g);
	}
}