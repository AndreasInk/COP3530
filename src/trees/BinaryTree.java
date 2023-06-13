package trees;

public class BinaryTree<E> {
	private static class BinaryTreeNode<E> {
		final private E element;
		private BinaryTreeNode<E> left, right;
		
		public BinaryTreeNode(E e) {
			element = e;
			left = right = null;
		}
		
		public E getElement() { return element; }
		//public void setElement(E e) { element = e; }
		public BinaryTreeNode<E> getLeft() { return left; }
		public BinaryTreeNode<E> getRight() { return right; }
		public String toString() { return element.toString(); }
	}
	
	private BinaryTreeNode<E> root;
	
	public BinaryTree() { }
	public boolean isEmpty() { return root == null; }
	public BinaryTreeNode<E> getRoot() { return root; }
	
	//========================================================
	public void printPreOrder() {
		preOrder(root);
	}
		
	private void preOrder(BinaryTreeNode<E> n) {
		if( n == null )
			return;
		
		System.out.print("<" + n.getElement() + "> " );
		preOrder(n.getLeft());
		preOrder(n.getRight());
	}
	//========================================================
	public void printInOrder() {
		inOrder(root);
	}
		
	private void inOrder(BinaryTreeNode<E> n) {
		if( n == null )
			return;
		
		inOrder(n.getLeft());
		System.out.print("<" + n.getElement() + "> " );
		inOrder(n.getRight());
	}
	//========================================================
	public void printPostOrder() {
		postOrder(root);
	}
		
	private void postOrder(BinaryTreeNode<E> n) {
		if( n == null )
			return;
		
		postOrder(n.getLeft());
		postOrder(n.getRight());
		System.out.print("<" + n.getElement() + "> " );
	}
	//========================================================
	public void insert(E e, String path) {
		if( e == null || path == null )
			throw new IllegalArgumentException("Unable to create a node. Missing information.");

		if( isEmpty() && path.equals("root") ) {
			root = new BinaryTreeNode<>(e);
			return;
		}

		for( int pos = 0; pos < path.length(); pos++)
			if( path.charAt(pos) != '0' && path.charAt(pos) != '1')
				throw new IllegalArgumentException("Invalid path string!");
		
		BinaryTreeNode<E> current = root, newNode = new BinaryTreeNode<>(e);
		
		for( int pos = 0; pos < path.length() - 1; pos++ )
			current = (path.charAt(pos) == '0') ? current.left : current.right;
		
		if( path.charAt( path.length() - 1 ) == '0' ) {
			if( current.left != null )
				throw new IllegalArgumentException("Some other node already exists. Insertion failed");
			
			current.left = newNode;
		}
		else {
			if( current.right != null )
				throw new IllegalArgumentException("Some other node already exists. Insertion failed");
			
			current.right = newNode;
		}
	}
	//========================================================
	public int countNodes(BinaryTreeNode<E> n) {
		if( n == null )
			return 0;

		return countNodes(n.left) + countNodes(n.right) + 1;
	}
	//========================================================
}
