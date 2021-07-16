import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.*;

@SuppressWarnings("serial")
public class Fractal extends JPanel  //Ftactal class inherites JPanel
{ 	

    Mandelbrot obj1=null;
	Julia obj2=null;


	private boolean abs;
	private int width=800; //since canvas size is 800*800
	private int height=800; //since canvas size is 800*800
	private int iter_no;
	private int complex=5;
	
	// Julia Set constructor
	public Fractal(double x,double y,int iterations)
	{
		iter_no=iterations;
		complex=1;
		// new object from julia set
		obj2 = new Julia(x,y);  
		// set the panel size to the given size 
		setPreferredSize(new Dimension(width,height));
	}
	
	// Mandelbrot Set constructor
	public Fractal(double xs,double xf,double ys,double yf,int iter)
	{
		iter_no=iter;
		complex=0;
		// new object from Mandelbrot set
		obj1 = new Mandelbrot(xs,xf,ys,yf);
		// set the panel size to the given size
		setPreferredSize(new Dimension(width,height));
	}
	
	//can use this method to print points using the given colour
	private static void printPoint(Graphics2D frame, Color c,double x,double y) 
	{
	    frame.setColor(c); 
	    frame.draw(new Line2D.Double(x,y,x,y)); 
	}	
	 
	//call paintComponent from parent class 
	public void paintComponent(Graphics g) 
	{ 
		// call paintComponent from parent class 
		super.paintComponent(g); 
		Color color=null;
		for(int i=0;i<=800;i++)
		{	
			for(int j=0;j<=800;j++)
			{	
				if(complex==0)//checks the type of fractal
				{
                    //call the method cmpval from Mandelbrot class					
					obj1.cmpVal(i,j);
					this.abs = obj1.checkABS(iter_no);
					//color iteration for different points
					color = Color.getHSBColor((float)obj1.getIteration()*20.0f/(float)iter_no,1.0f,1.0f);
					//checks the point exists in or out of the Fractal set
					
					//paint black and white for mandelbrot set
				    if(abs)
				    {	
                        //paint black if abs isnot larger than 2				
					    printPoint((Graphics2D)g,Color.BLACK,i,j); 
				    }	 
				    else
				    {
					    printPoint((Graphics2D)g,color.WHITE,i,j);
				    }
				}
				else if(complex==1)//checks the type of fractal
				{
					//call the method cmpval from Julia class
					obj2.cmpVal(i,j);
					this.abs = obj2.checkABS(iter_no);
					//color iteration for different points
					color = Color.getHSBColor((float)obj2.getIteration()*10.0f/(float)iter_no,1.0f,1.0f);	
				    //checks the point exists in or out of the Fractal set
					
					//paint colours for Julia set
				    if(abs)
				    {	
				        //paint black if abs isnot larger than 2
					    printPoint((Graphics2D)g,Color.BLACK,i,j); 
				    }	 
				    else
				    {
					    printPoint((Graphics2D)g,color,i,j);
				    }
				}
			}	
		}		
	}	
	
	//print the correct syntax if something is wrong
	public static void printCorrectSyntax()
	{
		System.out.println("Syntax is below");
    	System.out.println("java Fractal Mandelbrot start_x finish_x start_y finish_y iterations");
    	System.out.println("java Fractal Julia x y iterations");
    	System.exit(0);
	}
	
	public static void main(String[]args)
	{	
	    JFrame frame=null;
		//if there is no argument print syntax
	    if(args.length==0)  
	    {
			System.out.println("\nArguments should follow the syntax");
	    	printCorrectSyntax();
		}
		else
		{
            double start_x=0;  
	    	double finish_x=0;
	    	double start_y=0;
	    	double finish_y=0;
	    	int iterations=1000;	

			
	        // if the first argument is Julia do the following things
	    	if(args[0].equals("Julia"))  
	    	{	
				// if only Mandelbrot is given as the argument use the default values
	    		if(args.length==1)  
	    		{
	    			start_x=-0.4;
	    			start_y=0.6;
	    		}
				// if no of iteration is not given use 1000
				else if(args.length==3)
	    		{	
	    			start_x = Double.parseDouble(args[1]);
	    			start_y = Double.parseDouble(args[2]);
	    		}
				// if everything was given
	    		else if(args.length==4)
	    		{	
	    			start_x = Double.parseDouble(args[1]);
	    			start_y = Double.parseDouble(args[2]);
	    			iterations = Integer.parseInt(args[3]);
	    		}
	    		else
	    		{
					// if arguments are wrong except the Julia print the syntax again
	    			System.out.println("\nArguments should follow the syntax");
	    			printCorrectSyntax();
	    		}
				//call the parent class to get a new frame
	    		frame = new JFrame("Julia Set"); 
	    		// set the content of the frame as one of this panel
				// call the constructor to make new object
	    		frame.setContentPane(new Fractal(start_x,start_y,iterations));
	    	}
			// if the first argument is Mandelbrot do the following things
	    	else if(args[0].equals("Mandelbrot"))   
	    	{
				// if only Mandelbrot is given as the argument use the default values
	    		if(args.length==1) 
	    		{
	    			start_x=-1;
	    			finish_x=1;
	    			start_y=-1;
	    			finish_y=1;
	    		}
				// if no of iteration is not given use 1000
				else if(args.length==5) 
	    		{
	    			start_x = Double.parseDouble(args[1]);
	    			finish_x = Double.parseDouble(args[2]);
	    			start_y = Double.parseDouble(args[3]);
	    			finish_y = Double.parseDouble(args[4]);
	    		}
				// if everything was given
	    		else if(args.length==6)  
	    		{
	    			start_x = Double.parseDouble(args[1]);
	    			finish_x = Double.parseDouble(args[2]);
	    			start_y = Double.parseDouble(args[3]);
	    			finish_y = Double.parseDouble(args[4]);
	    			iterations = Integer.parseInt(args[5]);
	    		}
	    		else  
	    		{
					// if arguments are wrong except the Mandelbrot print the syntax again
	    			System.out.println("\nArguments should follow the syntax");
	    			printCorrectSyntax();
	    		}
				//call the parent class to get a new frame
	    		frame = new JFrame("Mandelbrot Set");     
	    		// set the content of the frame
				// call the constructor to make new object
	    		frame.setContentPane(new Fractal(start_x,finish_x,start_y,finish_y,iterations));  
	    	}	
	    	else
	    	{
				// If the first argument is not Mandelbrot or Julia print the syntax
	    		System.out.println("\nArguments should follow the syntax");
		    	printCorrectSyntax();
	    	}
			
			
			
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack(); 
			frame.setLocationRelativeTo(null); 
			frame.setVisible(true); 	
	    }	
	}
}
