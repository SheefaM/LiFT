package com.lift;

import it.sauronsoftware.ftp4j.FTPFile;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.jdesktop.swingx.JXTable;

public class Tab {
	
	private ConnectionTabManager tabManager;
	public Connection connection;
	public JPanel tabPanel = new JPanel();
	public JTextField localPathField = new JTextField();
	public JTextField remotePathField = new JTextField();
	public JXTable localTable = new JXTable();
	public JXTable remoteTable = new JXTable();
	
	public Tab(ConnectionTabManager tabManager, String title) {
		this.tabManager = tabManager;
		
		tabPanel.setName(title);
		
		//tabPanel
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{445, 441, 0};
		gbl_panel.rowHeights = new int[]{39, 216, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		tabPanel.setLayout(gbl_panel);
		
		//Left Panel
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		tabPanel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		panel_2.setLayout(gbl_panel_2);
		
		//Rigid area
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_6 = new GridBagConstraints();
		gbc_rigidArea_6.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea_6.gridx = 0;
		gbc_rigidArea_6.gridy = 0;
		panel_2.add(rigidArea_6, gbc_rigidArea_6);
		
		//label for localPathField
		JLabel lblNewLabel = new JLabel("Local:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		//localPathField
		GridBagConstraints gbc_localPathField = new GridBagConstraints();
		gbc_localPathField.gridwidth = 1;
		gbc_localPathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_localPathField.insets = new Insets(0, 0, 0, 5);
		gbc_localPathField.gridx = 2;
		gbc_localPathField.gridy = 0;
		panel_2.add(localPathField, gbc_localPathField);
		localPathField.setColumns(10);
		
		//Rigid area
		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_7 = new GridBagConstraints();
		gbc_rigidArea_7.gridx = 3;
		gbc_rigidArea_7.gridy = 0;
		panel_2.add(rigidArea_7, gbc_rigidArea_7);
		
		//Right panel
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		tabPanel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		panel_3.setLayout(gbl_panel_3);
		
		//Rigid area
		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_8 = new GridBagConstraints();
		gbc_rigidArea_8.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea_8.gridx = 0;
		gbc_rigidArea_8.gridy = 0;
		panel_3.add(rigidArea_8, gbc_rigidArea_8);
		
		//label for remotePathField
		JLabel lblNewLabel_1 = new JLabel("Remote:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		//remotePathField
		GridBagConstraints gbc_remotePathField = new GridBagConstraints();
		gbc_remotePathField.insets = new Insets(0, 0, 0, 5);
		gbc_remotePathField.fill = GridBagConstraints.HORIZONTAL;
		gbc_remotePathField.gridx = 2;
		gbc_remotePathField.gridy = 0;
		panel_3.add(remotePathField, gbc_remotePathField);
		remotePathField.setColumns(10);
		
		//Rigid area
		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_9 = new GridBagConstraints();
		gbc_rigidArea_9.gridx = 3;
		gbc_rigidArea_9.gridy = 0;
		panel_3.add(rigidArea_9, gbc_rigidArea_9);
		
		//splitPane
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 2;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 1;
		tabPanel.add(splitPane, gbc_splitPane);
		
		//left scrollPane
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		//localTable
		scrollPane.setViewportView(localTable);
		
		//right scrollPane
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		//remoteTable
		scrollPane_1.setViewportView(remoteTable);
		
		localTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event) {
				if(SwingUtilities.isRightMouseButton(event)) {
					//Right click
					int rowNum = localTable.rowAtPoint(event.getPoint());
					
					//Ex: Getting a file
					FTPFile file = ((LocalTableModel)localTable.getModel()).files.get(rowNum);
					
					//TODO: Right click menu
					
					final LocalFilePopupMenu menu = new LocalFilePopupMenu();
					menu.addMouseListener(new MouseAdapter() {
			        	public void mouseClicked(MouseEvent event) {
			        		JMenuItem item = (JMenuItem)event.getComponent();
			        		
			        		showDialog(false, "Menu Item", menu.getComponentIndex(item) + " - " + item.getText());
			        	}
			        });
					
				} else if(SwingUtilities.isLeftMouseButton(event) && event.getClickCount() == 2) {
					//Double-click
					int rowNum = localTable.rowAtPoint(event.getPoint());
					//TODO: Do a download here
					showDialog(false, "Double-Clicked", "row: " + rowNum);
				}
			}
		});
		
		remoteTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event) {
				if(SwingUtilities.isRightMouseButton(event)) {
					//Right click
					int rowNum = remoteTable.rowAtPoint(event.getPoint());
					//TODO: check if rowNum starts at 0 or 1
					
					//Ex: Getting a file
					FTPFile file = ((RemoteTableModel)remoteTable.getModel()).files.get(rowNum);
					
					//TODO: Right click menu
					
					final RemoteFilePopupMenu menu = new RemoteFilePopupMenu();
					menu.addMouseListener(new MouseAdapter() {
			        	public void mouseClicked(MouseEvent event) {
			        		JMenuItem item = (JMenuItem)event.getComponent();
			        		
			        		showDialog(false, "Menu Item", menu.getComponentIndex(item) + " - " + item.getText());
			        	}
			        });
					
					
				} else if(SwingUtilities.isLeftMouseButton(event) && event.getClickCount() == 2) {
					//Double-click
					int rowNum = remoteTable.rowAtPoint(event.getPoint());
					//TODO: Do a download here
					showDialog(false, "Double-Clicked", "row: " + rowNum);
				}
			}
		});
	}
	
	public void showDialog(boolean error, String title, String text) {
		tabManager.showDialog(error, title, text);
	}
	
	public void setLocalPath(String path) {
		localPathField.setText(path);
	}
	
	public void setRemotePath(String path) {
		remotePathField.setText(path);
	}
	
	public void updateLocalTable(ArrayList<FTPFile> files) {
		localTable.setModel(new LocalTableModel(files));
	}
	
	public void updateRemoteTable(ArrayList<FTPFile> files) {
		remoteTable.setModel(new RemoteTableModel(files));
	}
	
	class RemoteFilePopupMenu extends JPopupMenu {
	    public RemoteFilePopupMenu(){
	        JMenuItem item1 = new JMenuItem("Test");
	        
	        add(item1);
	    }
	}
	
	class LocalFilePopupMenu extends JPopupMenu {
	    public LocalFilePopupMenu(){
	        JMenuItem item1 = new JMenuItem("Test");
	        add(item1);
	    }
	}
	
	private class RemoteTableModel extends AbstractTableModel {
		 
		public ArrayList<FTPFile> files;
		
		public RemoteTableModel(ArrayList<FTPFile> files) {
			this.files = files;
		}
		
		public int getColumnCount() {
			return 1;
		}
 
		@Override
		public String getColumnName(int column) {
			return "Filename";
		}
		
		
 
		public int getRowCount() {
			return files.size();
		}
 
		public Object getValueAt(int rowIndex, int columnIndex) {
			return files.get(rowIndex).getName();
		}
	}
	
	private class LocalTableModel extends AbstractTableModel {
		 
		public ArrayList<FTPFile> files;
		
		public LocalTableModel(ArrayList<FTPFile> files) {
			this.files = files;
		}
		
		public int getColumnCount() {
			return 1;
		}
 
		@Override
		public String getColumnName(int column) {
			return "Filename";
		}
		
		
 
		public int getRowCount() {
			return 5;
		}
 
		public Object getValueAt(int rowIndex, int columnIndex) {
			return "" + rowIndex + "" + columnIndex;
		}
	}
}
