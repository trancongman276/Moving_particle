package lab2.Object;

public class Pacticle {
	private int x,y;
	static enum Direction {N,NE,E,SE,S,SW,W,NW};
	
	public Pacticle(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean move(int w, int h, Direction D) {
		switch(D) {
			case N: 
				if(!check(w,h,0,-1)) return false; 
				else y-=1;return true;
			case NE: 
				if(!check(w,h,1,-1)) return false;
				else x+=1; y-=1;return true;
			case E: 
				if(!check(w,h,1,0)) return false;
				else x+=1;return true;
			case SE: 
				if(!check(w,h,1,1)) return false;
				else x+=1;y+=1;return true;
			case S: 
				if(!check(w,h,0,1)) return false;
				else y+=1;return true;
			case SW: 
				if(!check(w,h,-1,1)) return false;
				else y+=1;x-=1;return true;
			case W: 
				if(!check(w,h,-1,0)) return false;
				else x-=1;return true;
			case NW: 
				if(!check(w,h,-1,-1)) return false;
				else y-=1;x-=1;return true;
		}
		return true;
	}
	//Check if the particle hits the wall or not
	private boolean check(int w, int h, int mX,int mY) {
		if(x==1 && mX==-1) return false;
		if(y==1 && mY==-1) return false;
		if(x==h && mX==1) return false;
		if(y==w && mY==1) return false;
		return true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
