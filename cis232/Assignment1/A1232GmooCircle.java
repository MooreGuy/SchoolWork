public class A1232GmooCircle extends A1232GmooShape
{
    public A1232GmooCircle( double rad )
    {
        radius = rad;
    }
    
    public double area( )
    {
        return Math.PI * radius * radius;
    }
    
    public double perimeter( )
    {
        return 2 * Math.PI * radius;
    }
    
    public String toString( )
    {
        return "Circle: " + radius;
    }
    
    private double radius;
}
