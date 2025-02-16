import java.net.*;
import java.io.*;

class client{
	public static void main(String[] args) throws IOException{
		Socket s = new Socket("localHost",4999);
	
		//Testing if sending info works
		
		InputStreamReader in = new InputStreamReader(s.getInputStream());
		BufferStreamReader bf = new BufferedReader(in);

		String str = bf.readLine();	

	}//end of main class
}//end of client class

