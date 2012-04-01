package com.lift;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;
import it.sauronsoftware.ftp4j.FTPListParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

public class Connection implements Runnable {
	
	public Thread thread;
	public Tab tab;
	private ConnectionManager cManager;
	private FTPClient client;
	private int connId;
	public String host;
	public int port;
	public String user;
	public String pass;
	public String localPath;
	public String remotePath;
	
	public Connection(ConnectionManager cManager, int connId, String host, int port, String user, String pass, String localPath, String remotePath) {
		this.cManager = cManager;
		this.connId = connId;
		this.host = host;
		this.port = port;
		this.user = user;
		this.pass = pass;
		this.localPath = localPath;
		this.remotePath = remotePath;
	}
	
	@Override
	public void run() {
		if(this.port == -1) {
			// Right now default to 21 if there is no port set.
			// Later, check if we're connecting with SSL/TLS and choose accordingly
			this.port = 21;
		}
		
		this.client = new FTPClient();
		
		//TODO: Connectors (For proxies, SOCKS, etc)
		//client.setConnector(connector);
		
		// TODO: SSL/TLS
		//client.setSecurity(FTPClient.SECURITY_FTPS); // enables FTPS
		//client.setSecurity(FTPClient.SECURITY_FTPES); // enables FTPES
		
		boolean error = false;
		try {
			client.connect(this.host, this.port);
			client.login(this.user, this.pass);
		} catch (IllegalStateException e) {
			error = true;
		} catch (IOException e) {
			error = true;
		} catch (FTPIllegalReplyException e) {
			error = true;
		} catch (FTPException e) {
			error = true;
		}
		
		if(error) {
			cManager.connectionError(this.connId);
			return;
		}
		
		//Once connected, update our tab's info
		if(remotePath != "" && remotePath != getCwd()) {
			changeDirectory(remotePath);
		} else {
			remotePath = getCwd();
		}
		
		tab.setLocalPath(localPath);
		tab.setRemotePath(remotePath);
	}
	
	public String getCwd() {
		String cwd = "";
		try {
			cwd = this.client.currentDirectory();
		} catch (IllegalStateException e) {
			/* */
		} catch (IOException e) {
			/* */
		} catch (FTPIllegalReplyException e) {
			/* */
		} catch (FTPException e) {
			/* */
		}
		
		return cwd + "/";
	}
	
	public boolean changeDirectory(String path) {
		boolean success = true;
		try {
			client.changeDirectory(path);
		} catch (IllegalStateException e) {
			success = true;
		} catch (IOException e) {
			success = true;
		} catch (FTPIllegalReplyException e) {
			success = true;
		} catch (FTPException e) {
			success = true;
		}
		return success;
	}
	
	public boolean changeDirectoryUp() {
		boolean success = true;
		try {
			client.changeDirectoryUp();
		} catch (IllegalStateException e) {
			success = true;
		} catch (IOException e) {
			success = true;
		} catch (FTPIllegalReplyException e) {
			success = true;
		} catch (FTPException e) {
			success = true;
		}
		return success;
	}
	
	public boolean moveItem(FTPFile source, String targetPath) {
		boolean success = true;
		try {
			client.rename(source.getName(), targetPath);
		} catch (IllegalStateException e) {
			success = true;
		} catch (IOException e) {
			success = true;
		} catch (FTPIllegalReplyException e) {
			success = true;
		} catch (FTPException e) {
			success = true;
		}
		return success;
	}
	
	// This handles files and directories.
	public boolean deleteItem(FTPFile item) {
		boolean success = true;
		
		try {
			if(item.getType() != FTPFile.TYPE_DIRECTORY) {
				//Just delete if it's a file
				client.deleteFile(item.getName());
			} else {
				//For directories we have to recurse and delete files and other directories first
				String startDir = getCwd();
				recursiveDelete(item);
				changeDirectory(startDir);
			}
		} catch (IllegalStateException e) {
			success = false;
		} catch (IOException e) {
			success = false;
		} catch (FTPIllegalReplyException e) {
			success = false;
		} catch (FTPException e) {
			success = false;
		}
		return success;
	}
	
	private void recursiveDelete(FTPFile file) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
		String cwd = getCwd();
		if(file.getType() == FTPFile.TYPE_DIRECTORY) {
			changeDirectory(getCwd() + file.getName());
			for(FTPFile f : getCwdFileList()) {
				recursiveDelete(f);
			}
			changeDirectory(cwd);
			client.deleteDirectory(file.getName());
		} else {
			client.deleteFile(file.getName());
		}
	}

	public ArrayList<FTPFile> getCwdFileList() {
		return getCwdFileList("");
	}
	
	public ArrayList<FTPFile> getCwdFileList(String filter) {
		
		boolean success = true;
		FTPFile[] temp = new FTPFile[0];
		try {
			temp = (FTPFile[])client.list(filter);
		} catch (IllegalStateException e) {
			/* */
		} catch (IOException e) {
			/* */
		} catch (FTPIllegalReplyException e) {
			/* */
		} catch (FTPException e) {
			/* */
		} catch (FTPDataTransferException e) {
			/* */
		} catch (FTPAbortedException e) {
			/* */
		} catch (FTPListParseException e) {
			/* */
		}
		
		return new ArrayList<FTPFile>(Arrays.asList(temp));
	}
	
	public void closeConnection() {
		try {
			client.abortCurrentDataTransfer(true);
			client.disconnect(true);
		} catch (IllegalStateException e) {
			/* */
		} catch (IOException e) {
			/* */
		} catch (FTPIllegalReplyException e) {
			/* */
		} catch (FTPException e) {
			/* */
		}
	}
	
	public void enqueueDownload(FileTransfer ft) {
		boolean error = false;
		try {
			client.download(ft.remoteFile, new File(ft.localFile), ft);
		} catch (IllegalStateException e) {
			error = true;
		} catch (FileNotFoundException e) {
			error = true;
		} catch (IOException e) {
			error = true;
		} catch (FTPIllegalReplyException e) {
			error = true;
		} catch (FTPException e) {
			error = true;
		} catch (FTPDataTransferException e) {
			error = true;
		} catch (FTPAbortedException e) {
			error = true;
		}
		
		if(error) {
			ft.failed();
		}
	}
	
	public void enqueueUpload(FileTransfer ft) {
		boolean error = false;
		try {
			client.upload(new File(ft.localFile), ft);
		} catch (IllegalStateException e) {
			error = true;
		} catch (FileNotFoundException e) {
			error = true;
		} catch (IOException e) {
			error = true;
		} catch (FTPIllegalReplyException e) {
			error = true;
		} catch (FTPException e) {
			error = true;
		} catch (FTPDataTransferException e) {
			error = true;
		} catch (FTPAbortedException e) {
			error = true;
		}
		
		if(error) {
			ft.failed();
		}
	}
}
