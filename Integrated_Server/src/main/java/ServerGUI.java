import org.dom4j.DocumentException;
import org.xml.sax.SAXException;

import java.awt.*;
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

public class ServerGUI extends JFrame implements ActionListener {
    private JButton buttonA, buttonB, buttonC, buttonD, buttonE;
    private static JTextArea textArea;
    private PrintWriter pw;
    private Socket socket;
    public static List<Socket> clients = new ArrayList<>();
    public static String ip_A;
    public static String ip_B;
    public static String ip_C;
    //创建一个线程池,如果有客户端连接就创建一个线程, 与之通信
    public static ExecutorService executorService = Executors.newCachedThreadPool();
    public ServerGUI() throws IOException {
        super("服务端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        // 创建组件
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 5));
        buttonA = new JButton("collect A");
        buttonB = new JButton("collect B");
        buttonC = new JButton("collect C");
        //buttonD = new JButton("D");
        //buttonE = new JButton("E");
        buttonA.addActionListener(this);
        buttonB.addActionListener(this);
        buttonC.addActionListener(this);
        //buttonD.addActionListener(this);
        //buttonE.addActionListener(this);
        panel.add(buttonA);
        panel.add(buttonB);
        panel.add(buttonC);
        //panel.add(buttonD);
        //panel.add(buttonE);
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        // 添加组件
        Container container = getContentPane();
        container.add(panel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        // 创建服务端Socket并等待客户端连接
        //ServerSocket serverSocket = new ServerSocket(9999);
        //textArea.append("服务端已启动，等待客户端连接...\n");
        /*while (true) {
            //3.监听客户端
            final Socket socket = serverSocket.accept();
            textArea.append("有客户端连接，IP地址为：" + socket.getInetAddress().getHostAddress() + "\n");
            System.out.println("有客户端连接");
            clients.add(socket);
            System.out.println(socket.getInetAddress().getHostAddress());

            //4.开启新的线程处理
            executorService.execute(new Runnable() {
                public void run() {
                    handle(socket);
                }
            });
        }*/

    }
    public static void handle(Socket socket) {
        System.out.println("线程ID:" + Thread.currentThread().getId()
                + "  线程名称:" + Thread.currentThread().getName());
        textArea.append("线程ID:" + Thread.currentThread().getId()
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
                    textArea.append("Received message from client: " + message + "\n");
                    if(message.equals("A")){
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
                    /*else if(message.equals("A_@B_message")){
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
                    }*/
                    else if(message.equals("A will send *")){
                        //System.out.println("1");
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        //clientWriter.println("collect *");
                        clientWriter.flush();
                        for(int i = 0;i < 3;i++){
                            //System.out.println("2");
                            String m = reader.readLine();
                            //System.out.println("3");
                            int line = Integer.parseInt(m.substring(12));
                            System.out.println("行数为" + line);
                            textArea.append("行数为" + line + "\n");
                            FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\docA"+i+".xml");
                            //System.out.println("1");
                            for(int j = 0;j < line;j++){
                                String mes = reader.readLine();
                                byte[] bytes = (mes + "\n").getBytes();
                                fos.write(bytes);
                            }
                            //System.out.println("4");
                            fos.close();
                        }
                    }
                    else if(message.equals("B will send *")){
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.flush();
                        for(int i = 0;i < 3;i++){
                            String m = reader.readLine();
                            int line = Integer.parseInt(m.substring(12));
                            System.out.println("行数为" + line);
                            FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\docB"+i+".xml");
                            for(int j = 0;j < line;j++){
                                String mes = reader.readLine();
                                byte[] bytes = (mes + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                        }
                    }
                    else if(message.equals("C will send *")){
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.flush();
                        for(int i = 0;i < 3;i++){
                            String m = reader.readLine();
                            int line = Integer.parseInt(m.substring(12));
                            System.out.println("行数为" + line);
                            FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\docC"+i+".xml");
                            for(int j = 0;j < line;j++){
                                String mes = reader.readLine();
                                byte[] bytes = (mes + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                        }
                    }
                    else if(message.equals("share A")){
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (!ip_A.equals(clientIP)) {
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                clientWriter.println(message);
                                clientWriter.flush();
                                //Thread.sleep(1000);
                                String m = reader.readLine();
                                if(m.equals("A will share *")){
                                    String mm = reader.readLine();
                                    int line = Integer.parseInt(mm.substring(12));
                                    System.out.println("行数为" + line);
                                    FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\share_A.xml");
                                    for(int j = 0;j < line;j++){
                                        String mes = reader.readLine();
                                        byte[] bytes = (mes + "\n").getBytes();
                                        fos.write(bytes);
                                    }
                                    fos.close();
                                }
                                else if(m.equals("B will share *")){
                                    String mm = reader.readLine();
                                    int line = Integer.parseInt(mm.substring(12));
                                    System.out.println("行数为" + line);
                                    FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\share_B.xml");
                                    for(int j = 0;j < line;j++){
                                        String mes = reader.readLine();
                                        byte[] bytes = (mes + "\n").getBytes();
                                        fos.write(bytes);
                                    }
                                    fos.close();
                                }
                                else if(m.equals("C will share *")){
                                    String mm = reader.readLine();
                                    int line = Integer.parseInt(mm.substring(12));
                                    System.out.println("行数为" + line);
                                    FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\share_C.xml");
                                    for(int j = 0;j < line;j++){
                                        String mes = reader.readLine();
                                        byte[] bytes = (mes + "\n").getBytes();
                                        fos.write(bytes);
                                    }
                                    fos.close();
                                }
                                clientWriter.println("share success");
                                clientWriter.flush();
                            }
                        }
                        //转化并向A院系发送合并后的文件
                        String outputfile = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\share_BC.xml";
                        utils.mergeXmlFiles("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\share_B.xml",
                                "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\share_C.xml",
                                outputfile);
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        FileInputStream fis = new FileInputStream(outputfile);
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

                    else if(message.contains("choose")){
                        String m = reader.readLine();
                        String[] mlist = message.split(" ");
                        String source = mlist[1];
                        String classid = mlist[2];
                        String target = mlist[3];
                        String target_ip;
                        if(target.equals("A")) target_ip = ip_A;
                        else if(target.equals("B")) target_ip = ip_B;
                        else target_ip = ip_C;
                        int line = Integer.parseInt(m.substring(12));
                        System.out.println("行数为" + line);
                        String informationtable = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\information.xml";
                        String choosetable = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\choice.xml";
                        FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\information.xml");
                        for(int j = 0;j < line;j++){
                            String mes = reader.readLine();
                            byte[] bytes = (mes + "\n").getBytes();
                            fos.write(bytes);
                        }
                        fos.close();
                        utils.creatChoiceXmlFromCidAndStudentXML(classid,"D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\information.xml",
                                "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\choice.xml");
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (target_ip.equals(clientIP)) {
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                FileInputStream fis1 = new FileInputStream(informationtable);
                                //发送学生信息表
                                System.out.println("准备发送文件，文件行数为：" + countLinesInXML(informationtable));
                                textArea.append("准备发送文件，文件行数为：" + countLinesInXML(informationtable) + "\n");
                                byte[] bytes1 = ("准备发送文件，文件行数为：" + countLinesInXML(informationtable) + "\n").getBytes();
                                clientOutput.write(bytes1);
                                byte[] buffer1 = new byte[1024];
                                int len1;
                                while ((len1 = fis1.read(buffer1)) != -1) {
                                    clientOutput.write(buffer1, 0, len1);
                                }
                                fis1.close();
                                textArea.append("文件发送完成" + "\n");
                                //clientWriter.println("文件发送完成。");
                                clientWriter.flush();
                                //发送选课表
                                FileInputStream fis2 = new FileInputStream(choosetable);
                                System.out.println("准备发送文件，文件行数为：" + countLinesInXML(choosetable));
                                textArea.append("准备发送文件，文件行数为：" + countLinesInXML(choosetable) + "\n");
                                byte[] bytes2 = ("准备发送文件，文件行数为：" + countLinesInXML(choosetable) + "\n").getBytes();
                                clientOutput.write(bytes2);
                                byte[] buffer2 = new byte[1024];
                                int len2;
                                while ((len2 = fis2.read(buffer1)) != -1) {
                                    clientOutput.write(buffer2, 0, len2);
                                }
                                fis2.close();
                                //clientWriter.println("文件发送完成。");
                                textArea.append("文件发送完成" + "\n");
                                clientWriter.flush();

                            }
                        }
                    }
                    else if(message.contains("delete") && !message.contains("success")){
                        String[] mlist = message.split(" ");
                        String source = mlist[1];
                        String studentid = mlist[2];
                        String classid = mlist[3];
                        String target = mlist[4];
                        String target_ip;
                        if(target.equals("A")) target_ip = ip_A;
                        else if(target.equals("B")) target_ip = ip_B;
                        else target_ip = ip_C;
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (target_ip.equals(clientIP)) {
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                clientWriter.println("delete " + target + " " + studentid + " " + classid);
                                clientWriter.flush();
                            }
                        }
                    }
                    /*else if(message.equals("A_studentToB")){
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        String address = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\studentToB.xsl";
                        transfer.transferXML(address);
                        String outputfile = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\student_A.xml";
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
                    }*/
                    else if(message.contains("success")){}
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
        } catch (IOException | TransformerException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
    // 按钮点击事件处理
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getText();
        //pw.println("服务端按下了按钮" + button);
        //pw.flush();
        if(button.equals("collect A")){
            for (Socket client : clients) {
                String clientIP = client.getInetAddress().getHostAddress();
                if (ip_A.equals(clientIP)) {
                    OutputStream clientOutput = null;
                    try {
                        clientOutput = client.getOutputStream();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    //assert clientOutput != null;
                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                    clientWriter.println("collect *");
                    clientWriter.flush();
                }
            }
        }
        else if(button.equals("collect B")){
            for (Socket client : clients) {
                String clientIP = client.getInetAddress().getHostAddress();
                if (ip_B.equals(clientIP)) {
                    OutputStream clientOutput = null;
                    try {
                        clientOutput = client.getOutputStream();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                    clientWriter.println("collect *");
                    clientWriter.flush();
                }
            }
        }
        else if(button.equals("collect C")){
            for (Socket client : clients) {
                String clientIP = client.getInetAddress().getHostAddress();
                if (ip_C.equals(clientIP)) {
                    OutputStream clientOutput = null;
                    try {
                        clientOutput = client.getOutputStream();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                    clientWriter.println("collect *");
                    clientWriter.flush();
                }
            }
        }
        textArea.append("服务端按下了按钮" + button + "\n");
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

    public static void main(String[] args) throws IOException {

        //ExecutorService executorService = Executors.newCachedThreadPool();
        ServerGUI serverGUI = new ServerGUI();
        serverGUI.setVisible(true);
        // 创建服务端Socket并等待客户端连接
        ServerSocket serverSocket = new ServerSocket(9999);
        textArea.append("服务端已启动，等待客户端连接...\n");
        while (true) {
            //监听客户端
            final Socket socket = serverSocket.accept();
            textArea.append("有客户端连接，IP地址为：" + socket.getInetAddress().getHostAddress() + "\n");
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