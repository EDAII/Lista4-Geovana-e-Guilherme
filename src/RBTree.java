
public class RBTree {
	Node root;
	
	RBTree(Node root){
		this.root = root;
	}
	
	// A utility function to get the height of the tree 
	int height(Node N) { 
		if (N == null) 
			return 0; 
  
		return N.height; 
	} 
    	  
	// A utility function to get maximum of two integers 
	int max(int a, int b) { 
		return (a > b) ? a : b; 
	} 
	  
    // A utility function to right rotate subtree rooted with y 
    // See the diagram given above. 
	Node rightRotate(Node y) { 
		Node x = y.left; 
		Node T2 = x.right; 
  
        // Perform rotation 
        x.right = y; 
        y.left = T2; 
  
        // Update heights 
        y.height = max(height(y.left), height(y.right)) + 1; 
        x.height = max(height(x.left), height(x.right)) + 1; 
        
        if (y == this.root) this.root = x;
  
        // Return new root 
        return x; 
    } 
	  
    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
	Node leftRotate(Node x) { 
		Node y = x.right; 
		Node T2 = y.left; 
  
        // Perform rotation 
        y.left = x; 
        x.right = T2; 
  
        //  Update heights 
        x.height = max(height(x.left), height(x.right)) + 1; 
        y.height = max(height(y.left), height(y.right)) + 1; 
  
        if (x == this.root) this.root = y;
        
        // Return new root 
        return y; 
    } 
  
  
	public void insert(Node root, int value) { 
    	
		repairTree(addNode(root, value));
    	
    }
    
    private Node addNode(Node node, int value) { 
    	  
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new Node(value, 1)); 
  
        if (value < node.value) 
            node.left = addNode(node.left, value); 
        else if (value > node.value) 
            node.right = addNode(node.right, value); 
        else // Duplicate values not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        /* return the (unchanged) node pointer */
        return node;
    }
    
    private void repairTree(Node node) {
    	if (getParent(node.value, this.root) == null) {
    		insert_case1(node);
    	} else if (getParent(node.value, this.root).color == 0) {
    		return;
    	} else if (getUncle(node).color == 1) {
    		insert_case3(node);
    	} else {
    		insert_case4(node);
    	}
    }
    
    private void insert_case1(Node n){
    	if (getParent(n.value, this.root) == null)
    		n.color = 0;
    }
    
    private void insert_case3(Node n){
    	getParent(n.value, this.root).color = 0;
		getUncle(n).color = 0;
		getGrandparent(n).color = 1;
		repairTree(getGrandparent(n));
    }
    
    private void insert_case4(Node n){
    	Node p = getParent(n.value, this.root);
     	Node g = getGrandparent(n);

	    if (n == g.left.right) {
	    	leftRotate(p);
	    	n = n.left;
	    } else if (n == g.right.left) {
	    	rightRotate(p);
	    	n = n.right; 
	    }

	    insert_case4step2(n);
    }
    
    private void insert_case4step2(Node n){
    	Node p = getParent(n.value, this.root);
    	Node g = getGrandparent(n);

    	if (n == p.left)
    		rightRotate(g);
    	else
    		leftRotate(g);
    	p.color = 0;
    	g.color = 1;
    }
    
    public Node getParent(int value, Node root) {
    	
    	if (root == null) return null;
    	
    	if (  (root.left != null && root.left.value == value) || 
				  (root.right != null && root.right.value == value))
    		return root;    		  
    	  
    	if (value < root.value)
    		return getParent(value, root.left);
    	else if (value > root.value)
    		return getParent(value, root.right);
    	
    	return null;
    }
    
    private Node getGrandparent(Node node) {
    	Node p = getParent(node.value, this.root);
    	 if (p == null)
    	  return null; // No parent means no grandparent
    	 return getParent(p.value, this.root); // null if parent is root
    	}

    private Node getSibling(Node node) {
    	Node p = getParent(node.value, this.root);
    	 if (p == null)
    	  return null; // No parent means no sibling
    	 if (node == p.left)
    	  return p.right;
    	 else
    	  return p.left;
    	}
    
    private Node getUncle(Node node) {
    	Node p = getParent(node.value, this.root);
    	Node g = getGrandparent(node);
    	if (g == null)
    		return null; // No grandparent means no uncle
    	return getSibling(p);
    }
    
}
