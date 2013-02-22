package es.olgierd.aboynator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.font.GraphicAttribute;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

class GUI extends JPanel implements MouseListener, MouseMotionListener{
    
    private JPanel panel;
    public AboyneGame ag;
    private Field closestToCursor = null;
    private ArrayList<Field> availableMoves;
    private Stone selectedStone;
    
    private Polygon getNewHexagon(Point centre, int radius) {
	
	Polygon p = new Polygon();
	p.npoints = 7;

	int x[] = new int[7];
	int y[] = new int[7];
	

	for(int i = 0; i < 6; i++) {
	    x[i] = (int)(radius * Math.sin(Math.PI*(i/3.0)))+centre.x;
	    y[i] = (int)(radius * Math.cos(Math.PI*(i/3.0)))+centre.y;
	}	
	
	x[6] = x[0];
	y[6] = y[0];
	
	p.xpoints = x;
	p.ypoints = y;
	
	return p;
    }
    
    
    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	g.setColor(Color.blue);

	for (Field f : ag.board.getFields()) {
	    g.drawPolygon(getNewHexagon(f.location, ag.board.getFieldSize()/2));
	    g.drawString(Integer.toString(f.fieldID), f.location.x-5, f.location.y+5);
	}
	
	g.setColor(Color.red);
	
	for(Stone st : ag.playerLeft.stones) {
	    Point p = st.field.location; 
	    g.fillOval(p.x-8, p.y-8, 16, 16);
	    if(st.isLocked()) {
		g.setColor(Color.black);
		g.drawString("X", st.field.location.x-5, st.field.location.y+5);
		g.setColor(Color.white);
	    }
	    
	}
	
	g.setColor(Color.black);
	
	for(Stone st : ag.playerRight.stones) {
	    Point p = st.field.location; 
	    g.fillOval(p.x-8, p.y-8, 16, 16);
	    if(st.isLocked()) {
		g.setColor(Color.white);
		g.drawString("X", st.field.location.x-5, st.field.location.y+5);
		g.setColor(Color.black);
	    }
	}

	g.setColor(Color.red);
	
	if(closestToCursor != null) {
//	    g.fillOval(closestToCursor.location.x-4, closestToCursor.location.y-4, 8, 8);
	    
	    g.setColor(Color.orange);
	    
//	    for(Field f : closestToCursor.neighbors)
//		if(f != null)
//		    g.fillOval(f.location.x-4, f.location.y-4, 8, 8);
	}
	
	
	g.setColor(Color.RED);
	
	for(Field f: availableMoves) 
	    g.fillOval(f.location.x-4, f.location.y-4, 8, 8);
	
//	if(selectedStone != null)
//	    g.fillOval(selectedStone.field.location.x-4, selectedStone.field.location.y-4, 8, 8);
	
    }
    
    public GUI() {
	
	availableMoves = new ArrayList<Field>();
	panel = new JPanel();
	this.setSize(600, 600);
	this.setVisible(true);
	addMouseListener(this);
	addMouseMotionListener(this);
//	ag = new AboyneGame();
	
    }

    private void generateAvailableMoves(Stone s) {
	
	availableMoves = ag.getAvailableMoves(s);
	
    }
    
    private void getNearestField(Point p) {
//	sets closestToCursor to nearest field
	
	
	double dist = 2000, tempdist;
	
	for(Field f : ag.board.getFields()) {
	    tempdist = f.location.distance(p);
	    if(tempdist < dist) {
		dist = tempdist;
		closestToCursor = f;
	    }
	}
	
	if(dist > 30) {
	    closestToCursor = null;
	}
    }
    
    
    @Override
    public void mouseClicked(MouseEvent arg0) {
	
	if(arg0.getButton() == arg0.BUTTON1) {

	    getNearestField(arg0.getPoint());
	    
	    if(closestToCursor != null) {
		
		if(availableMoves.contains(closestToCursor)) {
		    
		    ag.move(selectedStone, closestToCursor);
		    closestToCursor = null;
		    availableMoves.clear();
		    selectedStone = null;
		}
		
		
		else if(closestToCursor.stone != null && selectedStone != closestToCursor.stone) {
		    if(!closestToCursor.stone.isLocked()) {
			if(closestToCursor.stone.owner == ag.playerLeft) {
			    selectedStone = closestToCursor.stone;
			    generateAvailableMoves(closestToCursor.stone);
			}
		    }
		}
		else {
		    closestToCursor = null;
		    availableMoves.clear();    
		    selectedStone = null;
		}
	    }
	}
	
	else if (arg0.getButton() == arg0.BUTTON3) {
	    closestToCursor = null;
	    availableMoves.clear();
	    selectedStone = null;
	}
	
	
	this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
	
    }


    @Override
    public void mouseExited(MouseEvent arg0) {
    }


    @Override
    public void mousePressed(MouseEvent arg0) {
    }


    @Override
    public void mouseReleased(MouseEvent arg0) {
    }


    @Override
    public void mouseDragged(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
	
	getNearestField(arg0.getPoint());
	
	this.repaint();
    }
}


public class MainWindow {
    
    public static void main(String[] argv) {
	
	JFrame window = new JFrame();
	Container content = window.getContentPane();
	GUI g = new GUI();
	
	g.ag = new AboyneGame();
	
	content.add(g);
	
	window.setSize(420, 420);
	window.setVisible(true);
	window.setTitle("Aboyne game by Pilarczyk&Tomczyk");

	
	window.addWindowListener(new WindowListener() {
	    
	    @Override
	    public void windowClosed(WindowEvent arg0) {}

	    @Override
	    public void windowActivated(WindowEvent e) {}

	    @Override
	    public void windowClosing(WindowEvent e) {	  
		System.exit(0);
	    }

	    @Override
	    public void windowDeactivated(WindowEvent e) {}

	    @Override
	    public void windowDeiconified(WindowEvent e) {}

	    @Override
	    public void windowIconified(WindowEvent e) {}

	    @Override
	    public void windowOpened(WindowEvent e) {}
	});
	
	
	
	
    }
    
    
    
}