package com.lift;

import com.jgoodies.forms.layout.*;
import com.jgoodies.forms.factories.*;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Comparator;

import org.jdesktop.swingx.JXTable;

public class LiFT {

	private JFrame frame;
	private JTextField qcHost;
	private JTextField qcPort;
	private JTextField qcUser;
	private JPasswordField qcPass;
	private ConnectionTabManager tManager;
	private ConnectionManager cManager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
			    try {
			            // Set System L&F
			        UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
			        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
			    } 
			    catch (UnsupportedLookAndFeelException e) {
			       // handle exception
			    }
			    catch (ClassNotFoundException e) {
			       // handle exception
			    }
			    catch (InstantiationException e) {
			       // handle exception
			    }
			    catch (IllegalAccessException e) {
			       // handle exception
			    }
				
				try {
					LiFT window = new LiFT();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LiFT() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 910, 726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mainMenuBar = new JMenuBar();
		frame.setJMenuBar(mainMenuBar);
		
		JMenu mnNewMenu = new JMenu("LiFT");
		mainMenuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("About LiFT");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmPreferences = new JMenuItem("Preferences..");
		mnNewMenu.add(mntmPreferences);
		
		JMenu mnNewMenu_1 = new JMenu("File");
		mainMenuBar.add(mnNewMenu_1);
		
		JMenuItem mntmOpenliftFile = new JMenuItem("Open .lift File..");
		mnNewMenu_1.add(mntmOpenliftFile);
		
		JMenu mnEdit = new JMenu("Edit");
		mainMenuBar.add(mnEdit);
		
		JMenu mnConnection = new JMenu("Connection");
		mainMenuBar.add(mnConnection);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Reconnect");
		mnConnection.add(mntmNewMenuItem_1);
		
		JMenuItem mntmReconnect = new JMenuItem("Disconnect");
		mnConnection.add(mntmReconnect);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{809, 0};
		gridBagLayout.rowHeights = new int[]{10, 427, 177, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JToolBar qcBar = new JToolBar();
		qcBar.setForeground(Color.LIGHT_GRAY);
		qcBar.setBackground(Color.LIGHT_GRAY);
		qcBar.setFloatable(false);
		GridBagConstraints gbc_qcBar = new GridBagConstraints();
		gbc_qcBar.insets = new Insets(0, 0, 5, 0);
		gbc_qcBar.fill = GridBagConstraints.BOTH;
		gbc_qcBar.gridx = 0;
		gbc_qcBar.gridy = 0;
		frame.getContentPane().add(qcBar, gbc_qcBar);
		  
		JLabel qcBarLabel = new JLabel("QuickConnect");
		qcBar.add(qcBarLabel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(30, 20));
		qcBar.add(rigidArea);
		
		JLabel qcHostLabel = new JLabel("Host:");
		qcBar.add(qcHostLabel);
		
		qcHost = new JTextField();
		qcBar.add(qcHost);
		
		qcHost.setMinimumSize(new Dimension(115, 20));
		qcHost.setPreferredSize(new Dimension(115, 20));
		qcHost.setMaximumSize(new Dimension(115, 20));
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		qcBar.add(rigidArea_1);
		
		JLabel qcPortLabel = new JLabel("Port:");
		qcBar.add(qcPortLabel);
		
		qcPort = new JTextField();
		qcBar.add(qcPort);
		
		qcPort.setMinimumSize(new Dimension(50, 20));
		qcPort.setPreferredSize(new Dimension(50, 20));
		qcPort.setMaximumSize(new Dimension(50, 20));
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		qcBar.add(rigidArea_2);
		
		JLabel qcUserLabel = new JLabel("User:");
		qcBar.add(qcUserLabel);
		
		qcUser = new JTextField();
		qcBar.add(qcUser);
		
		qcUser.setMinimumSize(new Dimension(100, 20));
		qcUser.setPreferredSize(new Dimension(100, 20));
		qcUser.setMaximumSize(new Dimension(100, 20));
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		qcBar.add(rigidArea_3);
		
		JLabel qcPassLabel = new JLabel("Pass:");
		qcBar.add(qcPassLabel);
		
		qcPass = new JPasswordField();
		qcBar.add(qcPass);
		qcPass.setMinimumSize(new Dimension(100, 20));
		qcPass.setPreferredSize(new Dimension(100, 20));
		qcPass.setMaximumSize(new Dimension(100, 20));
		
		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		qcBar.add(rigidArea_4);
		
		final JButton qcConnectButton = new JButton("Connect");
		qcBar.add(qcConnectButton);
		
		Component rigidArea_5 = Box.createRigidArea(new Dimension(11, 20));
		qcBar.add(rigidArea_5);
		
		JButton qcSaveButton = new JButton("Save");
		qcBar.add(qcSaveButton);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.LIGHT_GRAY);
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.fill = GridBagConstraints.BOTH;
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		frame.getContentPane().add(toolBar, gbc_toolBar);
		
		JTabbedPane connectionTabPane = new JTabbedPane(JTabbedPane.TOP);
		connectionTabPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_connectionTabPane = new GridBagConstraints();
		gbc_connectionTabPane.insets = new Insets(0, 0, 5, 0);
		gbc_connectionTabPane.fill = GridBagConstraints.BOTH;
		gbc_connectionTabPane.gridx = 0;
		gbc_connectionTabPane.gridy = 1;
		frame.getContentPane().add(connectionTabPane, gbc_connectionTabPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBackground(Color.LIGHT_GRAY);
		toolBar_1.setFloatable(false);
		GridBagConstraints gbc_toolBar_1 = new GridBagConstraints();
		gbc_toolBar_1.fill = GridBagConstraints.BOTH;
		gbc_toolBar_1.gridx = 0;
		gbc_toolBar_1.gridy = 3;
		gbc_toolBar_1.insets = new Insets(0, 0, 0, 0);
		frame.getContentPane().add(toolBar_1, gbc_toolBar_1);
		
		tManager = new ConnectionTabManager(connectionTabPane);
		cManager = new ConnectionManager(tManager);
		
		InputMap iMap = qcBar.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		ActionMap aMap = qcBar.getActionMap();
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		aMap.put("enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				qcConnectButton.doClick();
				connectButtonClick();
			}
		});
		
		qcConnectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				connectButtonClick();
			}
		});
	}
	
	public void connectButtonClick() {
		String host = qcHost.getText().toString();
		String port_string = qcPort.getText().toString();
		int port = -1;
		String user = qcUser.getText().toString();
		String pass = qcPass.getText();
		
		try {
			if(host.equals("")) {
				JOptionPane.showMessageDialog(frame,
						"The host provided is invalid",
						"Invalid Parameter",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if(!InetAddress.getByName(host).isReachable(1000)) {
				JOptionPane.showMessageDialog(frame,
						"The host provided is not reachable",
						"Invalid Parameter",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (HeadlessException e1) {
			JOptionPane.showMessageDialog(frame,
					"An unknown error occured connecting to the host",
					"Invalid Parameter",
					JOptionPane.ERROR_MESSAGE);
			return;
		} catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(frame,
					"The host provided is invalid",
					"Invalid Parameter",
					JOptionPane.ERROR_MESSAGE);
			return;
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(frame,
					"An unknown error occured connecting to the host",
					"Invalid Parameter",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(!port_string.equals("")) {
			try {
				port = Integer.parseInt(port_string);
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(frame,
						"The port provided is invalid",
						"Invalid Parameter",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		if(user.equals("")) {
			user = "anonymous";
		}
		
		clearQcFields();
		
		cManager.connect(host, port, user, pass);
	}
	
	public void clearQcFields() {
		qcHost.setText("");
		qcPort.setText("");
		qcUser.setText("");
		qcPass.setText("");
	}
}
