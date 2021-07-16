// A new class for Mandelbrot.
// that helps to create objects only for mandelbrot set

public class Mandelbrot
{
	
	private double start_x;
	private double finish_x;
	private double start_y;
	private double finish_y;
	protected  static boolean abs;
	private static double x;
	private static double y;
	private int iter_no;
	
	// Mandelbrot Set constructor
	public Mandelbrot(double xs,double xf,double ys,double yf)
	{
		start_x=xs;
		finish_x=xf;
		start_y=ys;
		finish_y=yf;
	}
	
    //Take values of the complex numbers within the region of interest  
	public void cmpVal(int i,int j)
	{
		x=(((double)i*(finish_x-start_x))/800)-Math.abs(start_x);
		y=(((double)j*(start_y-finish_y))/800)+Math.abs(finish_y);
	}
	
	//take the bound value of the every point of the map
	public boolean checkABS(int iterations)		
	{
		iter_no=0;
		double n_x=0;
		double n_y=0;
	
	    while(iterations-->0)
		{
			double m_x=(n_x*n_x)-(n_y*n_y)+x;
			double m_y=(2*n_x*n_y)+y;
			n_x=m_x;
			n_y=m_y;
			iter_no++;
			//Check the absolute of the complex number is larger than two or not
			if((Math.pow(n_x,2)+Math.pow(n_y,2))>4)
			{	
				return abs=false;
			}	
		}	
		return abs=true;
	}
	
	//returns the number of iterations to further calculations
	public int getIteration()
	{
		return iter_no;
	}
}