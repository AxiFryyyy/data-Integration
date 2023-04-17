
import org.dom4j.DocumentException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class utils {
    /*public static void main(String args[]) throws DocumentException, IOException, TransformerException, ParserConfigurationException, SAXException {
        String path1 = "src/main/resources/Integrated_Server/studentFormated.xml";
        String path2 = "src/main/resources/Validate/formatStudent.xsd";
        System.out.println(validateXmlWithXsd(path1,path2));;

    }*/

    /**
     * 格式转化
     * @param inputFile 各院系文件
     * @param outFile 标准化后的文件
     * @param transformFile 用于转化的xsd文件
     * @throws TransformerException
     */
    public static void transformXML(String inputFile, String outFile, String transformFile) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(transformFile)));
        Source xmlSource = new StreamSource(new File(inputFile));
        Result result = new StreamResult(new File(outFile));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(xmlSource, result);
        transformer.reset();
    }

    /**
     * 合并两个格式相同的文件
     * @param file1 文件1
     * @param file2 文件2
     * @param outFile 合并后文件
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static void mergeXmlFiles(String file1,String file2,String outFile) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        // 读取XML文件
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc1 = docBuilder.parse(new File(file1));
        Document doc2 = docBuilder.parse(new File(file2));

        // 将第二个XML文件的内容添加到第一个XML文件中
        NodeList nodes = doc2.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = doc1.importNode(nodes.item(i), true);
            doc1.getDocumentElement().appendChild(node);
        }
        // 将合并后的XML写入一个新文件
        TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(new javax.xml.transform.dom.DOMSource(doc1), new javax.xml.transform.stream.StreamResult(new File(outFile)));
    }

    /** 处理课程id和标准学生表文件，拼成标准选课表文件
     *
     * @param classId 课程号
     * @param studentXmlPath 选课的学生信息，xml格式，仅含一条学生记录
     * @param outFile 拼接成的choice.xml文件
     * @throws ParserConfigurationException
     * @throws TransformerException
     * @throws IOException
     * @throws SAXException
     */
    public static void creatChoiceXmlFromCidAndStudentXML(String classId, String studentXmlPath,String outFile) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        String sid;
        String cid = classId;
        String score = "null";

        // 初始化
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // 从标准化的studentXml文件中获取sid节点的值
        File xmlFile = new File(studentXmlPath);
        Document doc0 = docBuilder.parse(xmlFile);
        NodeList nodeList = doc0.getElementsByTagName("id");
        Element idElement = (Element) nodeList.item(0);
        sid = idElement.getTextContent();

        // 构建标准化的choice.xml文件
        Document doc = docBuilder.newDocument();
        // 创建根元素
        Element rootElement = doc.createElement("choices");
        doc.appendChild(rootElement);
        // 创建子元素
        Element choice = doc.createElement("choice");
        rootElement.appendChild(choice);
        // 添加子元素
        Element sidElement = doc.createElement("sid");
        sidElement.appendChild(doc.createTextNode(sid));
        choice.appendChild(sidElement);
        Element cidElement = doc.createElement("cid");
        cidElement.appendChild(doc.createTextNode(cid));
        choice.appendChild(cidElement);
        Element scoreElement = doc.createElement("score");
        scoreElement.appendChild(doc.createTextNode(score));
        choice.appendChild(scoreElement);

        // 将XML文件写入到指定文件
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(outFile));
        transformer.transform(source, result);;
    }


    public static boolean validateXmlWithXsd(String xmlFilePath, String xsdFilePath) {
        try {
            // 创建SchemaFactory对象
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // 创建Schema对象
            Schema schema = factory.newSchema(new File(xsdFilePath));
            // 创建Validator对象
            javax.xml.validation.Validator validator = schema.newValidator();
            // 验证XML文件
            validator.validate(new StreamSource(new File(xmlFilePath)));
            return true;
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            return false;
        }
    }


}
