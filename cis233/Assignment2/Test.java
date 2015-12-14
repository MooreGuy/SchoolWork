public class Test {
	public static void main(String[] args) {
		System.out.println("Add " + testAdd());
		System.out.println("Multi Add " + testMultiAdd());
		System.out.println("Single Remove " + testRemove());
		System.out.println("Multi Remove " + testMultiRemove());
		System.out.println("balancedTree " + testBalancedTree());
	}

	private static boolean testAdd() {
		A2233GMooAVL<Integer> testTree = new A2233GMooAVL<Integer>();
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(2);

		return testTree.toString().equals("1 2 3");
	}

	private static boolean testMultiAdd() {
		A2233GMooAVL<Integer> testTree = new A2233GMooAVL<Integer>();
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(2);
		testTree.insert(2);

		return testTree.toString().equals("1 2 2 3");
	}

	private static boolean testRemove(){
		boolean working = true;
		A2233GMooAVL<Integer> testTree = new A2233GMooAVL<Integer>();
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(2);
		testTree.insert(2);

		testTree.remove(3);
		testTree.remove(2);

		return testTree.toString().equals("1 2");
	}

	public static boolean testMultiRemove() {
		A2233GMooAVL<Integer> testTree = new A2233GMooAVL<Integer>();
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(3);
		testTree.insert(3);
		testTree.insert(3);

		testTree.remove(3);
		testTree.remove(3);

		return testTree.toString().equals("1 3 3");

	}

	public static boolean testBalancedTree() {
		A2233GMooAVL<Integer> testTree = new A2233GMooAVL<Integer>();
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(2);
		testTree.insert(3);

		testTree.writeBalTree(true);

		testTree.printBalTree(true);

		System.out.println("Mode: ");
		System.out.println(testTree.findMode());

		return false;
	}
}
