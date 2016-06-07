package scripts.skcowhide;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import scripts.skcowhide.Data.Location;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gui extends JFrame {

	private JPanel contentPane;

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
		setTitle("skCowhide Looter v2.02");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("skCowhide Looter");
		lblNewLabel.setFont(new Font("Consolas", Font.ITALIC, 24));
		lblNewLabel.setBounds(118, 23, 215, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLocation.setBounds(182, 71, 79, 14);
		contentPane.add(lblLocation);
		
		final JComboBox comboBox = new JComboBox(Location.values());
		comboBox.setBounds(153, 101, 141, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				skCowhideLooter.field = (Location) comboBox.getSelectedItem();
				setVisible(false);
				
			}
		});
		btnNewButton.setBounds(182, 181, 89, 23);
		contentPane.add(btnNewButton);
	}
}
