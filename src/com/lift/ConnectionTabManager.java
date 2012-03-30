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
	public HashMap<Integer, Tab> tabs = new HashMap<Integer, Tab>();
	
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
	
	public void showDialog(boolean error, String title, String text) {
		JOptionPane.showMessageDialog(connectionTabPane, text, title,
				error ? JOptionPane.ERROR_MESSAGE : JOptionPane.WARNING_MESSAGE);
	}
	
	public int addTab(String title, String toolTipText) {
		index++;
		
		Tab tab = new Tab(this, title);
		
		tabs.put(index, tab);
		connectionTabPane.addTab(title, tab.tabPanel);
		connectionTabPane.setToolTipTextAt(connectionTabPane.getTabCount() - 1, toolTipText);
		return index;
	}
	
	public void removeTab(int connId) {
		connectionTabPane.remove(tabs.get(connId).tabPanel);
		tabs.remove(connId);
	}
}
