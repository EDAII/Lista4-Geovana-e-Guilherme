
public class RBTree {
	Node root;
	
	Node lastAdd = null;
	
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
	Node rightRotate(Node node) { 
		Node lChild = node.left; 
		Node lChild_right = lChild.right; 
  
        // Perform rotation 
        lChild.right = node; 
        node.left = lChild_right; 
  
        // Update heights 
        node.height = max(height(node.left), height(node.right)) + 1; 
        lChild.height = max(height(lChild.left), height(lChild.right)) + 1; 
        
        if (node == this.root) this.root = lChild;
  
        // Return new root 
        return lChild; 
    } 
	  
    // A utility function to left rotate subtree rooted with x 
    // See the diagram given above. 
	Node leftRotate(Node node) { 
		Node rChild = node.right; 
		Node rChild_left = rChild.left; 
  
        // Perform rotation 
        rChild.left = node; 
        node.right = rChild_left; 
  
        //  Update heights 
        node.height = max(height(node.left), height(node.right)) + 1; 
        rChild.height = max(height(rChild.left), height(rChild.right)) + 1; 
  
        if (node == this.root) this.root = rChild;
        
        // Return new root 
        return rChild; 
    } 
  
  
	public void insert(Node root, int value) { 
    	
		addNode(root, value);
		
		repairTree(this.lastAdd);
		this.lastAdd = null;	
    	
    }

    
    private Node addNode(Node node, int value) { 
    	  
        /* 1.  Perform the normal BST insertion */
        if (node == null) {
        	
        	Node newNode = new Node(value, 1);
        	
        	this.lastAdd = newNode;
        	
        	if(this.root == null) this.root = newNode;
        	
        	return newNode; 
        }
        
        
        if (value < node.value) 
            node.left = addNode(node.left, value); 
        else if (value > node.value) 
            node.right = addNode(node.right, value); 
        else { // Duplicate values not allowed 
        	this.lastAdd = null;
        	return node; 
        }
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        /* return the (unchanged) node pointer */
        return node;
    }
    
    private void repairTree(Node node) {
    	
    	// node is the inserted node
    	
    	Node parent = getParent(node.value, this.root);
    	Node uncle = getUncle(node);
    	
    	if (parent == null) {
    		insert_case1(node);
    	} else if (parent.color == Node.BLACK) {
    		return; // case 2, nothing to do
    	} else if (uncle != null && uncle.color == Node.RED) {
    		insert_case3(node);
    	} else { // parent is red and uncle is black
    		insert_case4(node);
    	}
    }
    
    private void insert_case1(Node n){
    	if (getParent(n.value, this.root) == null)
    		n.color = Node.BLACK;
    }
    
    private void insert_case3(Node n){
    	getParent(n.value, this.root).color = Node.BLACK;
		getUncle(n).color = Node.BLACK;
		
		Node gp = getGrandparent(n);
		gp.color = Node.RED;
		
		repairTree(gp);
		
    }
    
    private void insert_case4(Node n){
    	Node parent = getParent(n.value, this.root);
     	Node gParent = getGrandparent(n);
     	
     	boolean rotated = false;

	    if (gParent.left != null && gParent.left.right != null &&  n == gParent.left.right) {
	    	gParent.left = leftRotate(parent);
	    	rotated = true;
	    } else if (gParent.right != null && gParent.right.left != null &&n == gParent.right.left) {
	    	gParent.right = rightRotate(parent);
	    	rotated = true;
	    }

	    
	    if (rotated)
	    	insert_case4step2(parent);
	    else
	    	insert_case4step2(n);
    }
    
    private void insert_case4step2(Node n){
    	Node parent = getParent(n.value, this.root);
    	Node gParent = getGrandparent(n);
    	Node greatGParent = getGrandparent(parent);

    	Node temp = null;
    	if (n == parent.left) 
    		temp = rightRotate(gParent);
    	else 
    		temp = leftRotate(gParent);
    	
    	if (greatGParent != null) {
			
			boolean isLeft = false;
			
			if (greatGParent.left != null && greatGParent.left == gParent)
				isLeft = true;
			
			if (isLeft)
				greatGParent.left = temp;
			else
				greatGParent.right = temp;
		} 
    	
    	parent.color = Node.BLACK;
    	gParent.color = Node.RED;
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
