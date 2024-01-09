import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Runner extends JPanel implements ActionListener, MouseMotionListener{
	
	//arraylist syntax
	Particle[] p1 = new Particle[100];
	ArrayList<Particle> particles = new ArrayList<Particle>();
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		//check for collisions - infections
		for (int i=0; i<particles.size();i++) {
			for (int j= i+1; j<particles.size(); j++) {
				particles.get(i).collide(particles.get(j));
			}
		}
		//traversing a list
		//a list has a size and a get(i) method
		for (Particle p: particles) {
			p.paint(g);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] arg) {
		Runner m = new Runner();
	}
	
	public Runner() {
		JFrame f = new JFrame("ArrayList");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800,600);
		f.add(this);
		f.addMouseMotionListener(this);
		Timer t = new Timer(16, this);
		//Create particles to add to the list
		for (int i=0; i<100; i++) {
			
			//you can add to a list using the add method
			particles.add(new Particle());
			
		}
		t.start();
		f.setVisible(true);
		
	}









	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}









	@Override
	public void mouseDragged(MouseEvent m) {
		// TODO Auto-generated method stub
		
		//create a particle
		//add it to the list @mouse position
		Particle newP = new Particle(m.getX(), m.getY());
		particles.add(newP);
		
	}









	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
