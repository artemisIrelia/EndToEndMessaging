package websocket;

import com.reversehash.crypto.KeyFactory;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

//
//main and main2 are two clients(also has a server that listens to messages)
//besides for testing in same computer main runs on 8080 and main2 on 8085
//they are cmd line versions


public class Main {
    public static void main(String[] args) throws Exception {

        //no gui so set null for gui params
        KeyFactory serverKeys = new KeyFactory();

        ServerThread server = new ServerThread(8000, serverKeys);

        Thread t1 = new Thread(server);
        t1.start();

        Executor runner = Executors.newSingleThreadExecutor();

        Scanner uScanner = new Scanner(System.in);

//		String dest = "ws://localhost:8000/";

        System.out.println("select ip to connect and port:");
        String dest = uScanner.nextLine();

        try {
            SetUpClient setUpClient = new SetUpClient(dest, runner);

            System.out.println("username:");
            String username = uScanner.nextLine();
            while (true) {
                setUpClient.sendMessage(uScanner.nextLine());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            uScanner.close();
        }


    }
}

