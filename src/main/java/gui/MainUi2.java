package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import websocket.ServerThread;
import websocket.SetUpClient;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


//this connects to mainui so addres is
//ws://localhost:8080/

public class MainUi2 extends JFrame implements WritableGUI{

	private JPanel contentPane;
	private JTextField txtWebsocketAddress;
	private JTextField txtUsername;
	private JTextField txtMessage;
	private JButton btnConnect;
	private JTextArea chatArea;
	private SetUpClient setUpClient;
	private String username;
	private String connectedWebSocket;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUi2 frame = new MainUi2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainUi2() {
		
		//start the server thread
		ServerThread server = new ServerThread(8000, this);
		Thread t1 = new Thread(server);
		t1.start();
		
		Executor runner = Executors.newSingleThreadExecutor();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtWebsocketAddress = new JTextField();
		txtWebsocketAddress.setText("websocket address");
		txtWebsocketAddress.setToolTipText("");
		txtWebsocketAddress.setBounds(12, 25, 253, 19);
		contentPane.add(txtWebsocketAddress);
		txtWebsocketAddress.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setText("username");
		txtUsername.setBounds(292, 25, 238, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		btnConnect = new JButton("connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectedWebSocket = txtWebsocketAddress.getText().toString();
				username = txtUsername.getText().toString();
				try {
					setUpClient = new SetUpClient(connectedWebSocket, runner);
				}
					catch (Exception exception) {
						// TODO: handle exception
						exception.printStackTrace();
			}
			}
		});
		btnConnect.setBounds(605, 22, 117, 25);
		contentPane.add(btnConnect);
		
		chatArea = new JTextArea();
		chatArea.setBounds(16, 96, 706, 264);
		contentPane.add(chatArea);
		
		txtMessage = new JTextField();
		txtMessage.setText("message");
		txtMessage.setBounds(24, 400, 525, 19);
		contentPane.add(txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = txtMessage.getText().toString();
				if(setUpClient != null) {
				setUpClient.sendMessage(username + ":" + message);
				write("to " + connectedWebSocket + " : " + message);
				}
			}
		});
		btnSend.setBounds(619, 397, 117, 25);
		contentPane.add(btnSend);
	}

	@Override
	public void write(String s) {
		chatArea.append(s + System.lineSeparator());
	}
}
