import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientAccept extends Thread {
	ClientGui gui;
	ClientAccept(ClientGui gui) {
		super();
		this.gui = gui;
	}
	public void run() {
		InputStream in;
		try {
			int connect_flag = 1;	
			in = gui.s.getInputStream();
			int n;
			byte[] buf = new byte[1024];
			while (true) {
				if(connect_flag == 1) {
					n = in.read(buf);
					String returnedMessage;
					if(n==0) {
						returnedMessage = "";
					}
					returnedMessage = new String(buf, 0, n);
					gui.taBoard.append(returnedMessage + "\n");
					connect_flag=0;	
				}
				else {
					n = in.read(buf);
					String returnedMessage;
					if(n==0) {
						returnedMessage = "";
					}
					returnedMessage = new String(buf, 0, n);
					gui.taBoard.append("Server: " + returnedMessage + "\n");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
