import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAccept extends Thread{
	ServerGui gui;
	Socket s;
	ServerAccept(ServerGui gui,Socket s) {
		super();
		this.gui = gui;
		this.s = s;
	}
	public void run() {
		InputStream in;
		try {
			int n;
			byte[] buf = new byte[1024];
			in = s.getInputStream();
			while (true) {
				String returnedMessage;
				n = in.read(buf);
				if(n==0) {
					returnedMessage = "";
				}
				returnedMessage = new String(buf, 0, n);
				gui.taBoard.append("Client0: " + returnedMessage + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
