package Serializer;

import java.util.UUID;

public class Message {
	
	public static class CC{
		public UUID recieverUUID;
		public UUID sid;
		public String msg;
		public CC(UUID randomUUID, UUID randomUUID2, String string) {
			this.recieverUUID = randomUUID;
			this.sid = randomUUID2;
			this.msg = string;
		}
		
	
	}
	
	
	public int ver;
	public UUID senderUUID;
	public CC cc;
	public String MAC;
	
	
	public Message(int i, UUID randomUUID, CC cc2, String string) {
		this.ver = i;
		this.senderUUID = randomUUID;
		this.cc = cc2;
		this.MAC = string;
		
	}
}



