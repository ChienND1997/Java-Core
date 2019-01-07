package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
	private ServerSocket server;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public void RecData() {
		try {
			byte[] bytes = new byte[dis.available()];
			dis.read(bytes);
			FileIO.File_Writer(bytes);
			System.out.println("Da nhan duoc goi tin tu Client size :" + bytes.length/1024 + "kb");
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(6969);
			socket = server.accept();
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			while (true) {
				if (dis.available() > 0) {
					RecData();
				}else {	
						FileIO.stt++;
						dos.writeBoolean(false);
						dos.flush();
						while(!(dis.available() > 0)) {
							
						}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
