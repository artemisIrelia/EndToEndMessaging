package com.reversehash.communication.message;

import com.google.protobuf.ByteString;
import com.reversehash.util.Bytes;
import com.sun.xml.internal.ws.api.message.Packet;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@MessageType(type=2,version=0)
public class KeyExchangeMessage extends Message{
    public void  createInstance(){


    }
    public void decodeInstance(){

    }

    @Override
    public void handle(){

    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public void readBytes(int version,byte[] input) {

    }


}
