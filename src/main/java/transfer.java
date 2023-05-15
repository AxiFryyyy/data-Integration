
import org.dom4j.DocumentException;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class transfer {
    /*public static void main(String args[]) throws DocumentException, IOException, TransformerException {
        transferXML();
    }*/

    public static void transferXML(String filename) throws IOException, DocumentException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        //Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\Transfer\\"+filename)));
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(filename)));
        Source xmlSource = new StreamSource(new File("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\student_format.xml"));
        Result result = new StreamResult(new File("D:\\南大作业\\数据集成\\data-Integration-zg\\data-Integration-zg\\Integrated_Server\\src\\main\\resources\\student_out.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlSource, result);
        transformer.reset();
    }
}
