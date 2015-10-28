package cis233.a1;
// LinkedList class
//
// CONSTRUCTION: with no initializer
// Access is via LinkedListIterator class
//
// ******************PUBLIC OPERATIONS*********************
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// LinkedListIterator zeroth( )
//                        --> Return position to prior to first
// LinkedListIterator first( )
//                        --> Return first position
// void insert( x, p )    --> Insert x after current iterator position p
// void remove( x )       --> Remove x
// LinkedListIterator find( x )
//                        --> Return position that views x
// LinkedListIterator findPrevious( x )
//                        --> Return position prior to x
// ******************ERRORS********************************
// No special errors

/**
 * Linked list implementation of the list
 *    using a header node.
 * Access to the list is via LinkedListIterator.
 * @author Mark Allen Weiss
 * @see LinkedListIterator
 */
public class A1233GMooLL<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the list
     */
    public A1233GMooLL( )
    {
        header = new A1233GMooListNode<AnyType>( null );
    }

    /**
     * Test if the list is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return header.next == null;
    }

    /**
     * Make the list logically empty.
     */
    public void makeEmpty( )
    {
        header.next = null;
    }

    /**
     * Return an iterator representing the header node.
     */
    public A1233GMooLinkedListIterator<AnyType> zeroth( )
    {
        return new A1233GMooLinkedListIterator<AnyType>( header );
    }

    /**
     * Return an iterator representing the first node in the list.
     * This operation is valid for empty lists.
     */
    public A1233GMooLinkedListIterator<AnyType> first( )
    {
        return new A1233GMooLinkedListIterator<AnyType>( header.next );
    }

    /**
     * Insert after p.
     * @param x the item to insert.
     * @param p the position prior to the newly inserted item.
     */
    private void insert( AnyType x, A1233GMooLinkedListIterator<AnyType> p )
    {
        if( p != null && p.current != null )
            p.current.next = new A1233GMooListNode<AnyType>(x, p.current.next);
    }

	public void add(AnyType x) {
		A1233GMooLinkedListIterator<AnyType> itr = first();

		A1233GMooListNode<AnyType> previous;
		do {
			previous = itr.current;
			itr.advance();
		} while(itr.isValid() && itr.retrieve().compareTo(x) == -1);

		insert(x, new A1233GMooLinkedListIterator<AnyType>(previous));
	}

    /**
     * Return iterator corresponding to the first node containing an item.
     * @param x the item to search for.
     * @return an iterator; iterator is not valid if item is not found.
     */
    public A1233GMooLinkedListIterator<AnyType> find( AnyType x )
    {
        A1233GMooListNode<AnyType> itr = header.next;

        while( itr != null && !itr.element.equals( x ) )
            itr = itr.next;

        return new A1233GMooLinkedListIterator<AnyType>( itr );
    }

    /**
     * Return iterator prior to the first node containing an item.
     * @param x the item to search for.
     * @return appropriate iterator if the item is found. Otherwise, the
     * iterator corresponding to the last element in the list is returned.
     */
    public A1233GMooLinkedListIterator<AnyType> findPrevious( AnyType x )
    {
        A1233GMooListNode<AnyType> itr = header;

        while( itr.next != null && !itr.next.element.equals( x ) )
            itr = itr.next;

        return new A1233GMooLinkedListIterator<AnyType>( itr );
    }

    /**
     * Remove the first occurrence of an item.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        A1233GMooLinkedListIterator<AnyType> p = findPrevious( x );

        if( p.current.next != null )
            p.current.next = p.current.next.next;  // Bypass deleted node
    }

	public boolean replace(AnyType repl, AnyType ins) {
        A1233GMooLinkedListIterator<AnyType> p = findPrevious( repl );

        if( p.current.next != null ) {
            p.current.next = p.current.next.next;
			add(ins);
			return true;
		}


		return false;
	}

	public A1233GMooResult<AnyType> getMode() {
		if (isEmpty()) {
			return new ModeResult<AnyType>(null, 0);
		}

		A1233GMooLinkedListIterator<AnyType> itr = first();
		AnyType highest = itr.current.element;

		int highestCount = 1;
		int currentCount = 1;
		for (; itr.isValid(); itr.advance()) {
			if (itr.current.next != null &&
				itr.current.element.compareTo(itr.current.next.element) == 1) {
				currentCount++;
			} else {
				currentCount = 1;
			}

			if (highestCount < currentCount) {
				highestCount = currentCount;
				highest = itr.current.element;
			}
		}

		return new ModeResult<AnyType>(highest, highestCount);
	}

    // Simple print method
    public static <AnyType extends Comparable<? super AnyType>> void
		printList( A1233GMooLL<AnyType> theList )
    {
        if( theList.isEmpty( ) )
            System.out.print( "Empty list" );
        else
        {
            A1233GMooLinkedListIterator<AnyType> itr = theList.first( );
            for( ; itr.isValid( ); itr.advance( ) )
                System.out.print( itr.retrieve( ) + " " );
        }

        System.out.println( );
    }

	public void showList() {
		if(this.isEmpty()) {
			System.out.println("Empty List");
			System.out.println("Array Size: 0");
			return;
		}

		A1233GMooLinkedListIterator<AnyType> itr = first();
		int size = 1;

		System.out.println("Contents of the list:");
		while(itr.isValid()) {
			System.out.println(size + ": " + itr.current.element);
			itr.advance();
			size++;
		}

		System.out.println("Array Size: " + size);
	}

	public void showList(int perLine) {
		if(this.isEmpty()) {
			System.out.println("Empty List");
			System.out.println("Array Size: 0");
			return;
		}

		A1233GMooLinkedListIterator<AnyType> itr = first();
		int size = 1;
		int lineSize = 0;

		System.out.println("Contents of the list:");
		for(;itr.isValid(); size++, lineSize ++) {
			if(lineSize == perLine) {
				System.out.print("\n");
				lineSize = 0;
			}

			System.out.print(size + ": " + itr.current.element + ", ");
			itr.advance();
		}
		System.out.print("\n");

		System.out.println("Array Size: " + size);
	}

    private A1233GMooListNode<AnyType> header;

    // In this routine, LinkedList and LinkedListIterator are the
    // classes written in Section 17.2.
    public static <AnyType extends Comparable<? super AnyType>> int
		listSize( A1233GMooLL<AnyType> theList )
    {
        A1233GMooLinkedListIterator<AnyType> itr;
        int size = 0;

        for( itr = theList.first(); itr.isValid(); itr.advance() )
            size++;

        return size;
    }

	private class ModeResult<AnyType> implements A1233GMooResult<AnyType> {
		ModeResult(AnyType mo, int co) {
			mode = mo;
			count = co;
		}

		public AnyType mode() { return mode; }
		public int count() { return count; }

		private AnyType mode;
		private int count;
	}
}
