import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Client_A {
    public static void main(String[] args) {
        try {
            String studenttable = "src/main/resources/Transfer/studentToA.xsl";
            String coursetable = "src/main/resources/Transfer/studentToB.xsl";
            String selecttable = "src/main/resources/Transfer/studentToC.xsl";
            String sharetable = "src/main/resources/Transfer/classToA.xsl";
            String choosetable = "src/main/resources/Transfer/choiceToA.xsl";
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
            //已接收的文件数量
            AtomicInteger doc_count = new AtomicInteger();
            // 创建一个线程用于接收服务端的消息
            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = reader.readLine();
                        System.out.println("Received message from server: " + message);
                        if (message == null) {
                            break;
                        }
                        //接收文件
                        else if (message.contains("准备发送文件")) {
                            System.out.println(message);
                            String length = message.substring(13);
                            int l = Integer.parseInt(length);
                            FileOutputStream fos = new FileOutputStream("src/main/resources/output/a"+doc_count+".xml");
                            for(int i = 0;i < l;i++){
                                String m = reader.readLine();
                                byte[] bytes = (m + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                            System.out.println("第"+doc_count+"个文件接收完成。");
                            doc_count.getAndIncrement();
                        }
                        //处理服务端的collect请求，发送三个文件
                        else if(message.equals("collect *")){
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
                        //处理服务端转发的别的客户端的share请求，把共享表发送给服务端
                        else if(message.contains("share")){
                            writer.println("A will share *");
                            writer.flush();
                            writer.println("共享表准备发送，行数为：" + countLinesInXML(sharetable));
                            writer.flush();
                            sendmessage(socket,sharetable);
                        }
                        //接收服务端转发的其他客户端的delete请求，需要完成在客户端上继续完成删除功能
                        else if(message.contains("delete")){
                            //TO DO
                            writer.println("delete success");
                            writer.flush();
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
    //发送文件方法
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
        for(int i = 0;i < lines;i++){
            writer.println(bufferedReader.readLine());
            writer.flush();
        }
    }
    //数文件行数方法
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
}