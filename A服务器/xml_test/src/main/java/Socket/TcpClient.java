package Socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        InetAddress serverIP=null;
        Socket socket=null;
        OutputStream os=null;

        try{
            //1、要知道服务器地址，端口号
            serverIP=InetAddress.getByName("172.21.20.31");
            int port=9988;
            //2、创建一个socket连接
            socket=new Socket(serverIP,port);
            //发送消息IO流
            os=socket.getOutputStream();
            os.write("hello".getBytes());

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (os !=null) {
                    os.close();
                }
                if(socket!=null){
                    socket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}