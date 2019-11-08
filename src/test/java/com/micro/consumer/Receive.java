package com.micro.consumer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class Receive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean target; // 接收到的文件保存的位置
		FileOutputStream fos; // 将接收到的文件写入电脑
		DataInputStream dis = null; // 读取穿送过来的数据文件
		DataOutputStream dos = null;
		ServerSocket server = null; // 服务器
		Socket socket = null; // 套接字
		boolean flag = true;

		// 处理客户端的请求
		try {
			// 接受前文件的准备
			File targetFile = new File("/D/data/红鞋子");
			if(!targetFile.exists()) {
				target = targetFile.mkdir();
				if (!target) {
					return;
				}
			}
			
			// fos = new FileOutputStream(target);

			server = new ServerSocket(8080); // 服务端口

			// 等待客户端的呼叫
			System.out.println("等待客户端的呼叫");
			socket = server.accept(); // 阻塞
			System.out.println("连接完成");
			
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			String answer = "answer";
			byte[] answerByte = answer.getBytes();
			// 接收数据
			byte[] b = new byte[10240];
			
			while(flag) {
				//1.接收文件名和文件相对路径并回复
				byte[] fileNameByte = new byte[500];
				int fileNameLen = dis.read(fileNameByte);
				String fileName = new String(fileNameByte,0,fileNameLen,"UTF-8");
				System.out.println(fileName);
				if(fileName.contains("fileName:")) {
					dos.write(answerByte);
					dos.flush();
//					fos =new FileOutputStream("/D/data/红鞋子"+select.replace("fileName:", ""));
//					
					int available = dis.readInt();
					System.out.println(available);
					dos.write(answerByte);
					dos.flush();
//					while(available>0) {
//						n=dis.read(b, 0, available>10240?10240:available);
//						fos.write(b, 0, n);
//						fos.flush();
//						//dos.write(answerByte);
//						available = available>10240?available-10240:0;
//						//System.out.println(available);
//					}
					
				}else if(fileName.contains("finish")) {
					flag = false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(socket!=null) socket.close();
				if(dis!=null) dis.close();
				if(dos!=null) dos.close();
				if(server != null) server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
