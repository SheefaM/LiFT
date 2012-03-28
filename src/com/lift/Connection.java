package com.lift;

import it.sauronsoftware.ftp4j.FTPClient;

import java.io.*;
import java.util.concurrent.*;

public class Connection implements Runnable {
	
	Thread thread;
	private ConnectionManager cManager;
	private FTPClient client;
	private int connId;
	private String host;
	private int port;
	private String user;
	private String pass;
	
	public Connection(ConnectionManager cManager, int connId, String host, int port, String user, String pass) {
		this.cManager = cManager;
		this.connId = connId;
		this.host = host;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}
	
	@Override
	public void run() {
		if(this.port == -1) {
			// Right now default to 21 is there is no port set.
			// Later, check if we're connecting with SSL/TLS and choose accordingly
			this.port = 21;
		}
		
		this.client = new FTPClient();
		
		//TODO: Connectors (For proxies, SOCKS, etc)
		//client.setConnector(connector);
		
		// TODO: SSL/TLS
		//client.setSecurity(FTPClient.SECURITY_FTPS); // enables FTPS
		//client.setSecurity(FTPClient.SECURITY_FTPES); // enables FTPES
	}
	
	public void closeConnection() {
		
	}

}
