package cis233.a3;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class A3233GM  {
	public static void parseFile(File textFile) {
		A3233GMAVLTree<WordCounter> wordBank = new A3233GMAVLTree<WordCounter>();
		WordCounter highest = new WordCounter();
		int totalWords = 0;

		Scanner reader;
		try {
			reader = new Scanner(textFile)
				// Delimite by all whitespace and punctuation except the apostrophe.
				.useDelimiter("[\\s\\p{Punct}&&[^\u0027]]+");
		} catch(FileNotFoundException exception) {
			System.out.println("Couldn't find file.");
			return;
		}

		String nextChar;
		while(reader.hasNext()) {
			totalWords++;
			nextChar = reader.next();
			WordCounter currentCounter = wordBank.insert(new WordCounter(nextChar));

			// Check to see if we have beat the highest.
			if (highest.getCount() < currentCounter.getCount()) {
				highest = currentCounter;
			}

		}


		PrintTreeAndArray result = wordBank.printAndReturnTree();
		//result.
		
		CompareOccurrences comp = new CompareOccurrences();
		WordCounter[] occurrences = result.treeToArray.counters;

		mergeSort(occurrences, comp);

		StringBuilder builder = new StringBuilder();
		for (int x = 0; x < occurrences.length && occurrences[x] != null; x++) {
			builder.append(occurrences[x].toString());
		}

		PrintWriter writer;
		try {
			writer = new PrintWriter("233GMoo.txt");
		} catch(FileNotFoundException e) {
			System.out.println("Couldn't open file 233GMoo.txt");
			return ;
		}

		writer.println("### Sorted by number of occurrences ###");
		writer.println(builder.toString());
		writer.println("### Sorted alphabetically. ###");
		writer.println(result.treeString);

		writer.print("Total number of words: ");
		writer.println(totalWords);
		writer.print("Total distinct words: ");
		writer.println(result.treeToArray.size);

		writer.print("Most common word: ");
		writer.print(highest.getWord());
		writer.print(", ");
		writer.println(highest.getCount());

		System.out.println("### Sorted by number of occurrences ###");
		System.out.println(builder.toString());
		System.out.println("### Sorted alphabetically. ###");
		System.out.println(result.treeString);

		System.out.print("Total number of words: ");
		System.out.println(totalWords);
		System.out.print("Total distinct words: ");
		System.out.println(result.treeToArray.size);

		System.out.print("Most common word: ");
		System.out.print(highest.getWord());
		System.out.print(", ");
		System.out.println(highest.getCount());

	}

	public static void printAlphabetical(A3233GMAVLTree<WordCounter> wordBank) {
		wordBank.printTree();
	}

	private static void printOccurrence(A3233GMAVLTree<WordCounter> wordBank) {
		// Split tree in half many times, to merge sort and then bring back together
		// in an array.
	}

	private static <AnyType> void merge(AnyType[] a, AnyType[] tmpArray,
			int leftPos, int rightPos, int rightEnd, Comparer<AnyType> comp) {
		int leftEnd = rightPos -1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		while(leftPos <= leftEnd && rightPos <= rightEnd) {
			if(comp.compare(a[leftPos], a[rightPos]) <= 0) {
				tmpArray[tmpPos++] = a[leftPos++];
			} else {
				tmpArray[tmpPos++] = a[rightPos++];
			}
		}

		while(leftPos <= leftEnd) {
			tmpArray[tmpPos++] = a[leftPos++];
		}

		while(rightPos <= rightEnd) {
			tmpArray[tmpPos++] = a [rightPos++];
		}

		for(int i = 0; i < numElements; i++, rightEnd--) {
			a[rightEnd] = tmpArray[rightEnd];
		}
	}

	private static <AnyType> void mergeSort(AnyType[] a, Comparer<AnyType> comp) {
		AnyType[] tmpArray = (AnyType []) new Object[a.length];
		mergeSort(a, tmpArray, 0, a.length - 1, comp);
	}

	private static <AnyType> void mergeSort(AnyType[] a, AnyType[] tmpArray,
			int left, int right, Comparer<AnyType> comp) {
		if(left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center, comp);
			mergeSort(a, tmpArray, center + 1, right, comp);
			merge(a, tmpArray, left, center + 1, right, comp);
		}
	}
}

class CompareOccurrences implements Comparer<WordCounter> {
	public int compare(WordCounter lhs, WordCounter rhs) {
		if (lhs == null) {
			if (rhs == null) {
				return 0;
			} else {
				return 1;
			}
		} 

		if (rhs == null) {
			if (lhs == null) {
				return 0;
			} else {
				return  -1;
			}
		}

		return new Integer(lhs.getCount()).compareTo(rhs.getCount());
	}
}

interface Comparer<AnyType> {
	int compare(AnyType lhs, AnyType rhs);
}

class WordBankOccArray {
	WordCounter[] counters;
	int size;

	public WordBankOccArray(int length) {
		counters = new WordCounter[length];
		size = 0;
	}

	public void add(WordCounter wc) {
		counters[size++] = wc;
	}
}

class WordCounter implements A3233GMDuplicatable<WordCounter> {
	protected String word;
	protected int count;

	public WordCounter() {
		count = 1;
		word = "";
	}

	public WordCounter(String word) {
		this.word = word;
		count = 1;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(word.toLowerCase());
		builder.append(": ");
		builder.append(count);
		builder.append("\n");

		return builder.toString();
	}

	public String getWord() {
		return word;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int compareTo(WordCounter s) {
		return word.compareTo(s.getWord());
	}

	public void duplicate() {
		count++;
	}
}

class OccurrenceCounter extends WordCounter {
	public int compareTo(WordCounter s) {
		return new Integer(count).compareTo(s.getCount());
	}
}

class A3233GMAVLTree<AnyType extends A3233GMDuplicatable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public A3233GMAVLTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public AnyType insert( AnyType x )
    {
		Wrapper<AnyType> inserted = new Wrapper<AnyType>();
		inserted.value = x;

        root = insert( x, root, inserted);

		return inserted.value;
    }

	public AnyType get( AnyType x) {
		return get(x, root);
	}

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> remove( AnyType x, AvlNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return balance( t );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException("");
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException("");
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

	public PrintTreeAndArray printAndReturnTree() {
		// (2^rootheight+1) -1 is the total possible nodes.
		int possibleNodes = (new Double(Math.pow(2, root.height+1))).intValue() - 1;
		WordBankOccArray returnArr = new WordBankOccArray(possibleNodes);

		StringBuilder builder = new StringBuilder();
		printAndReturnTree(returnArr, builder, root);

		return new PrintTreeAndArray(builder.toString(), returnArr);
	}

    private static final int ALLOWED_IMBALANCE = 1;

    // Assume t is either balanced or within one of being balanced
    private AvlNode<AnyType> balance( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;

        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }

    public void checkBalance( )
    {
        checkBalance( root );
    }

    private int checkBalance( AvlNode<AnyType> t )
    {
        if( t == null )
            return -1;

        if( t != null )
        {
            int hl = checkBalance( t.left );
            int hr = checkBalance( t.right );
            if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
                    height( t.left ) != hl || height( t.right ) != hr )
                System.out.println( "OOPS!!" );
        }

        return height( t );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private AvlNode<AnyType> insert( AnyType x, AvlNode<AnyType> t, Wrapper<AnyType> inserted)
    {
        if( t == null ) {
            return new AvlNode<>( x, null, null );
		}

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 )
            t.left = insert( x, t.left, inserted);
        else if( compareResult > 0 )
            t.right = insert( x, t.right, inserted);
        else
			t.element.duplicate();
			inserted.value = t.element;
        return balance( t );
    }

	public AnyType get(AnyType x, AvlNode<AnyType> t) {
        if( t == null )
            return null;

        int compareResult = x.compareTo( t.element );

        if( compareResult < 0 ) {
            return get( x, t.left );
		} else if( compareResult > 0 ) {
            return get( x, t.right );
		} else {
			return t.element;
		}
	}

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private AvlNode<AnyType> findMin( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;

        while( t.left != null )
            t = t.left;
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private AvlNode<AnyType> findMax( AvlNode<AnyType> t )
    {
        if( t == null )
            return t;

        while( t.right != null )
            t = t.right;
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains( AnyType x, AvlNode<AnyType> t )
    {
        while( t != null )
        {
            int compareResult = x.compareTo( t.element );

            if( compareResult < 0 )
                t = t.left;
            else if( compareResult > 0 )
                t = t.right;
            else
                return true;    // Match
        }

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the tree.
     */
    private void printTree( AvlNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

	private void printAndReturnTree(WordBankOccArray arr, StringBuilder builder,  AvlNode<AnyType> t) {
        if( t != null )
        {
            printAndReturnTree(arr, builder, t.left);
			arr.add((WordCounter)t.element);
			builder.append(t.element.toString());
            printAndReturnTree(arr, builder, t.right);
        }
	}

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height( AvlNode<AnyType> t )
    {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithLeftChild( AvlNode<AnyType> k2 )
    {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> rotateWithRightChild( AvlNode<AnyType> k1 )
    {
        AvlNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithLeftChild( AvlNode<AnyType> k3 )
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }

    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode<AnyType> doubleWithRightChild( AvlNode<AnyType> k1 )
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }

    private static class AvlNode<AnyType>
    {
            // Constructors
        AvlNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        AvlNode( AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            height   = 0;
        }

        AnyType           element;      // The data in the node
        AvlNode<AnyType>  left;         // Left child
        AvlNode<AnyType>  right;        // Right child
        int               height;       // Height
    }

      /** The tree root. */
    private AvlNode<AnyType> root;
}

/**
 * Exception class for access in empty containers
 * such as stacks, queues, and priority queues.
 * @author Mark Allen Weiss
 */
class UnderflowException extends RuntimeException
{
    /**
     * Construct this exception object.
     * @param message the error message.
     */
    public UnderflowException( String message )
    {
        super( message );
    }
}

class Wrapper<AnyType> {
	public AnyType value;
}

class PrintTreeAndArray {
	public String treeString;
	public WordBankOccArray treeToArray;

	public PrintTreeAndArray(String treeString, WordBankOccArray treeToArray) {
		this.treeString = treeString;
		this.treeToArray = treeToArray;
	}
}
