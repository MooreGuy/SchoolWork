// Basic node stored in a linked list
// Note that this class is not accessible outside
// of package weiss.nonstandard

class A1233GMooListNode<AnyType>
{
      // Constructors
    public A1233GMooListNode( AnyType theElement )
    {
        this( theElement, null );
    }

    public A1233GMooListNode( AnyType theElement,
		A1233GMooListNode<AnyType> n )
    {
        element = theElement;
        next    = n;
    }

    public AnyType   element;
    public A1233GMooListNode<AnyType> next;
}
