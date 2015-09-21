/**
 * Guy Moore
 * Randy Scovil
 * Assignment 1
 * Cuesta College
 */
import java.util.Comparator;

public class A1232GmooRectangle extends A1232GmooShape
{
    public A1232GmooRectangle( double len, double wid )
    {
        length = len; width = wid;
    }
     
    public double area( )
    {
        return length * width;
    }
    
    public double perimeter( )
    {
        return 2 * ( length + width );
    }
    
    public String toString( )
    {
        return "Rectangle: " + length + " " + width;
    }
    
    public double getLength( )
    {
        return length;
    }
    
    public double getWidth( )
    {
        return width;
    }

    private double length;
    private double width;
}
