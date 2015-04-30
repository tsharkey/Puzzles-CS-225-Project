package Main.Games.ninestones;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Rock extends JPanel implements MouseListener {
	
	private int x, y;
	private int weight = 50;
	private BufferedImage img;
	private int weighing = 0; // none, left, right
	
	public Rock(Point coord, int weight) {
		this(coord);
		this.weight = weight;
	}
	
	public Rock(Point coord) {
		this.x = coord.x;
		this.y = coord.y;
		setSize(60, 60);
		setLocation(this.x, this.y);
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseListener(this); // listener for mouse events
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
	
	// mouse events
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
        g2d.setPaint(Color.BLUE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        this.draw(g);
    }
}
