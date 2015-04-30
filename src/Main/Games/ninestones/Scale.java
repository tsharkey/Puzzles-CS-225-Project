package Main.Games.ninestones;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Scale extends JPanel implements MouseListener {
	
	private BufferedImage img;
	private int scaleTipping; // none, left, right
	
	public Scale() {
		setSize(300, 200);
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseListener(this); // listener for mouse events
		try {
			img = ImageIO.read(Interface.class.getResource("./Resources/scale.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// setter
	void setTipping(int tip) {
		this.scaleTipping = tip;
	}
	
	// getter
	int getTipping() {
		return this.scaleTipping;
	}

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
	
	void draw(Graphics g)
	{
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
