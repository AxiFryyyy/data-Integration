package org.example;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Demo_3 {
    public static void main(String[] args) {
        try {
//            获取文件输入流“该地址为存放上述books的XML文件的地址
            FileInputStream fis = new FileInputStream("src/main/resources/books.xml");
//          创建XML读取工具的对象
            SAXReader sr = new SAXReader();
//           通过读取工具，读取XML文档的输入流，并得到文档对象
            Document doc = sr.read(fis);
//            Node是elememt和document的父
//            selectNodes：查找集合
            List<Node> names = doc.selectNodes("//name");
            for(int i =0;i<names.size();i++){
                System.out.println(names.get(i).getName());
                System.out.println(names.get(i).getText());
            }
            System.out.println("-------------");
//              selectSingleNode：查找单个
            Node n = doc.selectSingleNode("//book['id = 1001']//name");
            System.out.println(n.getName());
            System.out.println(n.getText());


        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

    }
}