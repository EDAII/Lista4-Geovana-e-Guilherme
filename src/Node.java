
public class Node {
	int value, height, color; //1-R 0-B 
	Node left, right;
	
	static final int BLACK = 0;
	static final int RED = 1;
	
	Node(int value) { 
	    this.value = value; 
	    height = 1; 
	} 
	
	Node(int value, int color) { 
	    this.value = value;
	    this.color = color;
	    height = 1; 
	} 
	  
}
