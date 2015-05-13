/**
 * Scale.java
 * 
 * Handle setting up and drawing scale. Update depending on weight of
 * rocks or gem.
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
public class Scale extends JPanel {
	private BufferedImage[] images;
	private BufferedImage currentImage;
	private int scaleStatus; // none, left, right
	private Color background = Color.GRAY;
	private String currentInfo;
	private String[] infoStr;

	/**
	 * Default constructor
	 */
	public Scale() {
		setPreferredSize(new Dimension(300, 200));

		this.infoStr = new String[4];
		this.infoStr[0] = "Both sides weigh the same!";
		this.infoStr[1] = "The left side weighs more!";
		this.infoStr[2] = "The right side weighs more!";
		this.infoStr[3] = "Nothing has been weighed yet!";

		this.images = new BufferedImage[3];
		try {
			this.images[0] = ImageIO.read(getClass().getResourceAsStream(
					"Resources/scale3.png"));
			this.images[1] = ImageIO.read(getClass().getResourceAsStream(
					"Resources/scale2.png"));
			this.images[2] = ImageIO.read(getClass().getResourceAsStream(
					"Resources/scale.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.resetScale(); // setting to default
	}

	/**
	 * Update scale image based on status. equal (0), left (1), right (2)
	 * 
	 * @param status
	 */
	public void updateScale(int status) {
		this.scaleStatus = status;
		if (this.scaleStatus < 3) {
			this.currentInfo = this.infoStr[this.scaleStatus];
			this.currentImage = this.images[this.scaleStatus];
			repaint();
		}
	}

	/**
	 * Reset scale to default state
	 */
	public void resetScale() {
		updateScale(0);
		this.currentInfo = this.infoStr[3];
	}

	/**
	 * Return info string
	 * 
	 * @return
	 */
	public String getInfo() {
		return this.currentInfo;
	}

	/**
	 * Drawing image of scale
	 */
	void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();

		if (this.currentImage != null) {
			int x = (getWidth() - this.currentImage.getWidth()) / 2;
			int y = (getHeight() - this.currentImage.getHeight()) / 2;
			g2d.drawImage(this.currentImage, x, y, this);
		}
		g2d.dispose();
	}

	/**
	 * paintComponent to handle drawing scale image
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
