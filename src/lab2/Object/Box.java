package lab2.Object;

import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Box {
	private final int width=8, 
					  height=8;
	private int nStep=0,t;
	Random Rand;
	Scanner sc;
	
	private char map[][];
	
	private static Box getInstantiate=null;
	
	public static Box getInstant() {
		if (getInstantiate == null) 
			{
			getInstantiate = new Box();
			}
		return getInstantiate;
	}
	
	private Box() {
		sc = new Scanner(System.in);
		Rand = new Random();
		map = new char[width+2][height+2];
		List<Pacticle> list = new ArrayList<Pacticle>();
		
		list = init();
		System.out.printf("Input n: "); nStep=sc.nextInt(); //Number of step.
		for(int n=1;n<=nStep;n++) {
			t=1;
			boolean k=true;
			System.out.println("Time "+n);
			list.forEach(i -> {
				while(k) //Try again if the particle hit the wall
				{
					if(i.move(width,
							   height,
							   Pacticle.Direction.values()
							   [Rand.nextInt(Pacticle.Direction.values().length)])) break;
				}
				
				//Print out the position of particles in each step
					//System.out.println("Pacticle "+t+": "+i.getX()+" "+i.getY()); 
					t++;
			});
			CheckCollide(list); // Check Collide
			draw(list); System.out.println(); //Draw
		}
		
		System.out.println("Number of Pacticles: "+list.size());
		
	}
	//Set init values.
	private List<Pacticle> init() {
		
		//Create the first particles
		Rand = new Random();
		List<Pacticle> list = new ArrayList<Pacticle>();
		for(int i=0;i<=2;i++) {
			list.add(new Pacticle(Rand.nextInt(width-1)+1,
					Rand.nextInt(height-1)+1));
		}
		return list;		
}
	
	//Check if two or more particle collide or not
	private void CheckCollide(List<Pacticle> ls) {
		int fb=1;
		int x= 0,y=0;
		boolean k=true;
		while(k) {
			k=false;
			for( Pacticle a : ls.subList(fb-1,ls.size()-1)) {
				for (Pacticle b : ls.subList(fb, ls.size()))
						if(a.getX()== b.getX() && a.getY()==b.getY()) {
							if(ls.size()==((width)*(height))) {
								System.out.println("Box is full."); 
								System.out.println("Number of Pacticles: "+ls.size());
								draw(ls);
								System.exit(-1);
								}
							
							//Check if the new particle has the same position as the existing particle or not
							boolean loop=true;
							while(loop) {
								loop=false;
								int w=Rand.nextInt(width-1)+1;
								int h=Rand.nextInt(height-1)+1;
									ls.forEach(c ->{
										if(c.getX()==w |c.getY()==h) 
											{int t=1;}
									});
									if (t==1) loop=true; else {x=w;y=h;}
							}	
							k=true; break;
						}
				if (k) break; else fb++;
			}
			//Create new particle
			if(k) ls.add(new Pacticle(x,y));	
			fb++;
		}
	}
	//Draw particles in console
	private void draw(List<Pacticle> ls) {
		//Draw the box
		for(int y=0;y<=height+1;y++) {
			for(int x=0;x<=width+1;x++) {	 
				if(y==0 | y==height+1) map[x][y]='-';
				else 
					if(x==0 | x==width+1) map[x][y]='|';
					else map[x][y]=' ';
			}
		}
		ls.forEach(a ->{
			map[a.getX()][a.getY()]='x';
		});
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("\n\n\n\n\n\n\n");
		for(int y=0;y<=height+1;y++) {
			for(int x=0;x<=width+1;x++){	
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
	}
}
