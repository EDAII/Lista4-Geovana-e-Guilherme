
public class Node {
	int value, height, color; //1-R 0-B 
	Node left, right;
	
	Node(int d) { 
	    value = d; 
	    height = 1; 
	} 
	
	Node(int d, int init) { 
	    value = d;
	    color = init;
	    height = 1; 
	} 
	  
}
