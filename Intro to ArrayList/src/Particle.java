import java.awt.Color;
import java.awt.Graphics;

public class Particle{
	//add properties
	private int x,y;
	private int vx, vy;
	private int width;
	private Color c;
	private int status = 1; //healthy: 1, unhealthy: -1, recovered: 0
	
	private long recoveryTime = 10000;
	private long destructTime = 10000;
	//add non-default constructor
	public Particle(int x, int y) {
		this();
		this.x = x;
		this.y = y;
		
		//infected cell- diff color
		c = Color.red;
		status = -1;
		
	}
	
	//collision
	public void collide(Particle other) {
		
		//how do you determine if two circles are 
		//intersecting based on their x,y, and width?
		
		//determine CENTERs of the particles
		int x1 = x + width/2;
		int y1 = y + width/2;
		
		//determine center of other object
		int x2 = other.x + other.width/2;
		int y2 = other.y + other.width/2;
		
		double d = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2, 2));
		
		if(d <=  width/2 + other.width/2) {
			//System.out.println("Infect");
			//collision
			//determine infection
			if(this.status == -1 && other.status == 1) {
				other.status = -1;
			}
			if (other.status == -1 && this.status == 1) {
				this.status = -1;
			}
			if (other.status == 0 && this.status == -1) {
				other.status = -2;
			}
			if (this.status == 0 && other.status == -1) {
				this.status = -2;
			}
			if (this.status == 0 && other.status == 0) {
				this.status=1;
				other.status=1;
			}
		}
	}
	private void setStatus(int i) {
		// TODO Auto-generated method stub
		this.status=i;
	}

	public Particle() {
		//randomize position
		x = (int)(Math.random()*800);
		y = (int)(Math.random()*550);
		
		c = Color.green;
		width = 5;
		
		//[-4 to 4] not including 0
		vx = (int)(Math.random()*9)-4;
		while(vx == 0) vx = (int)(Math.random()*9)-4;
		
		vy = (int)(Math.random()*9)-4;
		while (vy==0) vy = (int)(Math.random()*9)-4;
	}
	public void paint(Graphics g) {
		switch(status) {
		case -1:
			g.setColor(Color.red);
			recoveryTime-=16;
		if(recoveryTime <=0) {
			//chance of recovery or death
			if(Math.random()<.95) {
				status = 0; //recovered
			}
			else {
				status = -2;
				vx = 0;
				vy = 0;
			}
		}
		break;
		case 0:
			g.setColor(Color.blue);
			break;
		case -2:
			g.setColor(Color.black);
			destructTime-=10;
			if (destructTime <0) {
				g.setColor(Color.white);
			}
			vx = 0;
			vy = 0;
			break;
		default:
			g.setColor(Color.green);
		}
		g.fillOval(x, y, width, width);
		
		//update position
		x += vx;
		y += vy;
		if (x<0 || x>770) {
			vx*=-1;
		}
		if (y<0|| y>550) {
			vy*=-1;
		}
	}

	public int getStatus() {
		// TODO Auto-generated method stub
		return status;
	}
}