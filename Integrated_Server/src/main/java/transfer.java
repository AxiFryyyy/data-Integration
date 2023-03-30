import jdk.internal.util.xml.XMLStreamException;
import jdk.internal.util.xml.impl.XMLWriter;
import org.apache.xml.serialize.OutputFormat;
import org.dom4j.DocumentException;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class transfer {
    public static void main(String args[]) throws DocumentException, IOException, TransformerException {
        transferXML();
    }

    public static void transferXML() throws IOException, DocumentException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File("D:\\课程资料\\23-Spring-数据集成\\作业\\Integrated_Server\\src\\main\\resources\\Transfer\\studentToA.xsl")));
        Source xmlSource = new StreamSource(new File("D:\\课程资料\\23-Spring-数据集成\\作业\\Integrated_Server\\src\\main\\resources\\student_format.xml"));
        Result result = new StreamResult(new File("D:\\课程资料\\23-Spring-数据集成\\作业\\Integrated_Server\\src\\main\\resources\\student_out.xml"));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlSource, result);
        transformer.reset();
    }
}
