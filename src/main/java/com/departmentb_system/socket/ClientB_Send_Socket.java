package com.departmentb_system.socket;

import com.departmentb_system.controller.CourseManageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.*;
import java.net.Socket;

public class ClientB_Send_Socket {
    Socket socket;

    PrintWriter writer;

    @Autowired
    private ApplicationContext applicationContext;

    public String sharetable = "src/main/resources/Transfer/BShare.xml";

    public ClientB_Send_Socket(){
        try{
            socket = new Socket("172.17.194.248", 9999);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void sendMessage(String message) {
        try {
            writer = new PrintWriter(socket.getOutputStream(),true);
            writer.println(message);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void sendShareCourse(){
        //CourseManageController courseManageController = applicationContext.getBean(CourseManageController.class);
        //courseManageController.transferMyShareCourse();

        try{
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write('B');
            outputStream.write('\n');

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
            writer.println("B will share *");
            writer.flush();
            writer.println("共享表准备发送，行数为：" + ClientB_Socket.countLinesInXML(sharetable));
            writer.flush();

            ClientB_Socket.sendmessage(socket,sharetable);
            //outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void sendCourseTable(){

    }
}
