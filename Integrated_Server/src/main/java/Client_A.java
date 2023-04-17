import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Client_A {
    public static void main(String[] args) {
        try {
            String studenttable = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\studentToA.xsl";
            String coursetable = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\studentToB.xsl";
            String selecttable = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\studentToC.xsl";
            String sharetable = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\classToA.xsl";
            String choosetable = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\choiceToA.xsl";
            // 创建 Socket 并连接到指定地址和端口
            Socket socket = new Socket("127.0.0.1", 9999);
            System.out.println("Connected to server " + socket.getRemoteSocketAddress());
            // 获取客户端输入流
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // 获取客户端输出流
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
            //已接受的文件数量
            AtomicInteger doc_count = new AtomicInteger();
            // 创建一个线程用于接收服务端的消息
            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = reader.readLine();
                        if (message == null) {
                            break;
                        }
                        /*else if (message.contains("@")) {
                            System.out.println("正在处理：" + message);
                            //处理别的客户端的请求
                            //to do
                            System.out.println("处理完毕。");
                        }*/
                        else if (message.contains("准备发送文件")) {
                            System.out.println(message);
                            String length = message.substring(13);
                            int l = Integer.parseInt(length);
                            //OutputStream os = socket.getOutputStream();
                            FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\a"+doc_count+".xml");
                            //System.out.println("1");
                            for(int i = 0;i < l;i++){
                                String m = reader.readLine();
                                byte[] bytes = (m + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                            System.out.println("第"+doc_count+"个文件接收完成。");
                            doc_count.getAndIncrement();
                        }
                        else if(message.equals("collect *")){

                            //OutputStream os = socket.getOutputStream();
                            writer.println("A will send *");
                            writer.flush();
                            writer.println("学生表准备发送，行数为：" + countLinesInXML(studenttable));
                            writer.flush();
                            sendmessage(socket,studenttable);
                            writer.println("课程表准备发送，行数为：" + countLinesInXML(coursetable));
                            writer.flush();
                            sendmessage(socket,coursetable);
                            writer.println("选课表准备发送，行数为：" + countLinesInXML(selecttable));
                            writer.flush();
                            sendmessage(socket,selecttable);
                        }
                        else if(message.contains("share")){
                            writer.println("A will share *");
                            writer.flush();
                            writer.println("共享表准备发送，行数为：" + countLinesInXML(sharetable));
                            writer.flush();
                            sendmessage(socket,sharetable);
                        }
                        else if(message.contains("delete")){
                            //TO DO
                            writer.println("delete success");
                            writer.flush();
                        }
                        else {
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
                else if(message.contains("choose")){
                    writer.println(message);
                    writer.flush();
                    writer.println("选课表准备发送，行数为：" + countLinesInXML(choosetable));
                    writer.flush();
                    sendmessage(socket,choosetable);
                }
                else{
                    writer.println(message);
                    writer.flush();
                }

            }
            // 关闭连接
            receiveThread.interrupt();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendmessage(Socket socket,String path) throws IOException {
        // 获取客户端输入流
        InputStream inputStream = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        // 获取客户端输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        int lines = countLinesInXML(path);
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        /*while (bufferedReader.readLine() != null) {
            writer.println(bufferedReader.readLine());
            writer.flush();
        }*/
        for(int i = 0;i < lines;i++){
            writer.println(bufferedReader.readLine());
            writer.flush();
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
        //System.out.println("XML文件共有" + lines + "行。");
        bufferedReader.close();
        fileReader.close();
        return lines;
    }
}