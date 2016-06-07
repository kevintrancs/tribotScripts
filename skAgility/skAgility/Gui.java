package scripts.skAgility;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import scripts.skAgility.Data.Rooftops;
import scripts.skAgility.Handler.Bank;

public class Gui extends JFrame {


	private JPanel contentPane;
	private JTextField foodBox;

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
		setTitle("skAgility Rooftops by Stewiekidz");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("skAgility Rooftops");
		lblNewLabel.setFont(new Font("Source Code Pro", Font.BOLD, 18));
		lblNewLabel.setBounds(114, 11, 233, 23);
		contentPane.add(lblNewLabel);

		final JComboBox comboBox = new JComboBox(Rooftops.values());
		comboBox.setBounds(138, 58, 160, 20);
		contentPane.add(comboBox);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(189, 45, 46, 14);
		contentPane.add(lblLocation);

		foodBox = new JTextField();
		foodBox.setBounds(169, 119, 86, 20);
		contentPane.add(foodBox);
		foodBox.setColumns(10);

		JLabel lblFoodNametype = new JLabel("Food name (Type it exactly)");
		lblFoodNametype.setBounds(147, 100, 245, 14);
		contentPane.add(lblFoodNametype);

		JButton start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				skAgility.rooftop = (Rooftops) comboBox.getSelectedItem();
				Bank.food = foodBox.getText();
				setVisible(false);

			}
		});
		start.setFont(new Font("Sakkal Majalla", Font.PLAIN, 18));
		start.setBounds(169, 202, 89, 23);
		contentPane.add(start);
	}

}
