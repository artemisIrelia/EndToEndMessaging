package websocket;

import com.reversehash.crypto.DHKeyPair;
import com.reversehash.crypto.KeyFactory;
import com.sun.crypto.provider.DHKeyFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import gui.WritableGUI;

import javax.crypto.interfaces.DHKey;

public class ServerThread implements Runnable{
	
	int port;
	KeyFactory factory;
	//the gui interface
	WritableGUI gui;
	
	public ServerThread(int port, WritableGUI gui, KeyFactory factory) {
		this.factory=factory;
		this.port = port;
		this.gui = gui;
	}
	public ServerThread(int port, WritableGUI gui) {
		this.factory=new KeyFactory();
		this.port = port;
		this.gui = gui;

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
						return new WebSocketServer(gui,_factory);
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
