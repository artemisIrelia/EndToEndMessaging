package websocket;

import java.net.URI;
import java.util.concurrent.Executor;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

public class SetUpClient {
	
	public String webAddress;
	public WebSocketClient client;
	public Client socket;
	private Executor runner;
	
	public SetUpClient(String address, Executor runner)throws Exception
	{
		webAddress = address;
		client = new WebSocketClient();
		try {
			
			socket = new Client();
			client.start();
			URI echoUri = new URI(webAddress);
			ClientUpgradeRequest request = new ClientUpgradeRequest();
			client.connect(socket, echoUri, request);
			socket.getLatch().await();
		} catch (Throwable t) {
			throw new Exception("Unable to connect:" + toString().toString());
	}
		this.runner = runner;
	}
	
	public void sendMessage(String message) {
		runner.execute(new Runnable() {
	          public void run() {
	        	  socket.sendMessage(message);
	          }
	       });
		
		
	}
	
	public void closeSetupClient() throws Exception {
		client.stop();
	}
}

