package org.example;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Demo_1 {
    public static void main(String[] args) {
        try {
//            获取文件输入流“该地址为存放上述books的XML文件的地址
            FileInputStream fis = new FileInputStream("src/main/resources/books.xml");
//          创建XML读取工具的对象
            SAXReader sr = new SAXReader();
//           通过读取工具，读取XML文档的输入流，并得到文档对象
            Document doc = sr.read(fis);
//            通过文档对象，获取文档的根节点对象
            Element root = doc.getRootElement();
//            输出根节点包含的内容
            System.out.println(root.getName());
//            获取根节点名称
            List<Element> es = root.elements();
//            循环遍历两个book
            for(Element e :es){
//                attributeValue：获取id属性值
                System.out.println(e.attributeValue("id"));
//               elementText：获取子节点 name
                System.out.println(e.elementText("name"));
//                elementText：获取子节点 info
                System.out.println(e.elementText("info"));
            }
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

    }
}