package websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;

import com.reversehash.communication.ProtocolHandler;
import com.reversehash.communication.message.TextMessage;
import com.reversehash.crypto.KeyFactory;
import com.reversehash.util.Bytes;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class Client {
    private Session session;

    CountDownLatch latch = new CountDownLatch(1);
    KeyFactory factory=new KeyFactory();

    @OnWebSocketMessage
    public void onText(Session session, String message) throws IOException {
        System.out.println("Message received from server:" + message);
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connected to server");
        this.session = session;
        latch.countDown();
    }

    public void sendMessage(String str) {
        try {
            TextMessage m = new TextMessage(str);
            m.sender = "me".getBytes();
            m.receiver = "you".getBytes();

            byte[] data = new ProtocolHandler(factory).createMessage(m).toByteArray();
            session.getRemote().sendBytes(ByteBuffer.wrap(data));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CountDownLatch getLatch() {
        return latch;
    }


}
