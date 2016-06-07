package scripts;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import scripts.Data.Vars;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JSpinner;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setTitle("skAIO Collector v1.00 by Stewiekidz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(22, 59, 260, 83);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblItemsToLootseparate = new JLabel("Items to Loot");
		lblItemsToLootseparate.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblItemsToLootseparate.setBounds(99, 30, 113, 16);
		contentPane.add(lblItemsToLootseparate);

		JCheckBox chckbxDmm = new JCheckBox("DMM?");
		chckbxDmm.setFont(new Font("Georgia", Font.PLAIN, 14));
		chckbxDmm.setBounds(185, 151, 113, 25);
		contentPane.add(chckbxDmm);

		JCheckBox chckbxWorldHop = new JCheckBox("World Hop?");
		chckbxWorldHop.setFont(new Font("Georgia", Font.PLAIN, 14));
		chckbxWorldHop.setBounds(185, 180, 113, 25);
		contentPane.add(chckbxWorldHop);

		JCheckBox chckbxMemberonlyIfChecked = new JCheckBox("Member?(only if checked above)");
		chckbxMemberonlyIfChecked.setFont(new Font("Georgia", Font.PLAIN, 14));
		chckbxMemberonlyIfChecked.setBounds(185, 210, 235, 25);
		contentPane.add(chckbxMemberonlyIfChecked);

		JLabel lblSkaioCollector = new JLabel("skAIO Collector");
		lblSkaioCollector.setForeground(Color.GREEN);
		lblSkaioCollector.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 32));
		lblSkaioCollector.setBounds(132, 283, 235, 35);
		contentPane.add(lblSkaioCollector);

		JTextPane txtpnseparateByCommas = new JTextPane();
		txtpnseparateByCommas.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtpnseparateByCommas.setBackground(SystemColor.menu);
		txtpnseparateByCommas.setText(
				"(Separate by commas) e.g. bones,meat,hide Make sure you do it exactly to work. *word*,*word* - No space");
		txtpnseparateByCommas.setBounds(32, 155, 113, 118);
		contentPane.add(txtpnseparateByCommas);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(324, 59, 82, 25);
		contentPane.add(spinner);

		JLabel lblRadiusOfLooting = new JLabel("Radius of Looting");
		lblRadiusOfLooting.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblRadiusOfLooting.setBounds(301, 30, 145, 16);
		contentPane.add(lblRadiusOfLooting);

		JLabel lblMaxPlayersBefore = new JLabel("Max Players before Hopping");
		lblMaxPlayersBefore.setFont(new Font("Cordia New", Font.PLAIN, 18));
		lblMaxPlayersBefore.setBounds(299, 92, 168, 16);
		contentPane.add(lblMaxPlayersBefore);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(324, 121, 82, 22);
		contentPane.add(spinner_1);

		JButton btnNewButton = new JButton("Start!");
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vars.maxPlayers = (int) spinner_1.getValue();
				Vars.lootRadius = (int) spinner.getValue();
				Vars.dmm = chckbxDmm.isSelected();
				Vars.hopWorlds = chckbxWorldHop.isSelected();
				Vars.mems = chckbxMemberonlyIfChecked.isSelected();
				Vars.itemsToLoot = new ArrayList<String>(Arrays.asList(textField.getText().split(",")));
				setVisible(false);
			}
		});
		btnNewButton.setBounds(185, 244, 97, 25);
		contentPane.add(btnNewButton);

	}
}
