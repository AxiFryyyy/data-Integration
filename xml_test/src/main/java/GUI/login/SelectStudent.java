package GUI.login;

import GUI.dao.SSStudentDaoImpl;
import GUI.entity.SSStudent;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class SelectStudent extends JPanel implements ActionListener {
    JButton Selectbt;
    JScrollPane scpDemo;
    JTableHeader jth;
    JTable tabDemo;

    public SelectStudent() {
        this.setSize(650,350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);
        this.scpDemo = new JScrollPane();
        scpDemo.setBounds(10,60,630, 260);
        this.add(scpDemo);

        Selectbt = new JButton("查询");
        Selectbt.setBounds(10, 15, 80, 30);
        this.add(Selectbt);
        Selectbt.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SSStudentDaoImpl sd = new SSStudentDaoImpl();
        List<SSStudent> sl = sd.findAll();
        int count = sl.size();
        System.out.println(count);
        Object[][] info = new Object[count][4];
        String[] title = {"学号", "姓名", "性别", "院系"};
        for(int i=0; i<count; i++) {
            info[i][0] = sl.get(i).getSno();
            info[i][1] = sl.get(i).getSname();
            info[i][2] = sl.get(i).getSex();
            info[i][3] = sl.get(i).getInst();
        }

        File file = new File("src/main/resources/db_A/studentA.xml");
        if (file.isFile()) {
            file.delete();
        }
        Document document = DocumentHelper.createDocument();
        Element students = document.addElement("students");
        for (SSStudent s : sl) {
            Element student = students.addElement("student");
            student.addElement("学号").addText(s.getSno());
            student.addElement("姓名").addText(s.getSname());
            student.addElement("性别").addText(s.getSex());
            student.addElement("院系").addText(s.getInst());
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        Writer writer = null;
        XMLWriter xmlWriter = null;
        try {
            writer = new FileWriter(file);
            xmlWriter = new XMLWriter(writer, format);
            xmlWriter.write(document);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (xmlWriter != null) {
                try {
                    xmlWriter.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }

        tabDemo = new JTable(info, title);
        jth = tabDemo.getTableHeader();
        scpDemo.getViewport().add(tabDemo);
    }
}
