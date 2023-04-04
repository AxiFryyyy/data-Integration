package org.example;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Demo_4 {
    public static void main(String[] args) throws IOException, DocumentException {
        String phone = "18061389075";
//        获取到XML资源的输入流
        URL url = new URL("https://apis.juhe.cn/mobile/get?phone="+phone+"&dtype=xml&key=9f3923e8f87f1ea50ed4ec8c39cc9253");
//       openConnection:打开链接
        URLConnection coon = url.openConnection();
//       获得网址下的输入流
        InputStream is = coon.getInputStream();
//     创建一个XML读取对象
        SAXReader sr = new SAXReader();
//          通过读取对象 读取XML数据，并返回文档对象
        Document doc = sr.read(is);
//        selectSingleNode：查找单个
        Node n =doc.selectSingleNode("//province");
        System.out.println(n.getText());



    }
}