package com.lift;

import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Box;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.JXTable;

import com.lift.FileTransfer.TransferState;
import com.lift.FileTransfer.TransferType;



public class Tab {
	
	private ConnectionTabManager tabManager;
	public Connection connection;
	public JPanel tabPanel = new JPanel();
	public JTextField localPathField = new JTextField();
	public JTextField remotePathField = new JTextField();
	public JXTable localTable = new JXTable();
	public JXTable remoteTable = new JXTable();
	public JXTable transferTable = new JXTable();
	private int transferId = 0;
	
	public Tab(ConnectionTabManager tabManager, String title) {
		this.tabManager = tabManager;
		
		tabPanel.setName(title);
		
		//tabPanel
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{445, 441, 0};
				gbl_panel.rowHeights = new int[]{39, 216, 167};
				gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0};
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
				localTable.setRowSorter(null);
				localTable.setAutoCreateRowSorter(false);
				
				//right scrollPane
				JScrollPane scrollPane_1 = new JScrollPane();
				splitPane.setRightComponent(scrollPane_1);
				
				//remoteTable
				scrollPane_1.setViewportView(remoteTable);
				remoteTable.setRowSorter(null);
				remoteTable.setAutoCreateRowSorter(false);
				
				JPanel panel = new JPanel();
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.gridwidth = 2;
				gbc_panel.insets = new Insets(0, 0, 0, 5);
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = 2;
				tabPanel.add(panel, gbc_panel);
				gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{0, 0};
				gbl_panel.rowHeights = new int[]{0, 0};
				gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				
				JScrollPane scrollPane_2 = new JScrollPane();
				GridBagConstraints gbc_scrollPane_2 = new GridBagConstraints();
				gbc_scrollPane_2.fill = GridBagConstraints.BOTH;
				gbc_scrollPane_2.gridx = 0;
				gbc_scrollPane_2.gridy = 0;
				panel.add(scrollPane_2, gbc_scrollPane_2);
				
				transferTable.setModel(new TransferTableModel(transferTable));
				transferTable.setRowSorter(null);
				transferTable.setAutoCreateRowSorter(false);
				scrollPane_2.setViewportView(transferTable);
		
		InputMap iMap = localPathField.getInputMap(JComponent.WHEN_FOCUSED);
		ActionMap aMap = localPathField.getActionMap();
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		aMap.put("enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setLocalPath(localPathField.getText());
			}
		});
		
		iMap = remotePathField.getInputMap(JComponent.WHEN_FOCUSED);
		aMap = remotePathField.getActionMap();
		iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
		aMap.put("enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setRemotePath(remotePathField.getText());
			}
		});
		
		/*localTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event) {
				if(SwingUtilities.isRightMouseButton(event)) {
					//Right click
					int rowIndex = localTable.rowAtPoint(event.getPoint());
					
					File selectedFile = new File("");
					
					if(rowIndex == -1) { //Right clicking the table
						LocalTablePopupMenu menu = new LocalTablePopupMenu();
						menu.show(localTable.getComponentAt(event.getPoint()), event.getPoint().x, event.getPoint().y);
					} else {
						String selectedName = (String)localTable.getValueAt(rowIndex, 0);
						if(selectedName == "../") {//Right clicking the table
							LocalTablePopupMenu menu = new LocalTablePopupMenu();
							menu.show(localTable.getComponentAt(event.getPoint()), event.getPoint().x, event.getPoint().y);
						} else { //Right clicking an entry
							for(File file : ((LocalTableModel)localTable.getModel()).files) {
								if(file.isDirectory()) {
									if((file.getName() + "/").equals(selectedName)) {
										selectedFile = file;
										break;
									}
								} else {
									if(file.getName().equals(selectedName)) {
										selectedFile = file;
										break;
									}
								}
							}
							
							LocalFilePopupMenu menu = new LocalFilePopupMenu(selectedFile);
							menu.show(localTable.getComponentAt(event.getPoint()), event.getPoint().x, event.getPoint().y);
						}
					}
				} else if(SwingUtilities.isLeftMouseButton(event) && event.getClickCount() == 2) {
					//Double-click
					int rowIndex = localTable.rowAtPoint(event.getPoint());
					
					File selectedFile = new File("");
					
					if(rowIndex != -1) {
						String selectedName = (String)localTable.getValueAt(rowIndex, 0);
						if(selectedName == "../") {//Double-clicking the upDir
							connection.localPath = dirStringUp(connection.localPath);
							setLocalPath(connection.localPath);
						} else { //Double-clicking an entry
							for(File file : ((LocalTableModel)localTable.getModel()).files) {
								if(file.isDirectory()) {
									if((file.getName() + "/").equals(selectedName)) {
										selectedFile = file;
										break;
									}
								} else {
									if(file.getName().equals(selectedName)) {
										selectedFile = file;
										break;
									}
								}
							}
							if(selectedFile.isDirectory()) {
								connection.localPath = dirStringAppend(connection.localPath, selectedFile.getName());
								setLocalPath(connection.localPath);
							} else {
								uploadFile(selectedFile);
								transferTable.repaint();
							}
						}
					}
				}
			}
		});
		
		remoteTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event) {
				if(SwingUtilities.isRightMouseButton(event)) {
					//Right click
					int rowIndex = remoteTable.rowAtPoint(event.getPoint());
					
					FTPFile selectedFile = new FTPFile();
					
					if(rowIndex == -1) { //Right clicking the table
						RemoteTablePopupMenu menu = new RemoteTablePopupMenu();
						menu.show(remoteTable.getComponentAt(event.getPoint()), event.getPoint().x, event.getPoint().y);
					} else {
						String selectedName = (String)remoteTable.getValueAt(rowIndex, 0);
						if(selectedName == "../") {//Right clicking the table
							RemoteTablePopupMenu menu = new RemoteTablePopupMenu();
							menu.show(remoteTable.getComponentAt(event.getPoint()), event.getPoint().x, event.getPoint().y);
						} else { //Right clicking an entry
							for(FTPFile file : ((RemoteTableModel)remoteTable.getModel()).files) {
								if(file.getType() == FTPFile.TYPE_DIRECTORY) {
									if((file.getName() + "/").equals(selectedName)) {
										selectedFile = file;
										break;
									}
								} else {
									if(file.getName().equals(selectedName)) {
										selectedFile = file;
										break;
									}
								}
							}
							
							RemoteFilePopupMenu menu = new RemoteFilePopupMenu(selectedFile);
							menu.show(remoteTable.getComponentAt(event.getPoint()), event.getPoint().x, event.getPoint().y);
						}
					}
				} else if(SwingUtilities.isLeftMouseButton(event) && event.getClickCount() == 2) {
					//Double-click
					int rowIndex = remoteTable.rowAtPoint(event.getPoint());
					
					FTPFile selectedFile = new FTPFile();
					
					if(rowIndex != -1) {
						String selectedName = (String)remoteTable.getValueAt(rowIndex, 0);
						if(selectedName == "../") {//Double-clicking the upDir
							connection.changeDirectoryUp();
							setRemotePath(connection.getCwd());
						} else { //Double-clicking an entry
							for(FTPFile file : ((RemoteTableModel)remoteTable.getModel()).files) {
								if(file.getType() == FTPFile.TYPE_DIRECTORY) {
									if((file.getName() + "/").equals(selectedName)) {
										selectedFile = file;
										break;
									}
								} else {
									if(file.getName().equals(selectedName)) {
										selectedFile = file;
										break;
									}
								}
							}
							if(selectedFile.getType() == FTPFile.TYPE_DIRECTORY) {
								connection.changeDirectory(selectedFile.getName());
								setRemotePath(connection.getCwd());
							} else {
								downloadFile(selectedFile);
								transferTable.repaint();
							}
						}
					}
				}
			}
		});*/
		
		transferTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent event) {
				if(SwingUtilities.isRightMouseButton(event)) {
					//Right click
					int rowIndex = transferTable.rowAtPoint(event.getPoint());
					
					if(rowIndex == -1) {
						//Right clicking the table
						/*TransferTablePopupMenu menu = new TransferTablePopupMenu();
						menu.show(transferTable.getComponentAt(event.getPoint()), event.getPoint().x, event.getPoint().y);*/
					} else {
						//Right clicking an entry
						FileTransferPopupMenu menu = new FileTransferPopupMenu(rowIndex);
						menu.show(transferTable.getComponentAt(event.getPoint()), event.getPoint().x, event.getPoint().y);
					}
				}
			}
		});
	}
	
	public void downloadFile(FTPFile file) {
		transferId++;
		String localFile = connection.localPath + file.getName();
		String remoteFile = connection.getCwd() + file.getName();
		int size = (int)file.getSize();
		FileTransfer ft = new FileTransfer(connection,
				(TransferTableModel)transferTable.getModel(),
				transferId,
				TransferType.Download,
				localFile,
				remoteFile,
				size);
		connection.enqueueTransfer(ft);
	}
	
	public void uploadFile(File file) {
		transferId++;
		String localFile = file.getAbsolutePath();
		String remoteFile = connection.getCwd() + file.getName();
		int size = (int)file.length();
		FileTransfer ft = new FileTransfer(connection,
				(TransferTableModel)transferTable.getModel(),
				transferId,
				TransferType.Upload,
				localFile,
				remoteFile,
				size);
		connection.enqueueTransfer(ft);
	}
	
	public String dirStringUp(String cwd) {
		String newpath = "/";
		if(!cwd.equals("/") && !cwd.equals("")) {
			newpath = cwd.substring(0, cwd.substring(0, cwd.length() -2).lastIndexOf("/") + 1);
		}
		return newpath;
	}
	
	public String dirStringAppend(String cwd, String append)
	{
		String newpath = "/";
		if(cwd.equals("")) { cwd = "/"; }
		newpath = cwd + append + "/";
		return newpath;
	}
	public void showDialog(boolean error, String title, String text) {
		tabManager.showDialog(error, title, text);
	}
	
	public void setLocalPath(String path) {
		localPathField.setText(path);
		connection.localPath = path;
		updateLocalTable(path);
	}
	
	public void setRemotePath(String path) {
		if(path.equals("//")) { path = "/"; }
		if(connection.changeDirectory(path)) {
			remotePathField.setText(path);
			connection.remotePath = path;
			updateRemoteTable();
		}
	}
	
	public void updateLocalTable(String path) {
		File folder = new File(path);
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(folder.listFiles()));
		localTable.setModel(new LocalTableModel(files));
	}
	
	public void updateRemoteTable() {
		remoteTable.setModel(new RemoteTableModel(connection.getCwdFileList()));
		
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
			return files.size() + 1;
		}
 
		public Object getValueAt(int rowIndex, int columnIndex) {
			String name;
			if(rowIndex == 0) {
				name = "../";
			}
			else {
				FTPFile file = files.get(rowIndex - 1);
				if(file.getType() == FTPFile.TYPE_DIRECTORY) {
					name = file.getName() + "/";
				} else {
					name = file.getName();
				}
			}
			return name;
		}
	}
	
	class RemoteFilePopupMenu extends JPopupMenu {
		FTPFile targetFile;
		JMenuItem test = new JMenuItem("Test");
		JMenuItem test1 = new JMenuItem("Test1");
	    public RemoteFilePopupMenu(FTPFile tFile){
	    	this.targetFile = tFile;
	        
	    	test.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Menu Item Selected", "Test on " + targetFile.getName());
				}
	        });
	        
	        test1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Menu Item Selected", "Test1 on " + targetFile.getName());
				}
	        });
	        add(test);
	        add(test1);
	    }
	}
	
	class RemoteTablePopupMenu extends JPopupMenu {
		JMenuItem tabletest = new JMenuItem("TableTest");
		JMenuItem tabletest1 = new JMenuItem("TableTest1");
	    public RemoteTablePopupMenu(){
	        
	    	tabletest.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Menu Item Selected", "TableTest in " + connection.getCwd());
				}
	        });
	        
	        tabletest1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Menu Item Selected", "TableTest1 in " + connection.getCwd());
				}
	        });
	        add(tabletest);
	        add(tabletest1);
	    }
	}
	
	class LocalTablePopupMenu extends JPopupMenu {
		JMenuItem tabletest = new JMenuItem("TableTest");
		JMenuItem tabletest1 = new JMenuItem("TableTest1");
	    public LocalTablePopupMenu(){
	        
	    	tabletest.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Menu Item Selected", "TableTest in " + connection.localPath);
				}
	        });
	        
	        tabletest1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Menu Item Selected", "TableTest1 in " + connection.localPath);
				}
	        });
	        add(tabletest);
	        add(tabletest1);
	    }
	}
	
	class LocalFilePopupMenu extends JPopupMenu {
		File targetFile;
		JMenuItem test = new JMenuItem("Test");
		JMenuItem test1 = new JMenuItem("Test1");
	    public LocalFilePopupMenu(File tFile){
	    	this.targetFile = tFile;
	        
	    	test.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Menu Item Selected", "Test on " + targetFile.getName());
				}
	        });
	        
	        test1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Menu Item Selected", "Test1 on " + targetFile.getName());
				}
	        });
	        add(test);
	        add(test1);
	    }
	}
	
	private class LocalTableModel extends AbstractTableModel {
		 
		public ArrayList<File> files;
		
		public LocalTableModel(ArrayList<File> files) {
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
			return files.size() + 1;
		}
 
		public Object getValueAt(int rowIndex, int columnIndex) {
			String name;
			if(rowIndex == 0) {
				name = "../";
			}
			else {
				File file = files.get(rowIndex - 1);
				if(file.isDirectory()) {
					name = file.getName() + "/";
				} else {
					name = file.getName();
				}
			}
			return name;
		}
	}
	
	class FileTransferPopupMenu extends JPopupMenu {
		JMenuItem removeTransfer = new JMenuItem("Remove");
		JMenuItem abortTransfer = new JMenuItem("Abort");
	    public FileTransferPopupMenu(int row){
	        final int r = row;
	        final FileTransfer ft = ((TransferTableModel)transferTable.getModel()).getTransferAtPosition(r);
	    	removeTransfer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					((TransferTableModel)transferTable.getModel()).removeTransfer(r);
				}
	        });
	        
	        abortTransfer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						connection.client.abortCurrentDataTransfer(true);
					} catch (IOException e) {
						/* */
					} catch (FTPIllegalReplyException e) {
						/* */
					}
				}
	        });
	        
	        if(ft.state == TransferState.Downloading || ft.state == TransferState.Uploading) {
	        	add(abortTransfer);
	        }
	        
	        if(ft.state == TransferState.Aborted || ft.state == TransferState.Failed || ft.state == TransferState.Completed) {
	        	add(removeTransfer);
	        }
	    }
	}
	
	class TransferTablePopupMenu extends JPopupMenu {
		JMenuItem tabletest = new JMenuItem("TableTest");
		JMenuItem tabletest1 = new JMenuItem("TableTest1");
	    public TransferTablePopupMenu(){
	        
	    	tabletest.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Transfer Selected", "TableTest in Trasfer Table");
				}
	        });
	        
	        tabletest1.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					showDialog(false, "Transfer Selected", "TableTest in Trasfer Table");
				}
	        });
	        add(tabletest);
	        add(tabletest1);
	    }
	}
	
	public class ProgressBarRenderer implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			table.repaint();
			return (Component)value;
		}
	}
	
	public class StatusRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			String status = (String)value;
			
			cell.setFont(new Font("", Font.BOLD, 14));
			
			if(status.equals("Started")) {
				cell.setForeground(Color.YELLOW);
			} else if(status.equals("Downloading")) {
				cell.setForeground(Color.BLUE);
			} else if(status.equals("Completed")) {
				cell.setForeground(Color.GREEN);
			} else if(status.equals("Aborted")) {
				cell.setForeground(Color.ORANGE);
			} else {
				cell.setForeground(Color.RED);
			}
			
			table.repaint();
			return cell;
		}
	}
	
	public class TransferTableModel extends AbstractTableModel {
		 
		private HashMap<Integer, FileTransfer> transfers = new HashMap<Integer, FileTransfer>();
		private ArrayList<Integer> positions = new ArrayList<Integer>();
		private JXTable table;
		
		public TransferTableModel(JXTable table) {
			this.table = table;
		}
		
		public FileTransfer getTransferAtPosition(int pos) {
			return transfers.get(positions.get(pos));
		}
		
		public void removeTransfer(int pos) {
			transfers.remove(positions.get(pos));
			positions.remove(pos);
		}
		
		private void updateTable() {
			table.setModel(this);
			this.table.getColumn("Progress").setCellRenderer(new ProgressBarRenderer());
			this.table.getColumn("Status").setCellRenderer(new StatusRenderer());
			table.repaint();
		}
		
		public void addTransfer(FileTransfer ft) {
			transfers.put(ft.transferId, ft);
			positions.add(ft.transferId);
			updateTable();
		}
		
		public void updateTransferProgress() {
			updateTable();
		}
		
		public void transferStateChanged(int transferId) {
			FileTransfer transfer = transfers.get(transferId);
			switch(transfer.state) {
			case Completed:
				updateLocalTable(connection.localPath);
				updateRemoteTable();
				break;
			case Aborted:
				break;
			case Failed:
				break;
			default:
				break;
			}
		}
		
		public int getColumnCount() {
			return 3;
		}
 
		@Override
		public String getColumnName(int column) {
			switch(column) {
			case 0:
				return "Progress";
			case 1:
				return "Filename";
			case 2:
				return "Status";
			default:
				return "---";
			}
		}
 
		public int getRowCount() {
			return transfers.size();
		}
 
		public Object getValueAt(int rowIndex, int columnIndex) {
			FileTransfer transfer;
			
			switch(columnIndex) {
			case 0:
				transfer = transfers.get(positions.get(rowIndex));
				JProgressBar progressBar = new JProgressBar(0, transfer.maxProgress);
				progressBar.setValue(transfer.curProgress);
				progressBar.setStringPainted(true);
				if(transfer.state == TransferState.Aborted || transfer.state == TransferState.Failed || transfer.state == TransferState.Completed) {
					progressBar.setValue(transfer.maxProgress);
					progressBar.setEnabled(false);
				}
				return progressBar;
			case 1:
				transfer = transfers.get(positions.get(rowIndex));
				String path;
				if(transfer.type == TransferType.Download) {
					path = transfer.remoteFile;
				} else {
					path = transfer.localFile;
				}
				return path.substring(path.lastIndexOf("/") + 1);
			case 2:
				transfer = transfers.get(positions.get(rowIndex));
				return transfer.state.toString();
			default:
				return "---";
			}
		}
	}
}
