package Server;
public class Main {
	public static void main(String[] args) {
		SocketServer ss = new SocketServer();
		Thread thread = new Thread(ss);
		thread.start();
		System.out.println("Server is ready");
	}
}
