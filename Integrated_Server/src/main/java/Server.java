import org.dom4j.DocumentException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("服务端已启动，等待客户端连接...");
            while (true) {
                ServerSocket serverSocket = new ServerSocket(1234);
                Socket clientSocket = serverSocket.accept();
                System.out.println("客户端连接成功，客户端地址：" + clientSocket.getInetAddress().getHostAddress() + ", 端口号：" + clientSocket.getPort());
                BufferedReader in = null;
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                OutputStream out = clientSocket.getOutputStream();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String input = in.readLine();
                System.out.println("服务端已收到消息：" + input);
                if (input.equals("A_studentToB")) {
                    String address = "D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\studentToB.xsl";
                    transfer.transferXML(address);
                    FileInputStream fis = new FileInputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\student_out.xml");
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fis.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    fis.close();
                    System.out.println("文件发送完成。");
                    output.write("文件发送完成。");
                    output.newLine();
                    output.flush();
                }
                //此处省略好几个else if
                else {
                    System.out.println("内容无法识别。");
                    output.write("内容无法识别。");
                    output.newLine();
                    output.flush();
                }
                out.close();
                clientSocket.close();
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
