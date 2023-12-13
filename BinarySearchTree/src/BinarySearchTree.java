
/**
 * This class represent a binary search tree object which the internal structure is nodes
 * @author Chihyuan Tung
 *
 * @param <T> type parameter
 */
public class BinarySearchTree<T extends Comparable<T>>{
	
	private NodeType<T> root;
	private int currentNumLeafNodes;
	
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * Given the key and the root of the tree, returns the node that 
	 * contains the same key in the tree if presented, otherwise, return 
	 * null.
	 * @param node the root of the tree
	 * @param key the key
	 * @return the node that contains the same key in {@code node}
	 */
	private NodeType<T> findNode(NodeType<T> node, T key) {
		while (node != null) {
			if (node.getKey().compareTo(key) > 0) {
				node = node.getLeft();
			} else if (node.getKey().compareTo(key) < 0) {
				node = node.getRight();
			} else {
				return node;
			}
		}
		return null;
	}
	
	/**
	 * Given the key, search the sibling of the given key if presented, 
	 * otherwise, return null. Sibling is the node that share the same 
	 * parent node.
	 * @param key the key of the node to be searched for sibling
	 * @return the key if its sibling node
	 */
	public T findSibling(T key) {
		if (key.compareTo(this.root.getKey()) == 0) {
			return null;
		}
		NodeType<T> temp = findUpperNode(this.root, key);
		if (temp.getLeft() == null || temp.getRight() == null) {
			return null;
		} else if (temp.getLeft().getKey().compareTo(key) == 0) {
			return temp.getRight().getKey();
		} else {
			return temp.getLeft().getKey();
		}
	}
	
	/**
	 * Given the root of the tree and the key, search for the parent 
	 * node of the node that contains {@code key} in the given tree. 
	 * Returns its parent node if presented, otherwise, returns null.
	 * @param node the root of the tree to be searched
	 * @param key the key of the the child node
	 * @return reference of the parent node if presented
	 */
	private NodeType<T> findUpperNode(NodeType<T> node, T key) {
		while (node != null) {
			if (node.getKey().compareTo(key) > 0) {
				if (node.getLeft().getKey().compareTo(key) == 0) {
					return node;
				} else {
					node = node.getLeft();
				}
			} else if (node.getKey().compareTo(key) < 0) {
				if (node.getRight().getKey().compareTo(key) == 0) {
					return node;
				} else {
					node = node.getRight();
				}
			} else {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Given the node and returns the predecessor of that node in 
	 * the in-order traversal.
	 * @param node the node whose predecessor to be searched
	 * @return reference to the predecessor node
	 */
	private NodeType<T> findPredecessor(NodeType<T> node) {
		NodeType<T> temp = node.getLeft();	
		while (temp.getRight() != null) {
			temp = temp.getRight();
		}
		return temp;
	}
 	
	/**
	 * The helper method of insert. Recursively search for the appropriate 
	 * position for the key to be inserted, once the position is found, insert 
	 * the key into the tree. 
	 * @param node the root of the tree to be inserted
	 * @param key the key to be inserted
	 */
	private void insertHelper(NodeType<T> node, T key) {
		if (node.getLeft() == null && key.compareTo(node.getKey()) < 0) {
			node.setLeft(new NodeType<T>(key));
			return;
		} else if (node.getRight() == null && key.compareTo(node.getKey()) > 0) {
			node.setRight(new NodeType<T>(key));
			return;
		} else if (key.compareTo(node.getKey()) < 0) {
			insertHelper(node.getLeft(), key);
		} else if (key.compareTo(node.getKey()) > 0) {
			insertHelper(node.getRight(), key);
		} else {
			System.out.println("The item already exsits in the tree.");
		}
	}
	/**
	 * Call insertHelper to insert {@code key}.
	 * @param key the key to be inserted.
	 */
	public void insert(T key) {
		if (this.root == null) {
			this.root = new NodeType<T>(key);
			return;
		} 
		
		NodeType<T> temp = this.root;
		insertHelper(temp, key);
	}
	
	/**
	 * Given the key and delete it from the tree, and handle all the 
	 * possible deletion scenarios. If the root is null, print message 
	 * then return. If the tree only contains the root and the key 
	 * of the root equals to {@code key}, set root to {@code null}. 
	 * If the root only have one child and {@code key} equals to the 
	 * key of the root, make root reference to its child node. If the 
	 * node is a leaf node, set its parent to null. If the node only has 
	 * one child, find its parent node and set its parent node's child 
	 * to the child of the node node to be deleted. Finally, if the node 
	 * has two children, then find its predecessor in-order then delete
	 *  its predecessor, then set the key of the node to its predecessor's 
	 *  key.
	 * @param key
	 */
	public void delete(T key) {
		//if the root is empty
		if (this.root == null) {
			System.out.println("You cannot delete from an empty tree");
			return;
		//if key == the key of the root and the root has no child
		} else if (this.root.getKey().compareTo(key) == 0 && this.root.getLeft() == null 
				&& this.root.getRight() == null) {
			this.root = null;
			return;
		//if key == the key of the root and the root only has one child
		} else if (this.root.getKey().compareTo(key) == 0 
				&& (this.root.getLeft() == null || this.root.getRight() == null)) {
			if (this.root.getRight() == null) {
				this.root = this.root.getLeft();
			} else {
				this.root = this.root.getRight();
			}
			return;
		}
		
		//find the node to be deleted
		NodeType<T> target = findNode(this.root, key); 
		//if the key is not presented in the tree
		if (target == null) {
			System.out.println("The item is not present in the tree");
			return;
		}
		//find the parent node
		NodeType<T> upper = findUpperNode(this.root, key);
		//if the node is a leaf node
		if (target.getLeft() == null && target.getRight() == null) {
			if (target.getKey().compareTo(upper.getKey()) > 0) {
				upper.setRight(null);
			} else {
				upper.setLeft(null);
			}
			return;
		//if it only has one child
		} else if (target.getLeft() == null) {
			if (target.getKey().compareTo(upper.getKey()) > 0) {
				upper.setRight(target.getRight());
			} else {
				upper.setLeft(target.getRight());
			}
			return;
		//if it only has one child
		} else if (target.getRight() == null) {
			if (target.getKey().compareTo(upper.getKey()) > 0) {
				upper.setRight(target.getLeft());
			} else {
				upper.setLeft(target.getLeft());
			}
			return;
		//if it has two children
		} else {
			//find its predecessor
			NodeType<T> predecessor = findPredecessor(target);
			//store the key of the predecessor
			T preKey = predecessor.getKey();
			//self call to delete the predecessor
			delete(preKey);
			//replace the key to be deleted to it predecessor's key
			target.setKey(preKey);
		}
	}
	
	/**
	 * Given the {@code item} and check if it is presented in the tree. 
	 * If yes, return true, else return false. Utilizing the property of 
	 * a binary search tree, if the key if greater, go right, else go left, 
	 * until finding the key or reaching the end.
	 * @param item the item to be searched
	 * @return {@code true} if found, {@code false} otherwise
	 */
	public boolean retrieve(T item) {
		NodeType<T> temp = this.root;
		while (temp != null) {
			if (temp.getKey().compareTo(item) > 0) {
				temp = temp.getLeft();
			} else if (temp.getKey().compareTo(item) < 0) {
				temp = temp.getRight();
			} else {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Helper method of inOrder. Conduct a in-order traversal to the tree 
	 * and print the keys. Visiting the left first, then the root, then the right.
	 * @param node the root of the node to be traversed
	 */
	private void inOrderHelper(NodeType<T> node) {
		if (node == null) {
			return;
		}
		
		inOrderHelper(node.getLeft());
		System.out.print(node.getKey() + " ");
		inOrderHelper(node.getRight());
	}
	/**
	 * Call inOrderHelper
	 */
	public void inOrder() {
		NodeType<T> temp = this.root;
		inOrderHelper(temp);
	}
	
	/**
	 * Helper method of the getSingleParent. Conduct an in-order traversal and 
	 * print out all the node that only has one of the children.
	 * @param node the root of the tree
	 */
	private void getSingleParentHelper(NodeType<T> node) {
		if (node == null) {
			return;
		}

		getSingleParentHelper(node.getLeft());
		if ((node.getLeft() == null && node.getRight() != null) 
				|| (node.getLeft() != null && node.getRight() == null)) {
			System.out.print(node.getKey() + " ");
		}
		getSingleParentHelper(node.getRight());
	}
	/**
	 * Call getSingleParentHelper
	 */
	public void getSingleParent() {
		NodeType<T> temp = this.root; 
		this.getSingleParentHelper(temp);
	}
	
	/**
	 * Returns {@code currentNumLeafNodes}
	 * @return the number of leaf nodes
	 */
	public int getCurrentNumLeafNodes() {
		return this.currentNumLeafNodes;
	}
	/**
	 * Conduct a in-order traversal, if the node has no child, {@code numLeafNode} ++. 
	 * @param node the root of the tree
	 */
	private void getNumLeafNodesHelper(NodeType<T> node) {
		if (node == null) {
			return;
		}

		getNumLeafNodesHelper(node.getLeft());
		if (node.getLeft() == null && node.getRight() == null) {
			this.currentNumLeafNodes += 1;
		}
		getNumLeafNodesHelper(node.getRight());
	}
	/**
	 * Set {@code numLeafNodes} to 0 then start counting.
	 */
	public void getNumLeafNodes() {
		NodeType<T> temp = this.root;
		this.currentNumLeafNodes = 0;
		this.getNumLeafNodesHelper(temp);
	}
	
	/**
	 * Given a key then returns the level which the key belonging in the tree.
	 * @param key the key
	 * @return the level belonging
	 */
	public int getNodeLevel(T key) {
		int level = 0;
		NodeType<T> temp = this.root;
		while (temp != null) {
			if (temp.getKey().compareTo(key) > 0) {
				temp = temp.getLeft();
				level += 1;
			} else if (temp.getKey().compareTo(key) < 0) {
				temp = temp.getRight();
				level += 1;
			} else {
				return level;
			}
		}
		return -1;
	}
	/**
	 * This method try all possible paths of getting the cousins.
	 * @param arr contains the binary string output
	 * @return the key of possible cousin nodes
	 */
	private T findCousinsHelper(Integer[] arr) {
		NodeType<T> temp = this.root;
		for (int i = 0; i < arr.length && temp != null; ++i) {
			if (arr[i] == 0) {
				temp = temp.getLeft();
			} else {
				temp = temp.getRight();
			}
		}
		if (temp != null) {
			return temp.getKey();
		} else {
			return null;
		}
	}
	/**
	 * Find all the cousins of the targeting node, the algorithm details are 
	 * going to be written in the README.md.
	 * @param n the length of the binary string
	 * @param arr the array to store the output
	 * @param i starting from 0
	 * @param self the key of the targeting node
	 * @param sibling the key of the sibling of targeting node  
	 */
	public void findCousins(int n, Integer[] arr, int i, T self, T sibling) {
		if (i == n) {
			T cousin = findCousinsHelper(arr);
			if (sibling == null) {
				if (cousin != null && cousin.compareTo(self) != 0) {
					System.out.print(cousin + " ");
				}
			} else {
				if (cousin != null && cousin.compareTo(self) != 0 && cousin.compareTo(sibling) != 0) {
					System.out.print(cousin + " ");
				}
			}
			return;
		}
		
		arr[i] = 0;
		findCousins(n, arr, i+1, self, sibling);
		arr[i] = 1;
		findCousins(n, arr, i+1, self, sibling);
	}
 }
