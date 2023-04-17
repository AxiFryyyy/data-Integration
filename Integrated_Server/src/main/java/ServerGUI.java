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
    private JButton buttonA, buttonB, buttonC;
    private static JTextArea textArea;
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
        buttonA.addActionListener(this);
        buttonB.addActionListener(this);
        buttonC.addActionListener(this);
        panel.add(buttonA);
        panel.add(buttonB);
        panel.add(buttonC);
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        // 添加组件
        Container container = getContentPane();
        container.add(panel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
    }
    public static void handle(Socket socket) {
        System.out.println("线程ID:" + Thread.currentThread().getId()
                + "  线程名称:" + Thread.currentThread().getName());
        textArea.append("线程ID:" + Thread.currentThread().getId()
                + "  线程名称:" + Thread.currentThread().getName());
        try {
            while (true) {
                // 将客户端加入列表
                clients.add(socket);
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
                    textArea.append("Received message from client: " + message + "\n");
                    //客户端表明通信身份，客户端连接后必须先向服务端发送自己的身份，否则一些操作将无法完成
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
                    //按下collect按钮后，客户端发送will send *，识别这些消息并接收三个文件
                    else if(message.equals("A will send *")){
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        clientWriter.flush();
                        for(int i = 0;i < 3;i++){
                            String m = reader.readLine();
                            int line = Integer.parseInt(m.substring(12));
                            System.out.println("行数为" + line);
                            textArea.append("行数为" + line + "\n");
                            FileOutputStream fos = new FileOutputStream("src/main/resources/output/docA"+i+".xml");
                            for(int j = 0;j < line;j++){
                                String mes = reader.readLine();
                                byte[] bytes = (mes + "\n").getBytes();
                                fos.write(bytes);
                            }
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
                            FileOutputStream fos = new FileOutputStream("src/main/resources/output/docB"+i+".xml");
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
                            FileOutputStream fos = new FileOutputStream("src/main/resources/output/docC"+i+".xml");
                            for(int j = 0;j < line;j++){
                                String mes = reader.readLine();
                                byte[] bytes = (mes + "\n").getBytes();
                                fos.write(bytes);
                            }
                            fos.close();
                        }
                    }
                    //处理A客户端发送share请求（因为这段代码比较长，所以share B和C暂时没处理，反正同理复制粘贴即可）
                    else if(message.equals("share A")){
                        for (Socket client : clients) {
                            String clientIP = client.getInetAddress().getHostAddress();
                            if (!ip_A.equals(clientIP)) {
                                OutputStream clientOutput = client.getOutputStream();
                                PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                                //转发A的share请求到其他客户端
                                clientWriter.println(message);
                                clientWriter.flush();
                                String m = reader.readLine();
                                //处理其他客户端发来的响应，并接收文件
                                if(m.equals("A will share *")){
                                    String mm = reader.readLine();
                                    int line = Integer.parseInt(mm.substring(12));
                                    System.out.println("行数为" + line);
                                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_A.xml");
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
                                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_B.xml");
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
                                    FileOutputStream fos = new FileOutputStream("src/main/resources/output/share_C.xml");
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
                        String outputfile = "src/main/resources/share_BC.xml";
                        utils.mergeXmlFiles("src/main/resources/output/share_B.xml",
                                "src/main/resources/output/share_C.xml",
                                outputfile);
                        OutputStream clientOutput = socket.getOutputStream();
                        PrintWriter clientWriter = new PrintWriter(new OutputStreamWriter(clientOutput));
                        FileInputStream fis = new FileInputStream(outputfile);
                        //发送行数便于客户端读取
                        System.out.println("准备发送文件，文件行数为：" + countLinesInXML(outputfile));
                        byte[] bytes = ("准备发送文件，文件行数为：" + countLinesInXML(outputfile) + "\n").getBytes();
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
                        textArea.append("文件发送完成。" + "\n");
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
                        String informationtable = "src/main/resources/output/information.xml";
                        String choosetable = "src/main/resources/output/choice.xml";
                        //接收选课表
                        FileOutputStream fos = new FileOutputStream(informationtable);
                        for(int j = 0;j < line;j++){
                            String mes = reader.readLine();
                            byte[] bytes = (mes + "\n").getBytes();
                            fos.write(bytes);
                        }
                        fos.close();
                        //处理选课表
                        utils.creatChoiceXmlFromCidAndStudentXML(classid,informationtable, choosetable);
                        //给目标客户端发送学生信息表和选课表
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
                                textArea.append("文件发送完成" + "\n");
                                clientWriter.flush();

                            }
                        }
                    }
                    //处理退课请求
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
                        //转发消息给目标客户端
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
    public void actionPerformed(ActionEvent e) {
        String button = ((JButton) e.getSource()).getText();
        textArea.append("服务端按下了按钮" + button + "\n");
        if(button.equals("collect A")){
            //flag为false表示客户端未连接或连接了未记录ip，true表示连接了并且已经记录ip
            boolean flag = false;
            for (Socket client : clients) {
                String clientIP = client.getInetAddress().getHostAddress();
                if (ip_A.equals(clientIP)) {
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
            if(!flag){ textArea.append("服务端A未连接" + "\n"); }
        }
        else if(button.equals("collect B")){
            boolean flag = false;
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
                    flag = true;
                }
            }
            if(!flag){ textArea.append("服务端B未连接" + "\n"); }
        }
        else if(button.equals("collect C")){
            boolean flag = false;
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
                    flag = true;
                }
            }
            if(!flag){ textArea.append("服务端C未连接" + "\n"); }
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

    public static void main(String[] args) throws IOException {
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