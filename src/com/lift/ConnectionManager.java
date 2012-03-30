package com.lift;
import java.io.*;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.*;

public class ConnectionManager {
	
	private ConnectionTabManager tabManager;
	private HashMap<Integer, Connection> connections = new HashMap<Integer, Connection>();
	
	public ConnectionManager(ConnectionTabManager tabManager) {
		this.tabManager = tabManager;
	}
	
	public void connect(String host, int port, String user, String pass) {
		connect(user + "@" + host, host, port, user, pass, "/", "");
	}
	
	public void connect(String title, String host, int port, String user, String pass, String localPath, String remotePath) {
		int connId = tabManager.addTab(title, "<html>Connection to " + host + "<br />as " + user + "<br />on port " + (port > 0 ? port : 21) + "</html>");
		
		Connection connection = new Connection(this, connId, host, port, user, pass, localPath, remotePath);
		connection.tab = tabManager.tabs.get(connId);
		connection.tab.connection = connection;
		connection.thread = new Thread(connection);
		connection.thread.start();
		connections.put(connId, connection);
	}
	
	//Connection thread reports that it couldn't connect
	public void connectionError(int connId) {
		Connection connection = connections.get(connId);
		tabManager.showDialog(true, "Connection Failed", "Unable to connect to " + connection.host + ":" + connection.port + " as " + connection.user);
		tabManager.removeTab(connId);
		connections.remove(connId);
	}
	
	public synchronized void disconnect(int connId) {
		Connection connection = connections.get(connId);
		connection.closeConnection();
		tabManager.removeTab(connId);
		connections.remove(connId);
	}
}
