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
		connect(user + "@" + host, host, port, user, pass);
	}
	
	public void connect(String title, String host, int port, String user, String pass) {
		int connId = tabManager.addTab(title, "<html>Connection to " + host + "<br />as " + user + "<br />on port " + port + "</html>");
		
		Connection connection = new Connection(this, connId, host, port, user, pass);
		connection.thread = new Thread(connection);
		connection.thread.start();
		connections.put(connId, connection);
	}
	
	public synchronized void disconnect(int connId) {
		
	}
}
