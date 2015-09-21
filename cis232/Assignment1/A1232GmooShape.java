
public abstract class A1232GmooShape
{
    public abstract double area( );
    public abstract double perimeter( );
 
    public int compareTo( A1232GmooShape rhs )
    {
        double diff = area( ) - rhs.area( );
        if( diff == 0 )
            return 0;
        else if( diff < 0 )
            return -1;
        else
            return 1;
    }
   
    public double semiperimeter( )
    {
        return perimeter( ) / 2; 
    }
}
