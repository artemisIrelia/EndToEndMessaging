package Serializer;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Serializer.Message.CC;

public class TestSerializer {
	public static void main(String[] args) {
		Message myMessage = new Message(1, UUID.randomUUID(),new CC(UUID.randomUUID(), UUID.randomUUID(), "hahaha"),"hahah");
		try {
			String serialized = new ObjectMapper().writeValueAsString(myMessage);
			
			System.out.println(serialized);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
