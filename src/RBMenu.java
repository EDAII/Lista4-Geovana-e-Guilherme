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
public class RBMenu extends JFrame {

	private JPanel contentPane;
	private DrawTree applet = new DrawTree();

	public RBMenu(RBTree tree) {
			
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
			
			
		contentPane.add(applet);
		paintRBTree(tree);
	}

	private void paintRBTree(RBTree tree) {

		Toolkit tk = Toolkit.getDefaultToolkit();

		int xSize = (int) tk.getScreenSize().getWidth() - 150;
		applet.init(tree, xSize);

		applet.repaint();
	}

	private void callAddElementFrame(RBTree tree) {
		int value = Integer.parseInt(JOptionPane.showInputDialog(contentPane, "Digite o valor para adicionar", null));

		tree.insert(tree.root, value);

		paintRBTree(tree);
	}

	private void callRandomAdd(RBTree tree) {

		Random rnd = new Random();

		int value = rnd.nextInt(100);

		tree.insert(tree.root, value);

		paintRBTree(tree);
	}



}
