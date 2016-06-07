package scripts;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.tribot.api.General;

import scripts.Variables.Variables;
import scripts.data.Location;
import scripts.data.LogType;
import scripts.framework.GuiSet;
import scripts.framework.Task;
import scripts.tasks.Bank;
import scripts.tasks.LightLogs;

public class FireMakerGui extends GuiSet {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FireMakerGui frame = new FireMakerGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JComboBox log;
	private JComboBox spot;
	private JButton start;

	/**
	 * Create the frame.
	 */
	public FireMakerGui() {
		setTitle("skFiremaker AIO");
		init();
	}

	// declare variables here

	private void init() {

		log = new JComboBox(LogType.values());
		spot = new JComboBox(Location.values());
		start = new JButton("Start");

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 490, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSkfiremakerAio = new JLabel("skFiremaker AIO ");
		lblSkfiremakerAio.setForeground(Color.RED);
		lblSkfiremakerAio.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblSkfiremakerAio.setBounds(170, 13, 130, 16);
		contentPane.add(lblSkfiremakerAio);

		JLabel lblVersion = new JLabel("Version: 0.1");
		lblVersion.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 14));
		lblVersion.setBounds(192, 42, 95, 16);
		contentPane.add(lblVersion);

		JLabel lblCreatedByStewiekidz = new JLabel("Created by Stewiekidz");
		lblCreatedByStewiekidz.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 14));
		lblCreatedByStewiekidz.setBounds(157, 70, 159, 16);
		contentPane.add(lblCreatedByStewiekidz);

		log.setBounds(157, 122, 159, 22);
		contentPane.add(log);

		start.setBounds(192, 230, 97, 25);
		contentPane.add(start);

		spot.setBounds(157, 178, 159, 22);
		contentPane.add(spot);

		JLabel lblLog = new JLabel("Log");
		lblLog.setBounds(220, 93, 56, 16);
		contentPane.add(lblLog);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(208, 157, 56, 16);
		contentPane.add(lblLocation);

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishSetUp(e);
				setVisible(false);
			}
		});

	}

	@Override
	public ArrayList<Task> getUserTasks() {
		ArrayList<Task> tasks = new ArrayList<>();
		Variables.getVars().location = (Location) spot.getSelectedItem();
		Variables.getVars().logToBurn = (LogType) log.getSelectedItem();
		Variables.getVars().bestLane = Variables.getVars().location.getTile();
		Variables.getVars().hasLane = true;
		tasks.add(new Bank());
		tasks.add(new LightLogs());
		General.println("Location: " + Variables.getVars().location);
		General.println("Log: " + Variables.getVars().logToBurn);
		return tasks;
	}
}
