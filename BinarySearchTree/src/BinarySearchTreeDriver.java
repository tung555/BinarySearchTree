
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BinarySearchTreeDriver {
	
	/**
	 * In-order traversal then print out the key
	 * @param <T> the type parameter
	 * @param bst
	 */
	private static <T extends Comparable<T>> void print(BinarySearchTree<T> bst) {
		System.out.print("In-order: ");
		bst.inOrder();
		System.out.print("\n");
	}
	/**
	 * Parse the integer then insert them into the declared BST.
	 * @param bst the BST
	 * @param input the file from the command line
	 */
	private static void insertFromFileInt(BinarySearchTree<Integer> bst, String input) {
		String val = "";
		for (int i = 0; i < input.length(); ++i) {
			if (input.charAt(i) != ' ') {
				val += input.charAt(i);
			} else {
				bst.insert(Integer.parseInt(val));
				val = "";
			}
		}
	}
	/**
	 * Parse the double then insert them into the declared BST.
	 * @param bst the BST
	 * @param input the file from the command line
	 */
	private static void insertFromFileDouble(BinarySearchTree<Double> bst, String input) {
		String val = "";
		for (int i = 0; i < input.length(); ++i) {
			if (input.charAt(i) != ' ') {
				val += input.charAt(i);
			} else {
				bst.insert(Double.parseDouble(val));
				val = "";
			}
		}
	}
	/**
	 * Parse the string then insert them into the declared BST.
	 * @param bst the BST
	 * @param input the file from the command line
	 */
	private static void insertFromFileString(BinarySearchTree<String> bst, String input) {
		String val = "";
		for (int i = 0; i < input.length(); ++i) {
			if (input.charAt(i) != ' ') {
				val += input.charAt(i);
			} else {
				bst.insert(val);
				val = "";
			}
		}
	}

	public static void main(String[] args) {
		
		String input = "";
		
		try {
			File inputFile = new File(args[0]);
			Scanner fileReader = new Scanner(inputFile);
			while (fileReader.hasNextLine()) {
				input = fileReader.nextLine();
			}
			input += " ";
			fileReader.close();
		} catch (ArrayIndexOutOfBoundsException aioob) {
			System.out.println("The file is not presented");
		} catch (FileNotFoundException fnf) {
			System.out.println("The file provided is not found");
		}
		
		Scanner kb = new Scanner(System.in);
		String type = "", command = "";
		Integer[] nums;
		boolean done = false;

		System.out.print("Enter list type (i - int, d - double, s - string): ");
		type = kb.next();
		while (!type.equalsIgnoreCase("i") && !type.equalsIgnoreCase("d") && !type.equalsIgnoreCase("s")) {
			System.out.println("Invalid type, please try again.");
			System.out.print("Enter list type (i - int, d - double, s - string): ");
			type = kb.next();
		}

		if (type.equalsIgnoreCase("i")) {
			Integer num;
			BinarySearchTree<Integer> bst = new BinarySearchTree<>();
			try {
				insertFromFileInt(bst, input);
			} catch (Exception e) {
				System.out.println("Something wrong with the input format");
			}

			while (!command.equalsIgnoreCase("q")) {
				if (!done) {
					System.out.println("Commands:\n" + "(i) - Insert Item\n"
							+ "(d) - Delete Item\n"
							+ "(p) - Print Tree\n"
							+ "(r) - Retrieve Item\n"
							+ "(l) - Count Leaf Nodes\n"
							+ "(s) - Find Single Parents\n"
							+ "(c) - Find Cousins\n"
							+ "(q) - Quit program\n");
					done = true;
				}

				System.out.print("Enter a command: ");
				command = kb.next();

				try {
					if (command.equalsIgnoreCase("i")) {
						print(bst);
						System.out.print("Enter a number to insert: ");
						num = kb.nextInt();
						bst.insert(num);
						print(bst);
					} else if (command.equalsIgnoreCase("d")) {
						print(bst);
						System.out.print("Enter a number to delete: ");
						num = kb.nextInt();
						bst.delete(num);
						print(bst);
					} else if (command.equalsIgnoreCase("p")) {
						print(bst);
					} else if (command.equalsIgnoreCase("r")) {
						print(bst);
						System.out.print("Enter a number to search: ");
						num = kb.nextInt();
						if (bst.retrieve(num)) {
							System.out.println("Item is present in the tree");
						} else {
							System.out.println("Item is not present in the tree");
						}
					} else if (command.equalsIgnoreCase("l")) {
						bst.getNumLeafNodes();
						System.out.print("The number of leaf nodes are " 
						+ bst.getCurrentNumLeafNodes() + "\n");
					} else if (command.equalsIgnoreCase("s")) {
						System.out.print("Single Parents: ");
						bst.getSingleParent();
						System.out.println();
					} else if (command.equalsIgnoreCase("c")) {
						print(bst);
						System.out.print("Enter a number: ");
						num = kb.nextInt();
						nums = new Integer[bst.getNodeLevel(num)];
						System.out.print(num + " cousins: ");
						bst.findCousins(nums.length, nums, 0, num, bst.findSibling(num));
						System.out.println();
					} else if (command.equalsIgnoreCase("q")) {
						System.exit(0);
					} else {
						System.out.println("Invalid command! Please try again.");
					}
				} catch (Exception e) {
					System.out.println("Invalid input. Please try again.");
					kb.nextLine();
				}
			}
		} else if (type.equalsIgnoreCase("d")) {
			Double num;
			BinarySearchTree<Double> bst = new BinarySearchTree<>();
			try {
				insertFromFileDouble(bst, input);
			} catch (Exception e) {
				System.out.println("Something wrong with the input format");
			}
			
			while (!command.equalsIgnoreCase("q")) {
				if (!done) {
					System.out.println("Commands:\n" + "(i) - Insert Item\n"
							+ "(d) - Delete Item\n"
							+ "(p) - Print Tree\n"
							+ "(r) - Retrieve Item\n"
							+ "(l) - Count Leaf Nodes\n"
							+ "(s) - Find Single Parents\n"
							+ "(c) - Find Cousins\n"
							+ "(q) - Quit program\n");
					done = true;
				}

				System.out.print("Enter a command: ");
				command = kb.next();

				try {
					if (command.equalsIgnoreCase("i")) {
						print(bst);
						System.out.print("Enter a number to insert: ");
						num = kb.nextDouble();
						bst.insert(num);
						print(bst);
					} else if (command.equalsIgnoreCase("d")) {
						print(bst);
						System.out.print("Enter a number to delete: ");
						num = kb.nextDouble();
						bst.delete(num);
						print(bst);
					} else if (command.equalsIgnoreCase("p")) {
						print(bst);
					} else if (command.equalsIgnoreCase("r")) {
						print(bst);
						System.out.print("Enter a number to search: ");
						num = kb.nextDouble();
						if (bst.retrieve(num)) {
							System.out.println("Item is present in the tree");
						} else {
							System.out.println("Item is not present in the tree");
						}
					} else if (command.equalsIgnoreCase("l")) {
						bst.getNumLeafNodes();
						System.out.print("The number of leaf nodes are " 
						+ bst.getCurrentNumLeafNodes() + "\n");
					} else if (command.equalsIgnoreCase("s")) {
						System.out.print("Single Parents: ");
						bst.getSingleParent();
						System.out.println();
					} else if (command.equalsIgnoreCase("c")) {
						print(bst);
						System.out.print("Enter a number: ");
						num = kb.nextDouble();
						nums = new Integer[bst.getNodeLevel(num)];
						System.out.print(num + " cousins: ");
						bst.findCousins(nums.length, nums, 0, num, bst.findSibling(num));
						System.out.println();
					} else if (command.equalsIgnoreCase("q")) {
						System.exit(0);
					} else {
						System.out.println("Invalid command! Please try again.");
					}
				} catch (Exception e) {
					System.out.println("Invalid input. Please try again.");
					kb.nextLine();
				}
			}
		} else if (type.equalsIgnoreCase("s")) {
			String str;
			BinarySearchTree<String> bst = new BinarySearchTree<>();
			try {
				insertFromFileString(bst, input);
			} catch (Exception e) {
				System.out.println("Something wrong with the input format");
			}

			while (!command.equalsIgnoreCase("q")) {
				if (!done) {
					System.out.println("Commands:\n" + "(i) - Insert Item\n"
							+ "(d) - Delete Item\n"
							+ "(p) - Print Tree\n"
							+ "(r) - Retrieve Item\n"
							+ "(l) - Count Leaf Nodes\n"
							+ "(s) - Find Single Parents\n"
							+ "(c) - Find Cousins\n"
							+ "(q) - Quit program\n");
					done = true;
				}

				System.out.print("Enter a command: ");
				command = kb.next();

				try {
					if (command.equalsIgnoreCase("i")) {
						print(bst);
						System.out.print("Enter a number to insert: ");
						str = kb.next();
						bst.insert(str);
						print(bst);
					} else if (command.equalsIgnoreCase("d")) {
						print(bst);
						System.out.print("Enter a number to delete: ");
						str = kb.next();
						bst.delete(str);
						print(bst);
					} else if (command.equalsIgnoreCase("p")) {
						print(bst);
					} else if (command.equalsIgnoreCase("r")) {
						print(bst);
						System.out.print("Enter a number to search: ");
						str = kb.next();
						if (bst.retrieve(str)) {
							System.out.println("Item is present in the tree");
						} else {
							System.out.println("Item is not present in the tree");
						}
					} else if (command.equalsIgnoreCase("l")) {
						bst.getNumLeafNodes();
						System.out.print("The number of leaf nodes are " 
						+ bst.getCurrentNumLeafNodes() + "\n");
					} else if (command.equalsIgnoreCase("s")) {
						System.out.print("Single Parents: ");
						bst.getSingleParent();
						System.out.println();
					} else if (command.equalsIgnoreCase("c")) {
						print(bst);
						System.out.print("Enter a number: ");
						str = kb.next();
						nums = new Integer[bst.getNodeLevel(str)];
						System.out.print(str + " cousins: ");
						bst.findCousins(nums.length, nums, 0, str, bst.findSibling(str));
						System.out.println();
					} else if (command.equalsIgnoreCase("q")) {
						System.exit(0);
					} else {
						System.out.println("Invalid command! Please try again.");
					}
				} catch (Exception e) {
					System.out.println("Invalid input. Please try again.");
					kb.nextLine();
				}
			}
		}
		kb.close();
	}
}
