
/**
 * Represents a node object which contains {@code T} type values.
 * 
 * @author Chihyuan Tung
 *
 * @param <T> any type that extends Comparable interface
 */
public class NodeType<T extends Comparable<T>> {
	
	public T key;
	public NodeType<T> left;
	public NodeType<T> right;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param info is the object to initialize info
	 */
	public NodeType(T key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Constructor of the class.
	 * 
	 * @param info is the object to initialize info
	 * @param left is the node object to initialize left 
	 * @param right is the node object to initialize right
	 */
	public NodeType(T key, NodeType<T> left, NodeType<T> right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Returns left
	 * @return left
	 */
	NodeType<T> getLeft() {
		return this.left;
	}
	
	/**
	 * Returns right
	 * @return right
	 */
	NodeType<T> getRight() {
		return this.right;
	}
	
	/**
	 * Initializes or modifies next with {@code left}.
	 * 
	 * @param left the object to initialize or modifies left
	 */
	void setLeft(NodeType<T> left) {
		this.left = left;
	}
	
	/**
	 * Initializes or modifies back with {@code right}.
	 * 
	 * @param right the object to initialize or modifies back
	 */
	void setRight(NodeType<T> right) {
		this.right = right;
	}
	
	/**
	 * Returns key
	 * 
	 * @return key
	 */
	T getKey() {
		return this.key;
	}
	
	/**
	 * Initializes of modifies info.
	 * 
	 * @param info the object to initializes or modifies info
	 */
	void setKey(T key) {
		this.key = key;
	}
}
