package com.departmentb_system.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientBSocket {
    public static void main(String[] args) {
        try {
            // 创建 Socket 并连接到指定地址和端口
            Socket socket = new Socket("172.17.194.248", 9999);
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());
            // 获取客户端输入流
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // 获取客户端输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
            // 创建一个线程用于接收服务端的消息
            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = reader.readLine();
                        if (message == null) {
                            break;
                        }
                        else if (message.contains("@")) {
                            System.out.println("正在处理：" + message);
                            //处理别的客户端的请求
                            //to do
                            System.out.println("处理完毕。");
                        } else if (message.contains("准备发送文件")) {
                            System.out.println(message);
                            String length = message.substring(13);
                            int l = Integer.parseInt(length);
                            OutputStream os = socket.getOutputStream();
                            FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\a.xml");
                            System.out.println("1");
                            for(int i = 0;i < l;i++){
                                String m = reader.readLine();
                                byte[] bytes = (m + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                            System.out.println("文件接收完成。");
                        } else {
                            System.out.println("Received message from server: " + message);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();
            // 接受输入的消息并发送给服务端
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String message = scanner.nextLine();
                if (message.equals("exit")) {
                    break;
                }
                writer.println(message);
                writer.flush();
            }
            // 关闭连接
            receiveThread.interrupt();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
