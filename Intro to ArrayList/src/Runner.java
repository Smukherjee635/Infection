import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
	Particle[] p1 = new Particle[1];
	ArrayList<Particle> particles = new ArrayList<Particle>();
	public void paint(Graphics g) {
		this.setBackground(Color.yellow);
		int numInfected = 0;
		int numHealthy = 0;
		int numRecovered = 0;
		int numDead = 0;
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
		//Checking status of each
		for (Particle p: particles) {
			if (p.getStatus()==-1) {
				numInfected++;
			}
			if (p.getStatus()==1) {
				numHealthy++;
			}
			if (p.getStatus()==0) {
				numRecovered++;
			}
			if (p.getStatus()==-2) {
				numDead++;
			}
		}
		//Displaying counters on screen
		Font boldFont = new Font("Serif", Font.BOLD, 12);
		g.setFont(boldFont);
		g.setColor(Color.black);
		g.drawString("Number Infected: "+numInfected, 100, 30);
		g.drawString("Number Healthy: "+numHealthy, 250, 30);
		g.drawString("Number Recovered: "+numRecovered, 400, 30);
		g.drawString("Number Dead: "+numDead, 550, 30);
		
	}
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
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
		for (int i=0; i<1000; i++) {
			
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
