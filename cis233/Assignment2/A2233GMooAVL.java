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

		return t;
	}

	protected A2233GMooBinaryNode<AnyType> findMin(
		A2233GMooBinaryNode<AnyType> t) {
		if(t.left != null)
			findMin(t.left);

		return t;
	}

	protected A2233GMooBinaryNode<AnyType> removeMin(
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
}
