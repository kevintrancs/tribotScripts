package scripts;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.tribot.api.General;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;

import scripts.data.Chest;
import scripts.data.CustomNpc;
import scripts.data.Mode;
import scripts.data.Npc;
import scripts.data.Stalls;
import scripts.data.Variables;
import scripts.framework.GuiSet;
import scripts.framework.Task;
import scripts.tasks.Antiban;
import scripts.tasks.Bank;
import scripts.tasks.EatFood;
import scripts.tasks.LootChest;
import scripts.tasks.PickPocketCustomNpc;
import scripts.tasks.PickPocketSetNpc;
import scripts.tasks.PowerDrop;
import scripts.tasks.RunFromCombat;
import scripts.tasks.StealStall;

public class ThieverGui extends GuiSet {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThieverGui frame = new ThieverGui();
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
	public ThieverGui() {
		init();
	}

	private JComboBox sOptions;
	private JComboBox nOptions;
	private JComboBox cOptions;
	private JComboBox mOptions;
	private JCheckBox chckbxNewCheckBox;
	private JTextField foodName;
	private JButton startIt;
	private JTextField custNpcName;
	private JSpinner radiusArea;
	private JTextField cfoodName;

	@SuppressWarnings("unchecked")
	private void init() {
		mOptions = new JComboBox(Mode.values());
		sOptions = new JComboBox(Stalls.values());
		nOptions = new JComboBox(Npc.values());
		cOptions = new JComboBox(Chest.values());
		chckbxNewCheckBox = new JCheckBox("Power Thieve?");
		startIt = new JButton("Start Script!");
		radiusArea = new JSpinner();

		setTitle("skThiever AIO ");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 457, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 420, 227);
		contentPane.add(tabbedPane);

		/**
		 * 
		 * Welcome Screen
		 * 
		 * 
		 * 
		 */

		JPanel panel = new JPanel();
		tabbedPane.addTab("Home", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("skThiever AIO");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 16));
		lblNewLabel.setBounds(145, 13, 116, 16);
		panel.add(lblNewLabel);

		JTextArea txtrVerison = new JTextArea();
		txtrVerison.setBackground(UIManager.getColor("TextField.disabledBackground"));
		txtrVerison.setFont(new Font("Arial", Font.PLAIN, 12));
		txtrVerison.setRows(4);
		txtrVerison.setText("Version: 3.0\r\nCreated by Stewiekidz\r\n");
		txtrVerison.setBounds(145, 44, 142, 44);
		panel.add(txtrVerison);

		JLabel lblSelectYourMode = new JLabel(
				"Select your Mode. \r\nEdit tab for detail. \r\nCome back here and hit start!\r\n\r\n");
		lblSelectYourMode.setBounds(12, 84, 397, 16);
		panel.add(lblSelectYourMode);

		mOptions.setBounds(155, 106, 106, 22);
		panel.add(mOptions);

		startIt.setFont(new Font("Arial", Font.PLAIN, 12));
		startIt.setBounds(155, 159, 106, 25);
		panel.add(startIt);

		startIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishSetUp(e); // goes to taskmanager, calls addtask(), which
								// calls then getusertask()
				setVisible(false);
			}
		});

		/**
		 * 
		 * Stall Start
		 * 
		 * 
		 */

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Stalls", null, panel_1, null);
		panel_1.setLayout(null);

		sOptions.setBounds(134, 32, 118, 22);
		panel_1.add(sOptions);

		chckbxNewCheckBox.setBounds(134, 74, 118, 25);
		panel_1.add(chckbxNewCheckBox);

		/**
		 * 
		 * NPC START
		 * 
		 * 
		 * 
		 */
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Pick pocketing", null, panel_2, null);
		panel_2.setLayout(null);

		nOptions.setBounds(156, 35, 108, 22);
		panel_2.add(nOptions);

		JLabel lblFoodNametypeExactly = new JLabel("Food Name(Type Exactly)");
		lblFoodNametypeExactly.setBounds(135, 71, 154, 16);
		panel_2.add(lblFoodNametypeExactly);

		foodName = new JTextField();
		foodName.setBounds(156, 100, 108, 22);
		panel_2.add(foodName);
		foodName.setColumns(10);

		/**
		 * 
		 * 
		 * Chests Start
		 * 
		 * 
		 * 
		 */

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Chests", null, panel_3, null);
		panel_3.setLayout(null);

		cOptions.setBounds(149, 32, 117, 22);
		panel_3.add(cOptions);

		/**
		 * 
		 * Custom Npc Panel
		 * 
		 * 
		 */

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Custom Npc", null, panel_4, null);
		panel_4.setLayout(null);

		JLabel lblNpcName = new JLabel("NPC Name:");
		lblNpcName.setBounds(169, 13, 73, 16);
		panel_4.add(lblNpcName);

		custNpcName = new JTextField();
		custNpcName.setBounds(153, 42, 116, 22);
		panel_4.add(custNpcName);
		custNpcName.setColumns(10);

		radiusArea.setBounds(169, 99, 73, 22);
		panel_4.add(radiusArea);

		JLabel lblAreaRadius = new JLabel("Area Radius");
		lblAreaRadius.setBounds(169, 70, 83, 16);
		panel_4.add(lblAreaRadius);

		JLabel lblFoodName = new JLabel("Food Name");
		lblFoodName.setBounds(169, 134, 73, 16);
		panel_4.add(lblFoodName);

		cfoodName = new JTextField();
		cfoodName.setBounds(153, 162, 116, 22);
		panel_4.add(cfoodName);
		cfoodName.setColumns(10);

	}

	@Override
	public ArrayList<Task> getUserTasks() { // will run this when startbtn hit
		ArrayList<Task> tasks = new ArrayList<>();

		switch ((Mode) mOptions.getSelectedItem()) {
		case STALL:
			Variables.getVars().mode = Mode.STALL;
			Variables.getVars().drop = chckbxNewCheckBox.isSelected();
			Variables.getVars().stall = (Stalls) sOptions.getSelectedItem();
			// add tasks
			if (chckbxNewCheckBox.isSelected())
				tasks.add(new PowerDrop());
			else
				tasks.add(new Bank());
			tasks.add(new StealStall());
			tasks.add(new RunFromCombat());
			tasks.add(new Antiban());
			break;
		case NPC:
			Variables.getVars().mode = Mode.NPC;
			Variables.getVars().food = foodName.getText();
			Variables.getVars().npc = (Npc) nOptions.getSelectedItem();
			// add tasks
			tasks.add(new Bank());
			tasks.add(new EatFood());
			tasks.add(new PickPocketSetNpc());
			tasks.add(new RunFromCombat());
			tasks.add(new Antiban());
			break;
		case CHEST:
			Variables.getVars().mode = Mode.CHEST;
			Variables.getVars().chest = (Chest) cOptions.getSelectedItem();
			// add tasks
			tasks.add(new LootChest());
			tasks.add(new Antiban());
			break;
		case CUSTOM_NPC:
			Variables.getVars().mode = Mode.CUSTOM_NPC;
			Variables.getVars().food = cfoodName.getText();
			CustomNpc.getNpcDetails().npcName = custNpcName.getText();
			CustomNpc.getNpcDetails().radius = (int) radiusArea.getValue();
			CustomNpc.getNpcDetails().startingTile = Player.getPosition();
			CustomNpc.getNpcDetails().pickArea = new RSArea(CustomNpc.getNpcDetails().startingTile,
					CustomNpc.getNpcDetails().radius);
			// add tasks
			tasks.add(new Bank());
			tasks.add(new EatFood());
			tasks.add(new RunFromCombat());
			tasks.add(new Antiban());
			tasks.add(new PickPocketCustomNpc());
			break;
		default:
			break;
		}
		return tasks;
	}
}
