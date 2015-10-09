package cis232.a2;

import weiss.util.AbstractCollection;
import weiss.util.List;
import weiss.util.Collection;
import weiss.util.Iterator;
import weiss.util.ListIterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * The A2232GMoo implements a growable array.
 * Insertions are always done at the end.
 */
public class A2232GMoo<AnyType extends Comparable<? super AnyType>> extends AbstractCollection<AnyType> implements List<AnyType>
{
    /**
     * Construct an empty A2232GMoo.
     */
    public A2232GMoo( )
    {
        clear( );
    }

	public A2232GMoo(int customSize) {
		clear(customSize);
	}

    /**
     * Construct an A2232GMoo with same items as another Collection.
     */
    public A2232GMoo( Collection<? extends AnyType> other )
    {
        clear( );
        for( AnyType obj : other )
            add( obj );
    }

    public A2232GMoo<AnyType> subList( int from, int to )
    { return new SubList( from, to ); }

    private class SubList extends A2232GMoo<AnyType>
    {
        private List<AnyType> original;
        private int offset;
        private int size;


        public A2232GMoo<AnyType> subList( int from, int to )
        {
            return new SubList( this, from, to );
        }

        public SubList( int from, int to )
        {  if( from < 0 || to > A2232GMoo.this.size( ) ) throw new IllegalArgumentException( from + " " + to + " " + A2232GMoo.this.size( ) );
           original = A2232GMoo.this; offset = from; size = to - from; }

        public SubList( SubList sub, int from, int to )
        { if( from < 0 || to > sub.size( ) ) throw new IllegalArgumentException( from + " " + to + " " + sub.size( ) );
          original = sub.original; offset = sub.offset + from; size = to - from; }

        public int size( )
        { return size; }

        public AnyType get( int idx )
        { return original.get( offset + idx ); }

        public AnyType set( int idx, AnyType x )
        { return original.set( offset + idx, x ); }

        public boolean add( AnyType x )
        { throw new UnsupportedOperationException( ); }

        public AnyType remove( int idx )
        { throw new UnsupportedOperationException( ); }

        public boolean remove( Object x )
        { throw new UnsupportedOperationException( ); }

        public boolean contains( Object x )
        { for( AnyType item : this ) if( item.equals( x ) ) return true; return false;  }

        public ListIterator<AnyType> listIterator( int idx )
        { return original.listIterator( offset + idx ); }

        public Iterator<AnyType> iterator( )
        { return original.listIterator( offset ); }
    }

    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }

    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        return theItems[ idx ];
    }

    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws ArrayIndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        if( idx < 0 || idx >= size( ) )
            throw new ArrayIndexOutOfBoundsException( "Index " + idx + "; size " + size( ) );
        AnyType old = theItems[ idx ];
        theItems[ idx ] = newVal;

		sortAscending();

        return old;
    }

    /**
     * Tests if some item is in this collection.
     * @param x any object.
     * @return true if this collection contains an item equal to x.
     */
    public boolean contains( Object x )
    {
        return findPos( x ) != NOT_FOUND;
    }

    /**
     * Returns the position of first item matching x in this collection,
     * or NOT_FOUND if not found.
     * @param x any object.
     * @return the position of first item matching x in this collection,
     * or NOT_FOUND if not found.
     */
    private int findPos( Object x )
    {
        for( int i = 0; i < size( ); i++ )
            if( x == null )
            {
                if( theItems[ i ] == null )
                    return i;
            }
            else if( x.equals( theItems[ i ] ) )
                return i;

        return NOT_FOUND;
    }

	public Result<AnyType> getMode() {
		if (theSize == 0) {
			return new ModeResult<AnyType>(null, 0);
		}

		AnyType highest = theItems[0];

		int highestCount = 1;
		int currentCount = 1;
		for (int x = 1; x < theSize; x++) {
			if (theItems[x].compareTo(theItems[x - 1]) == 0)
				currentCount++;
			else {
				if (highestCount < currentCount)
					highestCount = currentCount;

				highest = theItems[x];
			}
		}

		return new ModeResult<AnyType>(highest, highestCount);
	}

    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        if( theItems.length == size( ) )
        {
            AnyType [ ] old = theItems;
            theItems = (AnyType []) new Comparable[ theItems.length * 2 + 1 ];
            for( int i = 0; i < size( ); i++ )
                theItems[ i ] = old[ i ];
        }

		int index = theSize;
		for (; index > 0 && x.compareTo(theItems[index - 1]) < 0; index--)
			theItems[index] = theItems[index - 1];

		theItems[index] = x;

		theSize++;
        modCount++;

        return true;
    }

	public void add(int index, AnyType element) {
		add(element);
	}

    /**
     * Removes an item from this collection.
     * @param x any object.
     * @return true if this item was removed from the collection.
     */
    public boolean remove( Object x )
    {
        int pos = findPos( x );

        if( pos == NOT_FOUND )
            return false;
        else
        {
            remove( pos );
            return true;
        }
    }

    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        AnyType removedItem = theItems[ idx ];

        for( int i = idx; i < size( ) - 1; i++ )
            theItems[ i ] = theItems[ i + 1 ];
        theSize--;

        modCount++;
        return removedItem;
    }

    /**
     * Change the size of this collection to zero.
     */
    public void clear( )
    {
        theSize = 0;
        theItems = (AnyType []) new Comparable[ DEFAULT_CAPACITY ];
        modCount++;
    }

	public void clear(int customSize) {
		theSize = 0;
		theItems = (AnyType []) new Comparable[customSize];
		modCount++;
	}

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public Iterator<AnyType> iterator( )
    {
        return new A2232GMooIterator( 0 );
    }
    /**
     * Obtains a ListIterator object used to traverse the collection bidirectionally.
     * @return an iterator positioned prior to the requested element.
     * @param idx the index to start the iterator. Use size() to do complete
     * reverse traversal. Use 0 to do complete forward traversal.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public ListIterator<AnyType> listIterator( int idx )
    {
        return new A2232GMooIterator( idx );
    }

    /**
     * This is the implementation of the A2232GMooIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the A2232GMoo.
     */
    private class A2232GMooIterator implements ListIterator<AnyType>
    {
        private int current;
        private int expectedModCount = modCount;
        private boolean nextCompleted = false;
        private boolean prevCompleted = false;

        A2232GMooIterator( int pos )
        {
            if( pos < 0 || pos > size( ) )
                throw new IndexOutOfBoundsException( );
            current = pos;
        }

        public boolean hasNext( )
        {
            if( expectedModCount != modCount )
                throw new ConcurrentModificationException( );
            return current < size( );
        }

        public boolean hasPrevious( )
        {
            if( expectedModCount != modCount )
                throw new ConcurrentModificationException( );
            return current > 0;
        }

        public AnyType next( )
        {
            if( !hasNext( ) )
                throw new NoSuchElementException( );
            nextCompleted = true;
            prevCompleted = false;
            return theItems[ current++ ];
        }

        public AnyType previous( )
        {
            if( !hasPrevious( ) )
                throw new NoSuchElementException( );
            prevCompleted = true;
            nextCompleted = false;
            return theItems[ --current ];
        }

        public void remove( )
        {
            if( expectedModCount != modCount )
                throw new ConcurrentModificationException( );

            if( nextCompleted )
                A2232GMoo.this.remove( --current );
            else if( prevCompleted )
                A2232GMoo.this.remove( current );
            else
                throw new IllegalStateException( );

            prevCompleted = nextCompleted = false;
            expectedModCount++;
        }
    }

	// TODO: Make this private since sorting should be done automatically.
	public void sortAscending() {
		for(int x = 1; x < theSize; x++) {
			AnyType temp = theItems[x];
			int y = x;

			for(; y > 0 && temp.compareTo(theItems[y - 1]) < 0; y--) {
				theItems[y] = theItems[y - 1];
			}
			theItems[y] = (AnyType) temp;
		}
	}

	public static <SomeType extends Comparable<? super SomeType>> int
			binarySearch(A2232GMoo<SomeType> sortedList, SomeType target) {
		int middle = 0;

		for (int low = 0, high = sortedList.size() - 1; low <= high;) {
			middle = (low + high) / 2;

			if (target.compareTo(sortedList.get(middle)) > 0) {
				low = middle + 1;
			} else if (target.compareTo(sortedList.get(middle)) < 0) {
				high = middle - 1;
			} else {
				return middle;
			}
		}

		return -1;
	}

	class ModeResult<AnyType> implements Result<AnyType> {
		ModeResult(AnyType mo, int co) {
			mode = mo;
			count = co;
		}

		public AnyType mode() { return mode; }
		public int count() { return count; }

		private AnyType mode;
		private int count;
	}

    private static final int DEFAULT_CAPACITY = 10;
    private static final int NOT_FOUND = -1;

    private AnyType [ ] theItems;
    private int theSize;
    private int modCount = 0;

}
