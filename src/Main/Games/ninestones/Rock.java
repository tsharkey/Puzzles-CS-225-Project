package Main.Games.ninestones;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

@SuppressWarnings("serial")
public class Rock extends JPanel implements MouseMotionListener, MouseListener {
	private int weight = 50;
	private BufferedImage img;
	private int weighing = 0; // none, left, right
	private Color clr;
	private MouseEvent firstMouseEvent = null;
	
	public Rock(int weight) {
		this();
		this.weight = weight;
		clr = Color.RED;
	}
	
	public Rock() {
		setSize(60, 60);
		setBorder(BorderFactory.createLineBorder(Color.black));
		addMouseMotionListener(this); // listener for mouse events
		addMouseListener(this);
		try {
			img = ImageIO.read(Interface.class.getResource("./Resources/rock.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		clr = Color.BLUE;
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

	
	  public void mousePressed(MouseEvent e) {
		    //Don't bother to drag if there is no image.
		    //if (image == null)
		      //return;

		    firstMouseEvent = e;
		    e.consume();
	}
	  
	  public void mouseDragged(MouseEvent e) {
		    //Don't bother to drag if the component displays no image.
		    ///if (image == null)
		    //  return;

		    if (firstMouseEvent != null) {
		      e.consume();

		      //If they are holding down the control key, COPY rather than MOVE
		      int ctrlMask = InputEvent.CTRL_DOWN_MASK;
		      int action = ((e.getModifiersEx() & ctrlMask) == ctrlMask) ? TransferHandler.COPY
		          : TransferHandler.MOVE;

		      int dx = Math.abs(e.getX() - firstMouseEvent.getX());
		      int dy = Math.abs(e.getY() - firstMouseEvent.getY());
		      //Arbitrarily define a 5-pixel shift as the
		      //official beginning of a drag.
		      if (dx > 5 || dy > 5) {
		        //This is a drag, not a click.
		        JComponent c = (JComponent) e.getSource();
		        TransferHandler handler = c.getTransferHandler();
		        //Tell the transfer handler to initiate the drag.
		        handler.exportAsDrag(c, firstMouseEvent, action);
		        firstMouseEvent = null;
		      }
		    }
		  }

		  public void mouseReleased(MouseEvent e) {
		    firstMouseEvent = null;
		  }
		  
		  public void mouseMoved(MouseEvent e) {
		  }

		@Override
		public void mouseClicked(MouseEvent e) {
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
		  
		  /*
	  
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
        JComponent c = (JComponent) e.getSource();
        TransferHandler handler = c.getTransferHandler();
        //Tell the transfer handler to initiate the drag.
        handler.exportAsDrag(c, e, TransferHandler.MOVE);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	*/
}