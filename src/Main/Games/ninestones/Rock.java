package Main.Games.ninestones;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Rock extends JPanel {
	private int weight = 50;
	private BufferedImage img;
	private int weighing = 0; // none, left, right
	private Color clr;
	
	public Rock(int weight) {
		this();
		this.weight = weight;
	}
	
	public Rock() {
		setSize(60, 60);
		setBorder(BorderFactory.createLineBorder(Color.black));
		try {
			img = ImageIO.read(Interface.class.getResource("./Resources/rock.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// getter
	int getWeight() {
		return this.weight;
	}
	
	int getWeighing() {
		return this.weighing;
	}
	
	// setter
	void setWeighing(int weighing) {
		this.weighing = weighing;
	}
	
	// drawing
	void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
        
        if (img != null) {
            int x = (getWidth() - img.getWidth()) / 2;
            int y = (getHeight() - img.getHeight()) / 2;
            g2d.drawImage(img, x, y, this);
        }
        g2d.dispose();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(this.clr);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        this.draw(g);
    }  

}
