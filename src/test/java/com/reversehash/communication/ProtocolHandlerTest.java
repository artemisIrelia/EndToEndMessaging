package com.reversehash.communication;

import com.google.protobuf.InvalidProtocolBufferException;
import com.reversehash.communication.message.TextMessage;
import com.reversehash.crypto.KeyFactory;
import org.junit.Test;

import javax.xml.bind.ValidationException;

import static org.junit.Assert.*;

public class ProtocolHandlerTest {

    @Test
    public void protocolTest() throws InvalidProtocolBufferException, ValidationException {
        KeyFactory alice=new KeyFactory();
        KeyFactory bob= new KeyFactory();

        ProtocolHandler aliceHandler=new ProtocolHandler(alice);
        ProtocolHandler bobHandler=new ProtocolHandler(bob);

        TextMessage aliceMessage=new TextMessage("Hello bob how are you?");
        aliceMessage.sender=alice.getMyKey().getPublic();
        aliceMessage.receiver=bob.getMyKey().getPublic();

        byte[] messageToNetwork=aliceHandler.createMessage(aliceMessage).toByteArray();


        TextMessage Receivedmessage= (TextMessage) bobHandler.handleMessage(MessageProtocol.Message.parseFrom(messageToNetwork));

        assert(Receivedmessage.text.equals(aliceMessage.text));

    }

}