import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
		ServerGui gui;
		ServerSocket svs;
		Server(ServerGui gui) {
			super();
			this.gui = gui;
		}

	public void run() {
		Socket s = null;
		OutputStream out;
		try {
			svs = new ServerSocket(3000);
			s = svs.accept();
			out = s.getOutputStream();
			
			gui.taBoard.append("Client0: 已經連線\n");
			
			String message = "連線成功!";
			out.write(message.getBytes());
			
			ServerAccept serverAccept =new ServerAccept(gui,s);
			serverAccept.start();
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		String message;
		try {
			
			while (true) {
				out = s.getOutputStream();
				System.out.print("");
				if (gui.send_flag == 1) {
					message = gui.tfMessage.getText();
					gui.taBoard.append("Server: " + message + "\n");
					out.write(message.getBytes());
					gui.send_flag = 0;
					gui.tfMessage.setText("");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	public void unlink() {
		try {
			svs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
