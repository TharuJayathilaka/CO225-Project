// A new class for Julia.
// that helps to create objects only for Julia set

public class Julia
{

	private double x_i;
	private double y_i;
	protected boolean abs;
	private double n_x;
	private double n_y;
	private int iter_no;
	
	// Julia Set constructor
	public Julia(double a,double b)
	{
		x_i=a;
		y_i=b;
	}
	
	//Take values of the complex numbers within the region of interest 
	public void cmpVal(double i,double j)
	{
		n_x=(((double)i*2)/800)-1;
		n_y=1-(((double)j*2)/800);
	}
	
	//take the bound value of the every point of the map
	public boolean checkABS(int iterations)		
	{
		iter_no=0;
		
		while(iterations-->0)
		{
			double m_x=(n_x*n_x)-(n_y*n_y)+x_i;
			double m_y=(2*n_x*n_y)+y_i;
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