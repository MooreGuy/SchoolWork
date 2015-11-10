public class Test {
	public static void main(String[] args) {
		System.out.println(testAdd());
		System.out.println(testMultiAdd());
	}

	private static boolean testAdd() {
		A2233GMooAVL<Integer> testTree = new A2233GMooAVL<Integer>();
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(2);

		System.out.println(testTree);

		return testTree.toString().equals("1 2 3");
	}

	private static boolean testMultiAdd() {
		A2233GMooAVL<Integer> testTree = new A2233GMooAVL<Integer>();
		testTree.insert(1);
		testTree.insert(3);
		testTree.insert(2);
		testTree.insert(2);

		System.out.println(testTree);

		return testTree.toString().equals("1 2 2 3");
	}	
}
