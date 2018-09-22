import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;

	public Menu() {
			
		setTitle("Árvores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.NORMAL); 
		setUndecorated(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			
		//Question section
		JLabel lblQuestion = new JLabel("Escolha qual árvore deseja trabalhar:");
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblQuestion);
			
		//separating label and first button
		contentPane.add(Box.createRigidArea(new Dimension(40,40)));

		JButton btnAVL= new JButton("AVL");
		btnAVL.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAVL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callAVLTreeMenu();
				
			}
		});
		contentPane.add(btnAVL);
		
		// separating first and second button
		contentPane.add(Box.createRigidArea(new Dimension(20,10)));
		
		JButton btnRB = new JButton("R&B");
		btnRB.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callRBTreeMenu();
			}
		});
		contentPane.add(btnRB);
			

	}
	
	private void callAVLTreeMenu() {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Node node = new Node(10);
					AVLTree tree = new AVLTree(node); 
			        tree.insert(tree.root, 30); 
					AVLTreeMenu frame = new AVLTreeMenu(tree);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
	}
	
	private void callRBTreeMenu() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Node node = new Node(10, 1);
					RBTree tree = new RBTree(node); 
			        tree.insert(tree.root, 30); 
					RBMenu frame = new RBMenu(tree);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		
	}
	

}
