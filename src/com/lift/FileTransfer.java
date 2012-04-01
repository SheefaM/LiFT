package com.lift;

import com.lift.Tab.TransferTableModel;

import it.sauronsoftware.ftp4j.FTPDataTransferListener;

public class FileTransfer implements FTPDataTransferListener {
	
	public static enum TransferType {
		Download, Upload
	}
	
	public static enum TransferState {
		Starting, Downloading, Completed, Aborted, Failed
	}
	
	private Connection connection;
	private TransferTableModel transferTable;
	public int transferId;
	public TransferType type;
	public TransferState state;
	public String localFile;
	public String remoteFile;
	public int maxProgress;
	public int curProgress;
	
	public FileTransfer(Connection connection, TransferTableModel transferTable, int transferId, TransferType type, String localFile, String remoteFile, int maxProgress) {
		this.connection = connection;
		this.transferTable = transferTable;
		this.transferId = transferId;
		this.type = type;
		this.localFile = localFile;
		this.remoteFile = remoteFile;
		this.maxProgress = maxProgress;
		
		this.state = TransferState.Starting;
		
		transferTable.addTransfer(this);
	}
	
	public void start() {
		if(type == TransferType.Download) {
			connection.enqueueDownload(this);
		} else {
			connection.enqueueUpload(this);
		}
	}
	
	public void started() {
		state = TransferState.Downloading;
		transferTable.transferStateChanged(transferId);
	}

	public void transferred(int length) {
		curProgress += length;
		transferTable.updateTransferProgress();
	}

	public void completed() {
		state = TransferState.Completed;
		transferTable.transferStateChanged(transferId);
	}

	public void aborted() {
		state = TransferState.Aborted;
		transferTable.transferStateChanged(transferId);
	}

	public void failed() {
		state = TransferState.Failed;
		transferTable.transferStateChanged(transferId);
	}
}