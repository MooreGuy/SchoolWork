package cis233.a2;

import java.io.PrintWriter;
import java.io.FileNotFoundException;
import weiss.nonstandard.ItemNotFoundException;

public class A2233GMooAVL <AnyType extends Comparable<? super AnyType>> {
	A2233GMooBinaryNode<AnyType> root;

	class A2233GMooBinaryNode<AnyType> {
		AnyType element;
		A2233GMooBinaryNode<AnyType> left;
		A2233GMooBinaryNode<AnyType> right;
		int height;
		int count;

		A2233GMooBinaryNode(AnyType theElement) {
			element = theElement;
			left = right = null;
			height = 0;
			count = 1;
		}

		public String toString() {
			StringBuilder build = new StringBuilder();
			build.append("Data: ");
			build.append(element);

			build.append("    Height: ");
			build.append(height);

			build.append("    Balance: ");
			int leftHeight = (left == null) ? 0 : left.height;
			int rightHeight = (right == null) ? 0 : right.height;
			build.append(leftHeight - rightHeight);

			return build.toString();
		}
	}

	public A2233GMooAVL() {
		root = null;
	}

	protected void insert(AnyType x) {
		root = insert(x, root);
	}

	protected A2233GMooBinaryNode<AnyType> insert(AnyType x,
		A2233GMooBinaryNode<AnyType> t) {
		if(t == null)
			t = new A2233GMooBinaryNode<AnyType>(x);
		else if(x.compareTo(t.element) < 0)
			t.left = insert(x, t.left);
		else if(x.compareTo(t.element) > 0)
			t.right = insert(x, t.right);
		else
			t.count++;

		return balance(t);
	}

    private static final int ALLOWED_IMBALANCE = 1;

    private A2233GMooBinaryNode<AnyType>
		balance(A2233GMooBinaryNode<AnyType> t) {
        if( t == null )
            return t;

        if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
            if( height( t.left.left ) >= height( t.left.right ) )
                t = rotateWithLeftChild( t );
            else
                t = doubleRotateWithLeftChild( t );
        else
        if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
            if( height( t.right.right ) >= height( t.right.left ) )
                t = rotateWithRightChild( t );
            else
                t = doubleRotateWithRightChild( t );

        t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }

    private int height( A2233GMooBinaryNode<AnyType> t )
    {
        return t == null ? -1 : t.height;
    }

	public void printBalTree(boolean ascending) {
		if (root == null) {
			System.out.println("Tree is currently empty");
			return;
		}

		StringBuilder builder = new StringBuilder();
		balancedTree(root, builder, ascending);
		System.out.print(builder.toString());
	}

	public void printTree() {
		if (isEmpty()) {
			System.out.println( "Empty tree" );
		} else {
			printTree(root);
		}
	}

	public boolean isEmpty( ) {
        return root == null;
    }

	private void printTree(A2233GMooBinaryNode<AnyType> t) {
		if(t == null) {
			printTree(t.left);
			for(int x = 0; x < t.count; x++) {
				System.out.println(t.element);
			}
			printTree(t.right);
		}
	}

	public void writeBalTree(boolean ascending) {
		PrintWriter writer ;
		try {
			writer = new PrintWriter("A2233GMooAVLout.txt");
		} catch(FileNotFoundException fnf) {
			System.out.println("File not found exception.");
			return;
		}

		if (root == null) {
			writer.println("Tree is currently empty");
			writer.close();
			return;
		}

		StringBuilder builder = new StringBuilder();
		balancedTree(root, builder, ascending);

		writer.print(builder.toString());
		writer.close();
	}

	public void balancedTree(A2233GMooBinaryNode<AnyType> node,
		StringBuilder builder, boolean ascending) {

		if (node == null) {
			return;
		}

		A2233GMooBinaryNode<AnyType> firstDirection;
		A2233GMooBinaryNode<AnyType> secondDirection;
		if (ascending)  {
			firstDirection = node.left;
			secondDirection = node.right;
		}
		else {
			firstDirection = node.right;
			secondDirection = node.left;
		}

		balancedTree(firstDirection, builder, ascending);

		// One for each count.
		for (int x = 0; x < node.count; x++) {
			// Current node
			builder.append(node.toString());

			builder.append("\n\tLeft: ");
			if (node.left == null)
				builder.append("null");
			else
				builder.append(node.left.toString());

			builder.append("\n\tRight: ");
			if (node.right == null)
				builder.append("null");
			else
				builder.append(node.right.toString());

			builder.append("\n");
		}

		balancedTree(secondDirection, builder, ascending);
	}

	private A2233GMooBinaryNode<AnyType> find(AnyType x,
		A2233GMooBinaryNode<AnyType> t) {
		while(t != null) {
			if(x.compareTo(t.element) < 0)
				t = t.left;
			else if(x.compareTo(t.element) > 0)
				t = t.right;
			else
				return t;
		}

		return null;
	}

	public void remove(AnyType x) {
		remove(x, root);
	}

	protected A2233GMooBinaryNode<AnyType> remove(AnyType x,
		A2233GMooBinaryNode<AnyType> t) {
		if(t == null)
			throw new ItemNotFoundException(x.toString());
		if(x.compareTo(t.element) < 0)
			remove(x, t.left);
		else if(x.compareTo(t.element) > 0)
			remove(x, t.right);
		else
			t.count--;

		return balance(t);
	}

	public A2233GMooBinaryNode<AnyType> findMin(
		A2233GMooBinaryNode<AnyType> t) {
		if(t.left != null)
			findMin(t.left);

		return t;
	}

	public A2233GMooBinaryNode<AnyType> findMax(
		A2233GMooBinaryNode<AnyType> t) {
		if(t.right != null)
			findMin(t.right);

		return t;
	}

	public  A2233GMooBinaryNode<AnyType> removeMin(
		A2233GMooBinaryNode<AnyType> t) {
		if(t == null) {
			throw new ItemNotFoundException();
		} else if(t.left != null) {
			t.left.count = t.left.count - 1;
			removeMin(t.left);
			return t;
		}
		else
			return t.right;
	}

	String author() {
		return "Guy";
	}

	private A2233GMooBinaryNode<AnyType> highestCount(
		A2233GMooBinaryNode<AnyType> current,
		A2233GMooBinaryNode<AnyType> highest) {
		if (current.count > highest.count)
			highest = current;

		A2233GMooBinaryNode<AnyType> rightCount;
		A2233GMooBinaryNode<AnyType> leftCount;
		if (current.left != null) {
			leftCount = highestCount(current.left, highest);
			if (highest.count < leftCount.count)
				highest = leftCount;
		}
		if (current.right != null) {
			rightCount = highestCount(current.right, highest);
			if (highest.count < rightCount.count)
				highest = rightCount;
		}

		return highest;
	}

	public Result<AnyType> findMode() {
		A2233GMooBinaryNode<AnyType> mode = highestCount(root, root);
		return new AVLResult<AnyType>(mode.element, mode.count);
	}

	public static class AVLResult<ResultType extends
		Comparable<? super ResultType>> implements Result<ResultType>{
		private ResultType mode;
		private int count;

		public AVLResult(ResultType mode, int count) {
			this.mode = mode;
			this.count = count;
		}

		public int count() {
			return count;
		}

		public ResultType mode() {
			return mode;
		}
	}

	A2233GMooBinaryNode<AnyType> rotateWithLeftChild(
		A2233GMooBinaryNode<AnyType> k2) {

		A2233GMooBinaryNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;

        k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = Math.max( height( k1.left ), k2.height ) + 1;

		return k1;
	}

	A2233GMooBinaryNode<AnyType> rotateWithRightChild(
		A2233GMooBinaryNode<AnyType> k1) {

		A2233GMooBinaryNode<AnyType> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;

		k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = Math.max( height( k2.right ), k1.height ) + 1;

		return k2;
	}

	A2233GMooBinaryNode<AnyType> doubleRotateWithRightChild(
		A2233GMooBinaryNode<AnyType> k1) {
		k1.right = rotateWithLeftChild(k1.right);
		return rotateWithRightChild(k1);
	}

	A2233GMooBinaryNode<AnyType> doubleRotateWithLeftChild(
		A2233GMooBinaryNode<AnyType> k3) {
		k3.left = rotateWithLeftChild(k3.left);
		return rotateWithRightChild(k3);
	}
}
