
public class Tree {
	Node root;
	
	Tree(Node root){
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
  
        // Return new root 
        return y; 
    } 
  
    // Get Balance factor of node N 
    int getBalance(Node N) { 
        if (N == null) 
            return 0; 
  
        return height(N.left) - height(N.right); 
    } 
  
    Node insert(Node node, int value) { 
  
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new Node(value)); 
  
        if (value < node.value) 
            node.left = insert(node.left, value); 
        else if (value > node.value) 
            node.right = insert(node.right, value); 
        else // Duplicate values not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), 
                              height(node.right)); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node); 
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if (balance > 1 && value < node.left.value) 
            return rightRotate(node); 
  
        // Right Right Case 
        if (balance < -1 && value > node.right.value) 
            return leftRotate(node); 
  
        // Left Right Case 
        if (balance > 1 && value > node.left.value) { 
            node.left = leftRotate(node.left); 
            return rightRotate(node); 
        } 
  
        // Right Left Case 
        if (balance < -1 && value < node.right.value) { 
            node.right = rightRotate(node.right); 
            return leftRotate(node); 
        } 
  
        /* return the (unchanged) node pointer */
        return node; 
    } 
}
