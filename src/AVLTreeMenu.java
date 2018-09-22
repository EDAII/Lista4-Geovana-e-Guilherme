import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AVLTreeMenu extends JFrame {

	private JPanel contentPane;
	private DrawTree applet = new DrawTree();

	public AVLTreeMenu(AVLTree tree) {
			
		setTitle("�rvores AVL");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			
		//Question section
		JLabel lblQuestion = new JLabel("Escolha a op��o");
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblQuestion);
			
		//separating label and first button
		contentPane.add(Box.createRigidArea(new Dimension(40,40)));

		JButton btnAdd = new JButton("Adicionar Elemento");
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callAddElementFrame(tree);
				
			}
		});
		contentPane.add(btnAdd);
		
		// separating first and second button
		contentPane.add(Box.createRigidArea(new Dimension(20,10)));
		
		JButton btnRandomAdd = new JButton("Adicionar Elemento Aleatorio");
		btnRandomAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnRandomAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callRandomAdd(tree);
			}
		});
		contentPane.add(btnRandomAdd);
			
		
		// separating buttons
		contentPane.add(Box.createRigidArea(new Dimension(20,10)));
		
		JButton btnPrintOrders = new JButton("Imprimir ordens (inorder, posorder, preorder)");
		btnPrintOrders.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnPrintOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callPrintOrder(tree);
			}
		});
		contentPane.add(btnPrintOrders);
			
		contentPane.add(applet);
		paintAVLTree(tree);
	}
	
	private void paintAVLTree(AVLTree tree) {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		int xSize = (int)tk.getScreenSize().getWidth() - 150;
		applet.init(tree, xSize);
		
		applet.repaint();
	}
	
	private void callAddElementFrame(AVLTree tree) {
		int value = Integer.parseInt(
				JOptionPane.showInputDialog(contentPane, "Digite o valor para adicionar", null)
		);
		
		tree.insert(tree.root, value);
		
		paintAVLTree(tree);	
	}
	
	private void callRandomAdd(AVLTree tree) {
		
		Random rnd = new Random();
		
		int value = rnd.nextInt(100);
		
		tree.insert(tree.root, value);
		
		paintAVLTree(tree);
	}

	private void callPrintOrder(AVLTree tree) {
		
		LinkedList<Integer> preorder = tree.getOrder(0, null, tree.root);
		LinkedList<Integer> inorder = tree.getOrder(1, null, tree.root);
		LinkedList<Integer> posorder = tree.getOrder(2, null, tree.root);
		
		String text = new String("Pre order: " + preorder.toString() + "\n In order: " + inorder.toString() + "\n Pos order: " + posorder.toString());
		
		JOptionPane.showMessageDialog(contentPane, text, "Order", JOptionPane.INFORMATION_MESSAGE);		
		
		
	}

}
