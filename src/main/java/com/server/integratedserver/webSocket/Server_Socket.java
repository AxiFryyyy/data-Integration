package com.server.integratedserver.webSocket;

import com.server.integratedserver.controller.HomeController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.xml.sax.SAXException;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

@Component
public class Server_Socket implements Runnable{
    public static List<Socket> clients = new ArrayList<>();
    private static String share;

    public static String ip_A;
    public static String ip_B;
    public static String ip_C;
    //创建一个线程池,如果有客户端连接就创建一个线程, 与之通信
    public static int share_count = 0;

    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void handle(Socket socket) {
        System.out.println("线程ID:" + Thread.currentThread().getId()
                + "  线程名称:" + Thread.currentThread().getName());
        //textArea.append("线程ID:" + Thread.currentThread().getId()
                //+ "  线程名称:" + Thread.currentThread().getName() + "\n");
        clients.add(socket);
        try {
            while (true) {
                // 将客户端加入列表

                // 获取客户端输入流
                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                // 获取客户端输出流
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
                // 读取客户端消息
                while(true){
                    String message = reader.readLine();
                    System.out.println("Received message from client: " + message);
                    //textArea.append("Received message from client: " + message + "\n");
                    //客户端表明通信身份，客户端连接后必须先向服务端发送自己的身份，否则一些操作将无法完成
                    if(message.equals("A")){
                        /*int c = 0;
                        for (Socket client : clients) {
                            c++;
                            textArea.append("当前第" + c + "个线程");}*/
                        ip_A = socket.getInetAddress().getHostAddress();
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.println("A客户端您好，已经记录您的ip地址。");
                        clientWriter.flush();
                    }
                    else if(message.equals("B")){
                        ip_B = socket.getInetAddress().getHostAddress();
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.println("B客户端您好，已经记录您的ip地址。");
                        clientWriter.flush();
                    }
                    else if(message.equals("C")){
                        ip_C = socket.getInetAddress().getHostAddress();
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.println("C客户端您好，已经记录您的ip地址。");
                        clientWriter.flush();
                    }
                    //按下collect按钮后，客户端发送will send *，识别这些消息并接收三个文件
                    else if(message.equals("A will send *")){
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.flush();
                        String path;
                        String prepath = "src/main/resources/output/predocA.xml";
                        for(int i = 0;i < 3;i++){
                            String m = reader.readLine();
                            int line = Integer.parseInt(m.substring(12));
                            System.out.println("行数为" + line);
                            path = "src/main/resources/output/docA" + i + ".xml";
                            FileOutputStream fos = new FileOutputStream(prepath);
                            for(int j = 0;j < line;j++){
                                String mes = reader.readLine();
                                byte[] bytes = (mes + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                            if(i == 0){
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatStudent.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalStudentTable.xml",
                                        path,"src/main/resources/total/totalStudentTable.xml");
                            }
                            else if(i == 1){
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalClassTable.xml",
                                        path,"src/main/resources/total/totalClassTable.xml");
                            }
                            else{
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatClassChoice.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalClassChoiceTable.xml",
                                        path,"src/main/resources/total/totalClassChoiceTable.xml");
                            }


                        }

                    }
                    else if(message.equals("B will send *")){
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.flush();
                        String path;
                        String prepath = "src/main/resources/output/predocB.xml";
                        for(int i = 0;i < 3;i++){

                            String m = reader.readLine();
                            int line = Integer.parseInt(m.substring(12));
                            System.out.println("行数为" + line);
                            path = "src/main/resources/output/docB" + i + ".xml";
                            FileOutputStream fos = new FileOutputStream(prepath);
                            for(int j = 0;j < line;j++){
                                String mes = reader.readLine();
                                byte[] bytes = (mes + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                            if(i == 0){
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatStudent.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalStudentTable.xml",
                                        path,"src/main/resources/total/totalStudentTable.xml");
                            }
                            else if(i == 1){
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalClassTable.xml",
                                        path,"src/main/resources/total/totalClassTable.xml");
                            }
                            else{
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatClassChoice.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalClassChoiceTable.xml",
                                        path,"src/main/resources/total/totalClassChoiceTable.xml");
                            }
                            /*utils.transformXML(prepath,path,"src/main/resources/Transfer/formatClassChoice.xsl");*/
                        }
                    }
                    else if(message.equals("C will send *")){
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.flush();
                        String path;
                        String prepath = "src/main/resources/output/predocC.xml";
                        for(int i = 0;i < 3;i++){
                            String m = reader.readLine();
                            int line = Integer.parseInt(m.substring(12));
                            System.out.println("行数为" + line);
                            path = "src/main/resources/output/docC" + i + ".xml";
                            FileOutputStream fos = new FileOutputStream(prepath);
                            for(int j = 0;j < line;j++){
                                String mes = reader.readLine();
                                byte[] bytes = (mes + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                            if(i == 0){
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatStudent.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalStudentTable.xml",
                                        path,"src/main/resources/total/totalStudentTable.xml");
                            }
                            else if(i == 1){
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalClassTable.xml",
                                        path,"src/main/resources/total/totalClassTable.xml");
                            }
                            else{
                                utils.transformXML(prepath,path,"src/main/resources/Transfer/formatClassChoice.xsl");
                                utils.mergeXmlFiles("src/main/resources/total/totalClassChoiceTable.xml",
                                        path,"src/main/resources/total/totalClassChoiceTable.xml");
                            }
                            /*utils.transformXML(prepath,path,"src/main/resources/Transfer/formatClass.xsl");*/
                        }
                    }
                    //处理A客户端发送share请求
                    else if(message.equals("share A")){
                        //int c = 0;
                        share = ip_A;
                        String lastip = null;
                        for (Socket client : clients) {
                            //c++;
                            //textArea.append("当前第" + c + "个线程");
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (!ip_A.equals(clientIP) && !clientIP.equals(lastip)) {
                                lastip = clientIP;
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));

                                try {
                                    Thread.sleep(3000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                //转发A的share请求到其他客户端
                                clientWriter.println(message);
                                clientWriter.flush();

                                System.out.println("testing");

                                String m = reader.readLine();
                                //textArea.append("Received m from client: " + m + "\n");
                                //处理其他客户端发来的响应，并接收文件
                                /*if(m.equals("B will share *")){
                                    System.out.println("222");
                                    textArea.append("222" + "\n");
                                    String mm = reader.readLine();
                                    textArea.append("Received message from client: " + mm + "\n");
                                    int line = Integer.parseInt(mm.substring(12));
                                    System.out.println("行数为" + line);
                                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_B.xml");
                                    for(int j = 0;j < line;j++){
                                        String mes = reader.readLine();
                                        byte[] bytes = (mes + "\n").getBytes();
                                        fos.write(bytes);
                                    }
                                    fos.close();
                                }
                                else if(m.equals("C will share *")){
                                    System.out.println("333");
                                    String mm = reader.readLine();
                                    textArea.append("Received message from client: " + mm + "\n");
                                    int line = Integer.parseInt(mm.substring(12));
                                    System.out.println("行数为" + line);
                                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_C.xml");
                                    for(int j = 0;j < line;j++){
                                        String mes = reader.readLine();
                                        byte[] bytes = (mes + "\n").getBytes();
                                        fos.write(bytes);
                                    }
                                    fos.close();
                                }*/
                                //发送共享成功反馈
                                clientWriter.println("share success");
                                clientWriter.flush();
                            }
                        }
                        //合并文件并向A院系发送合并后的文件
                        /*String outputfile = "src/main/resources/share_BC.xml";
                        utils.mergeXmlFiles("src/main/resources/output/share_B.xml",
                                "src/main/resources/output/share_C.xml",
                                outputfile);
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        FileInputStream fis = new FileInputStream(outputfile);
                        //发送行数便于客户端读取
                        System.out.println("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile));
                        byte[] bytes = ("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
                        clientOutput.write(bytes);
                        //发送合并后的文件
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) != -1) {
                            clientOutput.write(buffer, 0, len);
                        }
                        fis.close();
                        clientWriter.flush();
                        System.out.println("文件发送完成。");
                        textArea.append("文件发送完成。" + "\n");*/

                        //合并文件并向share院系发送合并后的文件
                        String outputfile = "src/main/resources/output/share_BC.xml";
                        String outputfile1 = "src/main/resources/output/share1.xml";
                        String outputfile2 = "src/main/resources/output/share2.xml";
                        String outputfile3 = "src/main/resources/output/share3.xml";
                        utils.transformXML("src/main/resources/output/share_B.xml",
                                outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                        utils.transformXML("src/main/resources/output/share_C.xml",
                                outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                        utils.mergeXmlFiles(outputfile1,
                                outputfile2,
                                outputfile3);
                        //utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                        String lastip1 = null;
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (ip_A.equals(clientIP) && !clientIP.equals(lastip1)) {
                                lastip1 = clientIP;
                                if(share.equals(ip_A)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                                }
                                else if(share.equals(ip_B)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToB.xsl");
                                }
                                else if(share.equals(ip_C)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToC.xsl");
                                }
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                FileInputStream fis = new FileInputStream(outputfile);
                                //发送行数便于客户端读取
                                System.out.println("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile));
                                byte[] bytes = ("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
                                clientOutput.write(bytes);
                                //发送合并后的文件
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = fis.read(buffer)) != -1) {
                                    clientOutput.write(buffer, 0, len);
                                }
                                fis.close();
                                clientWriter.flush();
                                System.out.println("文件发送完成。");
                                //textArea.append("文件发送完成。" + "\n");
                            }
                        }
                    }
                    else if(message.equals("share B")){
                        share = ip_B;
                        String lastip = null;
                        for (Socket client : clients) {
                            //c++;
                            //textArea.append("当前第" + c + "个线程");
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (!ip_B.equals(clientIP) && !clientIP.equals(lastip)) {
                                lastip = clientIP;
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                //转发A的share请求到其他客户端
                                clientWriter.println(message);
                                clientWriter.flush();
                                /*String m = reader.readLine();
                                textArea.append("Received m from client: " + m + "\n");*/
                            }
                        }

                        String outputfile = "src/main/resources/output/share_BC.xml";
                        String outputfile1 = "src/main/resources/output/share1.xml";
                        String outputfile2 = "src/main/resources/output/share2.xml";
                        String outputfile3 = "src/main/resources/output/share3.xml";
                        utils.transformXML("src/main/resources/output/share_B.xml",
                                outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                        utils.transformXML("src/main/resources/output/share_C.xml",
                                outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                        utils.mergeXmlFiles(outputfile1,
                                outputfile2,
                                outputfile3);
                        //utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                        String lastip1 = null;
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (ip_B.equals(clientIP) && !clientIP.equals(lastip1)) {
                                lastip1 = clientIP;
                                if(share.equals(ip_A)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                                }
                                else if(share.equals(ip_B)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToB.xsl");
                                }
                                else if(share.equals(ip_C)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToC.xsl");
                                }
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                FileInputStream fis = new FileInputStream(outputfile);
                                //发送行数便于客户端读取
                                System.out.println("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile));
                                byte[] bytes = ("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
                                clientOutput.write(bytes);
                                //发送合并后的文件
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = fis.read(buffer)) != -1) {
                                    clientOutput.write(buffer, 0, len);
                                }
                                fis.close();
                                clientWriter.flush();
                                System.out.println("文件发送完成。");
                                //textArea.append("文件发送完成。" + "\n");
                            }
                        }
                    }
                    else if(message.equals("share C")){
                        share = ip_C;
                        String lastip = null;
                        for (Socket client : clients) {
                            //c++;
                            //textArea.append("当前第" + c + "个线程");
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (!ip_C.equals(clientIP) && !clientIP.equals(lastip)) {
                                lastip = clientIP;
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                //转发A的share请求到其他客户端
                                clientWriter.println(message);
                                clientWriter.flush();
                                /*String m = reader.readLine();
                                textArea.append("Received m from client: " + m + "\n");
                                //处理其他客户端发来的响应，并接收文件
                                if(m.equals("A will share *")){
                                    System.out.println("111");
                                    textArea.append("111" + "\n");
                                    String mm = reader.readLine();
                                    textArea.append("Received message from client: " + mm + "\n");
                                    int line = Integer.parseInt(mm.substring(12));
                                    System.out.println("行数为" + line);
                                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_A.xml");
                                    for(int j = 0;j < line;j++){
                                        String mes = reader.readLine();
                                        byte[] bytes = (mes + "\n").getBytes();
                                        fos.write(bytes);
                                    }
                                    fos.close();
                                    textArea.append("接收成功！" + "\n");
                                }
                                else if(m.equals("B will share *")){
                                    System.out.println("222");
                                    textArea.append("222" + "\n");
                                    String mm = reader.readLine();
                                    textArea.append("Received message from client: " + mm + "\n");
                                    int line = Integer.parseInt(mm.substring(12));
                                    System.out.println("行数为" + line);
                                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_B.xml");
                                    for(int j = 0;j < line;j++){
                                        String mes = reader.readLine();
                                        byte[] bytes = (mes + "\n").getBytes();
                                        fos.write(bytes);
                                    }
                                    fos.close();
                                }
                                //发送共享成功反馈
                                clientWriter.println("share success");
                                clientWriter.flush();
                            }
                        }
                        //合并文件并向A院系发送合并后的文件
                        String outputfile = "src/main/resources/output/share_BC.xml";
                        String outputfile1 = "src/main/resources/output/share1.xml";
                        String outputfile2 = "src/main/resources/output/share2.xml";
                        String outputfile3 = "src/main/resources/output/share3.xml";
                        utils.transformXML("src/main/resources/output/share_B.xml",
                                outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                        utils.transformXML("src/main/resources/output/share_C.xml",
                                outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                        utils.mergeXmlFiles(outputfile1,
                                outputfile2,
                                outputfile3);
                        //utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                        String lastip1 = null;
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (ip_C.equals(clientIP) && !clientIP.equals(lastip1)) {
                                lastip1 = clientIP;
                                if(share.equals(ip_A)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                                }
                                else if(share.equals(ip_B)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToB.xsl");
                                }
                                else if(share.equals(ip_C)){
                                    utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToC.xsl");
                                }
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                FileInputStream fis = new FileInputStream(outputfile);
                                //发送行数便于客户端读取
                                System.out.println("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile));
                                byte[] bytes = ("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
                                clientOutput.write(bytes);
                                //发送合并后的文件
                                byte[] buffer = new byte[1024];
                                int len;
                                while ((len = fis.read(buffer)) != -1) {
                                    clientOutput.write(buffer, 0, len);
                                }
                                fis.close();
                                clientWriter.flush();
                                System.out.println("文件发送完成。");
                                textArea.append("文件发送完成。" + "\n");*/
                            }
                        }
                    }
                    else if(message.equals("A will share *")){
                        String mm = reader.readLine();
                        //textArea.append("Received message from client: " + mm + "\n");
                        int line = Integer.parseInt(mm.substring(12));
                        System.out.println("行数为" + line);
                        FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_A.xml");
                        for(int j = 0;j < line;j++){
                            String mes = reader.readLine();
                            byte[] bytes = (mes + "\n").getBytes();
                            fos.write(bytes);
                        }
                        fos.close();
                        //textArea.append("接收成功！" + "\n");
                        share_count++;
                        if(share_count%2 == 0){
                            //合并文件并向share院系发送合并后的文件
                            /*String outputfile = "src/main/resources/output/share_BC.xml";
                            String outputfile1 = "src/main/resources/output/share1.xml";
                            String outputfile2 = "src/main/resources/output/share2.xml";
                            String outputfile3 = "src/main/resources/output/share3.xml";
                            utils.transformXML("src/main/resources/output/share_B.xml",
                                    outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                            utils.transformXML("src/main/resources/output/share_C.xml",
                                    outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                            utils.mergeXmlFiles(outputfile1,
                                    outputfile2,
                                    outputfile3);*/
                            String outputfile;
                            String outputfile3;
                            if(share.equals(ip_A)){
                                outputfile = "src/main/resources/output/share_BC.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_B.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_C.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            else if(share.equals(ip_B)){
                                outputfile = "src/main/resources/output/share_AC.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_A.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_C.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            else{
                                outputfile = "src/main/resources/output/share_AB.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_A.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_B.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            //utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                            String lastip = null;
                            for (Socket client : clients) {
                                String clientIP = client.getInetAddress().getHostAddress();
                                if (share.equals(clientIP) && !clientIP.equals(lastip)) {
                                    lastip = clientIP;
                                    if(share.equals(ip_A)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                                    }
                                    else if(share.equals(ip_B)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToB.xsl");
                                    }
                                    else if(share.equals(ip_C)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToC.xsl");
                                    }
                                    OutputStream clientOutput = client.getOutputStream();
                                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                    FileInputStream fis = new FileInputStream(outputfile);
                                    //发送行数便于客户端读取
                                    System.out.println("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile));
                                    byte[] bytes = ("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
                                    clientOutput.write(bytes);
                                    //发送合并后的文件
                                    byte[] buffer = new byte[1024];
                                    int len;
                                    while ((len = fis.read(buffer)) != -1) {
                                        clientOutput.write(buffer, 0, len);
                                    }
                                    fis.close();
                                    clientWriter.flush();
                                    System.out.println("文件发送完成。");
                                    //textArea.append("文件发送完成。" + "\n");
                                }
                            }
                        }
                        else{
                            String lastip = null;
                            for (Socket client : clients) {
                                String clientIP = client.getInetAddress().getHostAddress();
                                if (!share.equals(clientIP) && !clientIP.equals(lastip) && !clientIP.equals(ip_A)) {
                                    lastip =clientIP;
                                    OutputStream clientOutput = client.getOutputStream();
                                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                    clientWriter.println("share A");
                                    clientWriter.flush();
                                }
                            }
                        }

                    }
                    else if(message.equals("B will share *")){
                        String mm = reader.readLine();
                        //textArea.append("Received message from client: " + mm + "\n");
                        int line = Integer.parseInt(mm.substring(12));
                        System.out.println("行数为" + line);
                        FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_B.xml");
                        for(int j = 0;j < line;j++){
                            System.out.println(j);
                            String mes = reader.readLine();
                            byte[] bytes = (mes + "\n").getBytes();
                            fos.write(bytes);
                        }
                        fos.close();
                        share_count++;
                        if(share_count%2 == 0){
                            //合并文件并向share院系发送合并后的文件
                            String outputfile;
                            String outputfile3;
                            if(share.equals(ip_A)){
                                outputfile = "src/main/resources/output/share_BC.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_B.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_C.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            else if(share.equals(ip_B)){
                                outputfile = "src/main/resources/output/share_AC.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_A.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_C.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            else{
                                outputfile = "src/main/resources/output/share_AB.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_A.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_B.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            //utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                            String lastip = null;
                            for (Socket client : clients) {
                                String clientIP = client.getInetAddress().getHostAddress();
                                if (share.equals(clientIP) && !clientIP.equals(lastip)) {
                                    lastip = clientIP;
                                    if(share.equals(ip_A)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                                    }
                                    else if(share.equals(ip_B)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToB.xsl");
                                    }
                                    else if(share.equals(ip_C)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToC.xsl");
                                    }
                                    OutputStream clientOutput = client.getOutputStream();
                                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                    FileInputStream fis = new FileInputStream(outputfile);
                                    //发送行数便于客户端读取
                                    System.out.println("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile));
                                    byte[] bytes = ("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
                                    clientOutput.write(bytes);
                                    //发送合并后的文件
                                    byte[] buffer = new byte[1024];
                                    int len;
                                    while ((len = fis.read(buffer)) != -1) {
                                        clientOutput.write(buffer, 0, len);
                                    }
                                    fis.close();
                                    clientWriter.flush();
                                    System.out.println("文件发送完成。");
                                    //textArea.append("文件发送完成。" + "\n");
                                }
                            }
                        }
                        else{
                            String lastip = null;
                            for (Socket client : clients) {
                                String clientIP = client.getInetAddress().getHostAddress();
                                if (!share.equals(clientIP) && !clientIP.equals(lastip) && !clientIP.equals(ip_B)) {
                                    lastip =clientIP;
                                    OutputStream clientOutput = client.getOutputStream();
                                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                    clientWriter.println("share A");
                                    clientWriter.flush();
                                }
                            }
                        }
                    }
                    else if(message.equals("C will share *")){

                        String mm = reader.readLine();
                        //textArea.append("Received message from client: " + mm + "\n");
                        int line = Integer.parseInt(mm.substring(12));
                        System.out.println("行数为" + line);
                        FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_C.xml");
                        for(int j = 0;j < line;j++){
                            String mes = reader.readLine();
                            byte[] bytes = (mes + "\n").getBytes();
                            fos.write(bytes);
                        }
                        fos.close();
                        share_count++;
                        if(share_count%2 == 0){
                            //合并文件并向share院系发送合并后的文件
                            String outputfile;
                            String outputfile3;
                            if(share.equals(ip_A)){
                                outputfile = "src/main/resources/output/share_BC.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_B.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_C.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            else if(share.equals(ip_B)){
                                outputfile = "src/main/resources/output/share_AC.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_A.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_C.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            else{
                                outputfile = "src/main/resources/output/share_AB.xml";
                                String outputfile1 = "src/main/resources/output/share1.xml";
                                String outputfile2 = "src/main/resources/output/share2.xml";
                                outputfile3 = "src/main/resources/output/share3.xml";
                                utils.transformXML("src/main/resources/output/share_A.xml",
                                        outputfile1,"src/main/resources/Transfer/formatClass.xsl");
                                utils.transformXML("src/main/resources/output/share_B.xml",
                                        outputfile2,"src/main/resources/Transfer/formatClass.xsl");
                                utils.mergeXmlFiles(outputfile1,
                                        outputfile2,
                                        outputfile3);
                            }
                            //utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                            String lastip = null;
                            for (Socket client : clients) {
                                String clientIP = client.getInetAddress().getHostAddress();
                                if (share.equals(clientIP) && !clientIP.equals(lastip)) {
                                    lastip = clientIP;
                                    if(share.equals(ip_A)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToA.xsl");
                                    }
                                    else if(share.equals(ip_B)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToB.xsl");
                                    }
                                    else if(share.equals(ip_C)){
                                        utils.transformXML(outputfile3,outputfile,"src/main/resources/Transfer/classToC.xsl");
                                    }
                                    OutputStream clientOutput = client.getOutputStream();
                                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                    FileInputStream fis = new FileInputStream(outputfile);
                                    //发送行数便于客户端读取
                                    System.out.println("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile));
                                    byte[] bytes = ("准备发送共享文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
                                    clientOutput.write(bytes);
                                    //发送合并后的文件
                                    byte[] buffer = new byte[1024];
                                    int len;
                                    while ((len = fis.read(buffer)) != -1) {
                                        clientOutput.write(buffer, 0, len);
                                    }
                                    fis.close();
                                    clientWriter.flush();
                                    System.out.println("文件发送完成。");
                                    //textArea.append("文件发送完成。" + "\n");
                                }
                            }
                        }
                        else{
                            String lastip = null;
                            for (Socket client : clients) {
                                String clientIP = client.getInetAddress().getHostAddress();
                                if (!share.equals(clientIP) && !clientIP.equals(lastip) && !clientIP.equals(ip_C)) {
                                    lastip =clientIP;
                                    OutputStream clientOutput = client.getOutputStream();
                                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                    clientWriter.println("share A");
                                    clientWriter.flush();
                                }
                            }
                        }
                    }
                    //处理客户端选课请求
                    else if(message.contains("choose")){
                        String m = reader.readLine();
                        String[] mlist = message.split(" ");
                        String source = mlist[1];
                        String classid = mlist[2];
                        String target = mlist[3];
                        //target_ip记录目标客户端的ip
                        String target_ip;
                        if(target.equals("A")) target_ip = ip_A;
                        else if(target.equals("B")) target_ip = ip_B;
                        else target_ip = ip_C;
                        //读取选课表行数
                        int line = Integer.parseInt(m.substring(12));
                        System.out.println("行数为" + line);
                        String sourceStudentTable = "src/main/resources/output/information.xml";
                        String formatedStudentTable = "src/main/resources/output/pre-choice.xml";
                        String choosetable = "src/main/resources/output/choice.xml";
                        String format = "src/main/resources/Transfer/formatStudent.xsl";
                        String transChoiceFile = "";
                        String transStudentFile = "";
                        String targetChoiceTable = "src/main/resources/output/targetChoiceTable.xsl";
                        String targetStudentTable = "src/main/resources/output/targetStudentTable.xsl";
                        //接收学生信息表
                        FileOutputStream fos = new FileOutputStream(sourceStudentTable);
                        for(int j = 0;j < line;j++){
                            String mes = reader.readLine();
                            byte[] bytes = (mes + "\n").getBytes();
                            fos.write(bytes);
                        }
                        fos.close();
                        //生成目标格式的选课表和学生表
                        utils.transformXML(sourceStudentTable,formatedStudentTable,format);
                        utils.creatChoiceXmlFromCidAndStudentXML(classid,formatedStudentTable, choosetable);
                        switch(target){
                            case "A":transChoiceFile = "src/main/resources/Transfer/choiceToA.xsl";
                                transStudentFile = "src/main/resources/Transfer/studentToA.xsl";
                                break;
                            case "B":transChoiceFile = "src/main/resources/Transfer/choiceToB.xsl";
                                transStudentFile = "src/main/resources/Transfer/studentToB.xsl";
                                break;
                            case "C":transChoiceFile = "src/main/resources/Transfer/choiceToC.xsl";
                                transStudentFile = "src/main/resources/Transfer/studentToC.xsl";
                        }
                        utils.transformXML(choosetable,targetChoiceTable,transChoiceFile);
                        utils.transformXML(formatedStudentTable,targetStudentTable,transStudentFile);
                        //给目标客户端发送学生信息表和选课表
                        String lastip = null;
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (target_ip.equals(clientIP) && !clientIP.equals(lastip)) {
                                lastip = clientIP;
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                FileInputStream fis1 = new FileInputStream(targetStudentTable);
                                //发送学生信息表
                                System.out.println("准备发送选课文件，文件行数为：" + countLinesInXML(targetStudentTable));
                                //textArea.append("准备发送选课文件，文件行数为：" + countLinesInXML(targetStudentTable) + "\n");
                                byte[] bytes1 = ("准备发送选课文件，文件行数为：" + countLinesInXML(targetStudentTable) + "\n").getBytes();
                                clientOutput.write(bytes1);
                                byte[] buffer1 = new byte[1024];
                                int len1;
                                while ((len1 = fis1.read(buffer1)) != -1) {
                                    clientOutput.write(buffer1, 0, len1);
                                }
                                fis1.close();
                                //textArea.append("文件发送完成" + "\n");
                                clientWriter.flush();
                                //发送选课表
                                FileInputStream fis2 = new FileInputStream(targetChoiceTable);
                                System.out.println("准备发送选课文件，文件行数为：" + countLinesInXML(targetChoiceTable));
                                //textArea.append("准备发送选课文件，文件行数为：" + countLinesInXML(targetChoiceTable) + "\n");
                                byte[] bytes2 = ("准备发送选课文件，文件行数为：" + countLinesInXML(targetChoiceTable) + "\n").getBytes();
                                clientOutput.write(bytes2);
                                byte[] buffer2 = new byte[1024];
                                int len2;
                                while ((len2 = fis2.read(buffer2)) != -1) {
                                    clientOutput.write(buffer2, 0, len2);
                                }
                                fis2.close();
                                //textArea.append("文件发送完成" + "\n");
                                clientWriter.flush();
                            }
                        }
                    }
                    //处理退课请求
                    else if(message.contains("delete") && !message.contains("success") && !message.contains("fail")){
                        //textArea.append("开始删除" + "\n");
                        String[] mlist = message.split(" ");
                        String source = mlist[1];
                        String studentid = mlist[2];
                        String classid = mlist[3];
                        String target = mlist[4];
                        String target_ip;
                        if(target.equals("A")) target_ip = ip_A;
                        else if(target.equals("B")) target_ip = ip_B;
                        else target_ip = ip_C;
                        //textArea.append("目标客户端为" + target + ":" + target_ip + "\n");
                        //转发消息给目标客户端
                        String lastip = null;
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (target_ip.equals(clientIP) && !clientIP.equals(lastip)) {
                                lastip = clientIP;
                                //textArea.append("正在发送到目标客户端" + target + ":" + clientIP + "\n");
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                clientWriter.println("delete " + target + " " + studentid + " " + classid);
                                clientWriter.flush();
                            }
                        }
                    }
                    //接收客户端的处理成功反馈，不进行任何处理即可
                    else if(message.contains("success")){}
                    else{
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.println("内容无法识别。");
                        clientWriter.flush();
                    }
                }
            }
        } catch (IOException | TransformerException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
    // 按钮点击事件处理
    public void actionPerformed(String button) {
        //textArea.append("服务端按下了按钮" + button + "\n");
        if(button.equals("collect A")){
            //flag为false表示客户端未连接或连接了未记录ip，true表示连接了并且已经记录ip
            boolean flag = false;
            String lastip = null;
            for (Socket client : clients) {
                String clientIP = client.getInetAddress().getHostAddress();
                if (ip_A.equals(clientIP) && !clientIP.equals(lastip)) {
                    lastip = clientIP;
                    OutputStream clientOutput = null;
                    try {
                        clientOutput = client.getOutputStream();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                    clientWriter.println("collect *");
                    clientWriter.flush();
                    flag = true;
                }
            }
            //if(!flag){ textArea.append("服务端A未连接" + "\n"); }
        }
        else if(button.equals("collect B")){
            boolean flag = false;
            String lastip = null;
            for (Socket client : clients) {
                String clientIP = client.getInetAddress().getHostAddress();
                if (ip_B.equals(clientIP) && !clientIP.equals(lastip)) {
                    lastip = clientIP;
                    OutputStream clientOutput = null;
                    try {
                        clientOutput = client.getOutputStream();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                    clientWriter.println("collect *");
                    clientWriter.flush();
                    flag = true;
                }
            }
            //if(!flag){ textArea.append("服务端B未连接" + "\n"); }
        }
        else if(button.equals("collect C")){
            boolean flag = false;
            String lastip = null;
            for (Socket client : clients) {
                String clientIP = client.getInetAddress().getHostAddress();
                if (ip_C.equals(clientIP) && !clientIP.equals(lastip)) {
                    lastip = clientIP;
                    OutputStream clientOutput = null;
                    try {
                        clientOutput = client.getOutputStream();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                    clientWriter.println("collect *");
                    clientWriter.flush();
                    flag = true;
                }
            }
            //if(!flag){ textArea.append("服务端C未连接" + "\n"); }
        }

    }

    public static int countLinesInXML(String path) throws IOException {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int lines = 0;
        while (bufferedReader.readLine() != null) {
            lines++;
        }
        bufferedReader.close();
        fileReader.close();
        return lines;
    }

    public void run() {
        // 创建服务端Socket并等待客户端连接
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //homeController.addString("aaa");
        System.out.println("服务端已启动，等待客户端连接...\n");
        while (true) {
            //监听客户端
            final Socket socket;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //textArea.append("有客户端连接，IP地址为：" + socket.getInetAddress().getHostAddress() + "\n");
            System.out.println("有客户端连接");
            clients.add(socket);
            System.out.println(socket.getInetAddress().getHostAddress());
            //开启新的线程处理
            executorService.execute(new Runnable() {
                public void run() {
                    handle(socket);
                }
            });
        }
    }
}