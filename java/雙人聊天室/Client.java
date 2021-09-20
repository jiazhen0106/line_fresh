import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Thread {
	ClientGui gui;
	Client(ClientGui gui) {
		super();
		this.gui = gui;
	}

	public void run() {
		OutputStream out;
		try {
			out = gui.s.getOutputStream();
			new ClientAccept(gui).start();
			while (true) {
				System.out.print("");
				if (gui.send_flag == 1) {
					String message = gui.tfMessage.getText();
					gui.taBoard.append("Client0: " + message + "\n");
					out.write(message.getBytes());
					gui.send_flag = 0;
					gui.tfMessage.setText("");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
