package com.lk.model.interview.socket.shareMsg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 信息共享，即服务器可以向多个客户端发送广播消息，客户端也可以向其他客户端发送消息。
 * 类似于聊天室的那种功能，实现信息能在多个客户端之间共享
 * @author Administrator
 *
 */
public class SocketClient extends Socket {

	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 2013;

	private Socket client;
	private PrintWriter out;
	private BufferedReader in;

	/**
	 * 与服务器连接，并输入发送消息
	 */
	public SocketClient() throws Exception {
		super(SERVER_IP, SERVER_PORT);
		client = this;
		out = new PrintWriter(this.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(this.getInputStream()));
		new readLineThread();

		while (true) {
			in = new BufferedReader(new InputStreamReader(System.in));
			String input = in.readLine();
			out.println(input);
		}
	}

	/**
	 * 用于监听服务器端向客户端发送消息线程类
	 */
	class readLineThread extends Thread {

		private BufferedReader buff;

		public readLineThread() {
			try {
				buff = new BufferedReader(new InputStreamReader(client.getInputStream()));
				start();
			} catch (Exception e) {
			}
		}

		@Override
		public void run() {
			try {
				while (true) {
					String result = buff.readLine();
					if ("byeClient".equals(result)) {// 客户端申请退出，服务端返回确认退出
						break;
					} else {// 输出服务端发送消息
						System.out.println(result);
					}
				}
				in.close();
				out.close();
				client.close();
			} catch (Exception e) {
			}
		}
	}

	public static void main(String[] args) {
		try {
			new SocketClient();// 启动客户端
		} catch (Exception e) {
		}
	}
}
