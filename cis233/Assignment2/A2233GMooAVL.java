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
	}

	public A2233GMooAVL() {
		root = null;
	}

	protected void insert(AnyType x) {
		root = insert(x, root);
	}

	protected A2233GMooBinaryNode<AnyType> insert(AnyType x, A2233GMooBinaryNode<AnyType> t) {
		if(t == null)
			t = new A2233GMooBinaryNode<AnyType>(x);
		else if(x.compareTo(t.element) < 0)
			t.left = insert(x, t.left);
		else if(x.compareTo(t.element) > 0)
			t.right = insert(x, t.right);
		else
			t.count++;
		return t;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		print(root, builder);
		return builder.toString().trim();
	}

	public void print(A2233GMooBinaryNode<AnyType> node, StringBuilder builder) {
		if (node == null) {
			return;
		}

		print(node.left, builder);

		// One for each count.
		for (int x = 0; x < node.count; x++) {
			builder.append(node.element);
			builder.append(" ");
		}

		print(node.right, builder);
	}

	private A2233GMooBinaryNode<AnyType> find(AnyType x, A2233GMooBinaryNode<AnyType> t) {
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

	protected A2233GMooBinaryNode<AnyType> remove(AnyType x, A2233GMooBinaryNode<AnyType> t) {
		if(t == null)
			// NOTE: TONY THE PONY
		if(x.compareTo(t.element) < 0)
			t.left = remove(x, t.left);
		else if(x.compareTo(t.element) > 0)
			t.right = remove(x, t.right);
		else if(t.left != null && t.right != null)
		{
			t.element = findMin(t.right).element;
			t.right = removeMin(t.right);
		}
		else
			t = (t.left != null) ? t.left : t.right;

		return t;
	}
}
