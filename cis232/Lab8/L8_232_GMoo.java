/**
 * Guy Moore
 * CIS 232
 * Lab 8
 * Instructor: Randy Scovil
 */
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import javax.swing.JFileChooser;

public class L8_232_GMoo {
	public static void main(String[] args) {
		for (int x = 1; x < 13; x++) {
			System.out.println("Factorial " + x + ": " + factorial(x));
		}

		File startDirectory = chooseDirectory();
		List<String> pdfPathNames = recursivePDFSpider(startDirectory);
		for (String name : pdfPathNames) {
			System.out.println(name);
		}

		startDirectory = chooseDirectory();
		pdfPathNames = nonRecursivePDFSpider(startDirectory);
		for (String name : pdfPathNames) {
			System.out.println(name);
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

	public static File chooseDirectory() {
		JFileChooser jfc = new JFileChooser();

		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (jfc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			System.exit(0);
		}

		return jfc.getSelectedFile();
	}

	public static ArrayList<String> recursivePDFSpider(File startDirectory) {
		ArrayList<String> pdfPathNames = new ArrayList<String>();

		File[] list = startDirectory.listFiles();
		for (File curFile : list) {
			if (curFile.isDirectory()) {
				pdfPathNames.addAll(recursivePDFSpider(curFile));
			} else if(curFile.toPath().toString().endsWith(".pdf")) {
				pdfPathNames.add(curFile.getAbsolutePath());
			}
		}

		return pdfPathNames;
	}

	public static ArrayList<String> nonRecursivePDFSpider(File startDirectory) {
		ArrayList<String> pdfPathNames = new ArrayList<String>();
		Stack<File> directories = new Stack<File>();

		File[] list = startDirectory.listFiles();
		for (File currentFile : list) {
			if (currentFile.isDirectory()) {
				directories.push(currentFile);
			} else if(currentFile.toPath().toString().endsWith(".pdf")) {
				pdfPathNames.add(currentFile.getAbsolutePath());
			}
		}

		while(directories.empty()) {
			list = directories.pop().listFiles();

			for (File currentFile : list) {
				if (currentFile.isDirectory()) {
					directories.push(currentFile);
				} else if(currentFile.toPath().toString().endsWith(".pdf")) {
					pdfPathNames.add(currentFile.getAbsolutePath());
				}
			}
		}

		return pdfPathNames;
	}
}
