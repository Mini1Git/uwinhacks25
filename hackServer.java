import java.net.*;
import java.io.*;
import java.util.*;
import java

class server{
        public static void main(String[] args) throws IOException{                                                      
                //creates serveer and client socket
		ServerSocket ss = new ServerSocket(4999);
		Socket s =ss.accept();

		System.out.println("Client Connected"); //Test
							
			
	}//end of main class                                                                                           
}//end of client class 
