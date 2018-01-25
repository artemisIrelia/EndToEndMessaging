package websocket;

import com.reversehash.crypto.KeyFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;


import javax.crypto.interfaces.DHKey;

public class ServerThread implements Runnable{
	
	int port;
	KeyFactory factory;
	
	public ServerThread(int port, KeyFactory factory) {
		this.factory=factory;
		this.port = port;
	}
	public ServerThread(int port) {
		this.factory=new KeyFactory();
		this.port = port;

	}

	@Override
	public void run() {
		Server server = new Server(port);
		final KeyFactory _factory=factory;
        WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory factory) {
                factory.register(WebSocketServer.class);
                factory.setCreator(new WebSocketCreator() {
					
					@Override
					public Object createWebSocket(ServletUpgradeRequest arg0, ServletUpgradeResponse arg1) {
						// TODO Auto-generated method stub
						return new WebSocketServer(_factory);
					}
				});
            }
        };
        server.setHandler(wsHandler);
        try {
			server.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
	}
	
	public void random() {
		System.out.println("random");
	}
	public KeyFactory getKeyFactory(){
		return this.factory;
	}

}
