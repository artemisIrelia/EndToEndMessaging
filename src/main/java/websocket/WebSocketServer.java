package websocket;

import java.io.IOException;
import java.io.InputStream;

import com.reversehash.communication.MessageProtocol;
import com.reversehash.communication.ProtocolHandler;
import com.reversehash.crypto.KeyFactory;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import javax.xml.bind.ValidationException;


@WebSocket
public class WebSocketServer {
	KeyFactory factory;
	public WebSocketServer( KeyFactory factory) {
	    this.factory=factory;
	}
	
    @OnWebSocketMessage
    public void messageReceived (Session sess,InputStream is){
        try {
            new ProtocolHandler(factory).handleMessage(MessageProtocol.Message.parseFrom(is));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

    }
}
