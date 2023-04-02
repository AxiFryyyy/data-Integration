import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client_C {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        Scanner scanner = null;
        try {
            while (true) {
                // 创建Socket对象
                socket = new Socket("localhost", 1234);
                System.out.println("Connected to server.");
                // 创建输入流和输出流
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                scanner = new Scanner(System.in);
                // 读取用户输入的消息
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                // 发送消息给服务端
                out.write(message);
                out.newLine();
                out.flush();
                System.out.println("Message sent.");
                InputStream ins = socket.getInputStream();
                FileOutputStream fos = new FileOutputStream("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\output\\a.xml");
                byte[] buffer = new byte[1024];
                int len;
                while ((len = ins.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
                // 接收服务端发送的响应消息
                String response = in.readLine();
                System.out.println("Received response: " + response);
                fos.close();
                ins.close();
            }
        } finally {
            // 关闭连接和流
            if (in != null) in.close();
            if (out != null) out.close();
            if (scanner != null) scanner.close();
            if (socket != null) socket.close();
        }
    }
}
