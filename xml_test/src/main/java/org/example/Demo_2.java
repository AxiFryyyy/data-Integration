package org.example;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo_2 {
    public static void main(String[] args) throws IOException, DocumentException {
        String phone = "18805150631";
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
//getRootElement:获取根节点
        Element root = doc.getRootElement();
//        解析内容
        String code = root.elementText("resultcode");
        if("200".equals(code)){//如果返回码为200开始解析
//            讲解析的根节点返回给Element对象
            Element result = root.element("result");
//            讲解析的province返回给String对象
            String province = result.elementText("province");
            //            讲解析的city返回给String对象
            String city = result.elementText("city");
            if(province.equals(city)){//如果省份和城市相等直接返回城市名
                System.out.println("手机号码归属地"+city);
            }else{//否则返回省会加城市名
                System.out.println("手机号归属地"+province+" "+city);
            }
        }else{
            System.out.println("手机号有误");
        }
    }
}