package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class SocketClient{
	private static Socket socket;
	private static DataOutputStream dos;
	private static DataInputStream dis;
	
	public SocketClient(String host, int port) {
		try {
			socket = new Socket(host, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void SendData() throws IOException, InterruptedException {
		FileIO.Add_FilesSQL_toSet();
		boolean block;
		Iterator<Path> iterator = FileIO.filesSQL.iterator();
		while(iterator.hasNext()) {
			block = true;
			byte[] bytes = FileIO.File_InputStream(iterator.next().toFile());
			dos.write(bytes);
			dos.flush();
			System.out.println("done!");
			
			while(block) {
				block = dis.readBoolean();
				if(block == false) break;
			}
		}
	}
}
