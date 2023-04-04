import org.dom4j.DocumentException;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ser {
    public static List<Socket> clients = new ArrayList<>();
    public static String ip_A = "127.0.0.1";
    public static String ip_B = "127.0.0.2";
    public static String ip_C = "127.0.0.3";
    public static void main(String[] args) throws IOException {
        //1.创建一个线程池,如果有客户端连接就创建一个线程, 与之通信
        ExecutorService executorService = Executors.newCachedThreadPool();
        //2.创建 ServerSocket 对象
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("Server is listening on port 9999...");
        while (true) {
            //3.监听客户端
            final Socket socket = serverSocket.accept();
            System.out.println("有客户端连接");
            clients.add(socket);
            System.out.println(socket.getInetAddress().getHostAddress());

            //4.开启新的线程处理
            executorService.execute(new Runnable() {
                public void run() {
                    handle(socket);
                }
            });
        }
    }
    public static void handle(Socket socket) {
        System.out.println("线程ID:" + Thread.currentThread().getId()
                + "  线程名称:" + Thread.currentThread().getName());
        try {
            // 创建 ServerSocket 并监听指定端口
            //ServerSocket serverSocket = new ServerSocket(9999);

            // 保存所有已连接的客户端
            //List<Socket> clients = new ArrayList<>();
            while (true) {
                // 等待客户端连接
                //Socket socket = serverSocket.accept();
                //System.out.println("Connected to client " + socket.getRemoteSocketAddress());
                // 将客户端加入列表
                clients.add(socket);
                // 获取客户端输入流
                InputStream inputStream = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                // 获取客户端输出流
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
                // 读取客户端消息
                //OutputStream os = socket.getOutputStream();
                while(true){
                    String message = reader.readLine();
                    System.out.println("Received message from client: " + message);
                    if(message.equals("A_@B_message")){
                        // 找到 IP 地址为 ip_B 的客户端并发送消息
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (ip_B.equals(clientIP)) {
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                clientWriter.println(message);
                                clientWriter.flush();
                            }
                        }
                    }
                    else if(message.equals("A_studentToB")){
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        String address = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\studentToB.xsl";
                        transfer.transferXML(address);
                        String outputfile = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\student_out.xml";
                        FileInputStream fis = new FileInputStream(outputfile);
                        //clientWriter.println("准备发送文件，文件行数为：" + countLinesInXML(outputfile));
                        System.out.println("准备发送文件，文件行数为：" + countLinesInXML(outputfile));
                        byte[] bytes = ("准备发送文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
                        clientOutput.write(bytes);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = fis.read(buffer)) != -1) {
                            clientOutput.write(buffer, 0, len);
                        }
                        fis.close();
                        //clientWriter.println("文件发送完成。");
                        clientWriter.flush();
                        System.out.println("文件发送完成。");
                    }
                    else{
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.println("内容无法识别。");
                        clientWriter.flush();
                    }

                }

                // 关闭连接
                //socket.close();
                //clients.remove(socket);
            }
        } catch (IOException | DocumentException | TransformerException e) {
            e.printStackTrace();
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