package com.lift;

import com.jgoodies.forms.layout.*;
import com.jgoodies.forms.factories.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JTabbedPane;

import org.jdesktop.swingx.JXTable;

public class ConnectionTabManager {
	
	int index = 0;
	JTabbedPane connectionTabPane;
	HashMap<Integer, JPanel> tabs = new HashMap<Integer, JPanel>();
	
	public ConnectionTabManager(JTabbedPane connectionTabPane) {
		this.connectionTabPane = connectionTabPane;
		
		connectionTabPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent event) {
				if (event.getSource() instanceof JTabbedPane) {
					JTabbedPane connectionTabPane = (JTabbedPane)event.getSource();
					//New tab was selected
				}
			}
		});
	}
	
	public int addTab(String title, String toolTipText) {
		index++;
		
		JPanel tab = new JPanel();
		tab.setName(title);
		
		tabs.put(index, tab);
		connectionTabPane.addTab(title, tab);
		connectionTabPane.setToolTipTextAt(connectionTabPane.getTabCount() - 1, toolTipText);
		initTab(tab);
		
		return index;
	}
	
	private void initTab(JPanel tab) {
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{445, 441, 0};
		gbl_panel.rowHeights = new int[]{39, 216, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		tab.setLayout(gbl_panel);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		tab.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		panel_2.setLayout(gbl_panel_2);
		
		Component rigidArea_6 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_6 = new GridBagConstraints();
		gbc_rigidArea_6.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea_6.gridx = 0;
		gbc_rigidArea_6.gridy = 0;
		panel_2.add(rigidArea_6, gbc_rigidArea_6);
		
		JLabel lblNewLabel = new JLabel("Local:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_2.add(lblNewLabel, gbc_lblNewLabel);
		
		JTextField textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 1;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panel_2.add(textField, gbc_textField);
		textField.setColumns(10);
		
		Component rigidArea_7 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_7 = new GridBagConstraints();
		gbc_rigidArea_7.gridx = 3;
		gbc_rigidArea_7.gridy = 0;
		panel_2.add(rigidArea_7, gbc_rigidArea_7);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		tab.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		panel_3.setLayout(gbl_panel_3);
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_8 = new GridBagConstraints();
		gbc_rigidArea_8.insets = new Insets(0, 0, 0, 5);
		gbc_rigidArea_8.gridx = 0;
		gbc_rigidArea_8.gridy = 0;
		panel_3.add(rigidArea_8, gbc_rigidArea_8);
		
		JLabel lblNewLabel_1 = new JLabel("Remote:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_3.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JTextField textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 0;
		panel_3.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		Component rigidArea_9 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_9 = new GridBagConstraints();
		gbc_rigidArea_9.gridx = 3;
		gbc_rigidArea_9.gridy = 0;
		panel_3.add(rigidArea_9, gbc_rigidArea_9);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 2;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 1;
		tab.add(splitPane, gbc_splitPane);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		JXTable table = new JXTable(new TestTableModel());
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		JXTable table_1 = new JXTable(new TestTableModel());
		scrollPane_1.setViewportView(table_1);
	}
	
	private static class TestTableModel extends AbstractTableModel {
		 
		public int getColumnCount() {
			return 6;
		}
 
		@Override
		public String getColumnName(int column) {
			return "WOAH";
		}
		
		
 
		public int getRowCount() {
			return 5;
		}
 
		public Object getValueAt(int rowIndex, int columnIndex) {
			return "" + rowIndex + "" + columnIndex;
		}
	}
}
