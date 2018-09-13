import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Node node = new Node(10);
					Tree tree = new Tree(node);
			        tree.root = tree.insert(tree.root, 10); 
			        tree.root = tree.insert(tree.root, 20); 
			        tree.root = tree.insert(tree.root, 30); 
			        tree.root = tree.insert(tree.root, 40); 
			        tree.root = tree.insert(tree.root, 50); 
			        tree.root = tree.insert(tree.root, 25); 
					Menu frame = new Menu(tree);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
