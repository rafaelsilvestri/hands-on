package com.ilegra.netty.training;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	 
    public static void main(String[] args) {
    	int port = (args.length == 0) ? 7001 : Integer.valueOf(args[0]);
 
        try (Socket socket = new Socket("localhost", port)) {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("1-1");
            dataOutputStream.flush();
           
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            String result = reader.readLine();
 
            dataOutputStream.close();
            System.out.println(result);
        } catch (Exception e) {
        	throw new RuntimeException("Error when running the client", e);
        }
    }
	
}
