package com.lk.model.interview.socket.advance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 多客户端连接服务器
 * 客户端会话可以一直保持，直到客户端请求断开连接，服务端才会断开连接
 * @author Administrator
 *
 */
public class SocketClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 2013);
			socket.setSoTimeout(60000);

			PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),
					true);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));

			String result = "";
			while (result.indexOf("bye") == -1) {
				BufferedReader sysBuff = new BufferedReader(
						new InputStreamReader(System.in));
				printWriter.println(sysBuff.readLine());
				//printWriter.flush();

				result = bufferedReader.readLine();
				System.out.println("Server say : " + result);
			}

			printWriter.close();
			bufferedReader.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
}
