/**
 * Guy Moore
 * CIS 232
 * Lab 8
 * Instructor: Randy Scovil
 */
import java.util.Stack;

public class L8_232_GMoo {
	public static void main(String[] args) {
		for (int x = 1; x < 13; x++) {
			System.out.println("Factorial " + x + ": " + factorial(x));
		}
	}

	public static int factorial(int base) {
		Stack<Integer> factorialStack = new Stack<Integer>();
		for (; base > 1; base--) {
			factorialStack.push(base);
		}

		int factorial = 1;
		while (!factorialStack.empty()) {
			factorial *= factorialStack.pop();
		}

		return factorial;
	}
}
