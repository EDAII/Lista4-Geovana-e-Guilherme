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
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Menu extends JFrame {

	private JPanel contentPane;
	private DrawTree applet = new DrawTree();

	public Menu(Tree tree) {
			
		setTitle("Árvores AVL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
			
		//Question section
		JLabel lblQuestion = new JLabel("Escolha a opção");
		lblQuestion.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblQuestion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblQuestion);
			
		//separating label and first button
		contentPane.add(Box.createRigidArea(new Dimension(40,40)));
			
		JButton btnShow = new JButton("Atualizar árvore");
		btnShow.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applet.repaint();
			}
		});
		contentPane.add(btnShow);
			
		// separating first and second button
		contentPane.add(Box.createRigidArea(new Dimension(20,10)));
			
		JButton btnAdd = new JButton("Adicionar Elemento");
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callAddElementFrame(tree);
				
			}
		});
		contentPane.add(btnAdd);
			
			
		contentPane.add(applet);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int)tk.getScreenSize().getWidth() / 2);
		applet.init(tree.root, xSize - 50);
	}
	
	private void callAddElementFrame(Tree tree) {
		int value = Integer.parseInt(
				JOptionPane.showInputDialog(contentPane, "Digite o valor para adicionar", null)
		);
		
		tree.insert(tree.root, value);		
		
	}
}
