package com.hand;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ServerListener extends Thread {

	@Override
	public void run() {
		try {
			
			ServerSocket ss = new ServerSocket(12345);
			
			while(true){
				Socket sc = ss.accept();
				JOptionPane.showMessageDialog(null, "�пͻ�������,�����ļ�");
				new ChartSocket(sc).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		
	}
	
	
}
