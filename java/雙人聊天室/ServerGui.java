import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ServerGui {

	private JFrame frame;
	private JTextField tfPort;
	JTextField tfMessage;
	JTextArea taBoard;
	ServerGui gui;
	Server server;
	int send_flag;
	int connect = 0;
	Socket s;
	public ServerGui() {
       gui = this;
	}

	void setVisible(boolean visiable) {
		frame.setVisible(visiable);
	}

	void initialize() {
		frame = new JFrame();
		frame.setTitle("Two Talkers: Server");
		frame.setBounds(100, 100, 506, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Server Port");
		lblNewLabel_1.setBounds(10, 21, 65, 15);
		frame.getContentPane().add(lblNewLabel_1);

		tfPort = new JTextField();
		tfPort.setText("3000");
		tfPort.setBounds(74, 18, 105, 21);
		frame.getContentPane().add(tfPort);
		tfPort.setColumns(10);

		JButton btnConnect = new JButton("�}�l��ť");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server =new Server(gui);
				server.start();
				gui.taBoard.append("�}�l��ť�� 3000...\n");
			}
		});

		btnConnect.setBounds(369, 17, 87, 23);
		frame.getContentPane().add(btnConnect);

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(369, 47, 87, 23);
		frame.getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				server.unlink();
				System.exit(0);
			}
		});

		taBoard = new JTextArea();
		taBoard.setBounds(22, 71, 446, 247);
		frame.getContentPane().add(taBoard);

		tfMessage = new JTextField();
		tfMessage.setBounds(22, 342, 446, 21);
		frame.getContentPane().add(tfMessage);
		tfMessage.setColumns(10);

		tfMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					send_flag = 1;
			}
		});
	}
}

