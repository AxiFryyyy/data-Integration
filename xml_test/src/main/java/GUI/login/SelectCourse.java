package GUI.login;

import GUI.dao.SSCourseDaoImpl;
import GUI.entity.SSCourse;
import GUI.entity.SSStudent;
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

public class SelectCourse extends JPanel implements ActionListener {
    JButton Selectbt;
    JScrollPane scpDemo;
    JTableHeader jth;
    JTable tabDemo;
    public SelectCourse() {
        this.setSize(650, 350);
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
        SSCourseDaoImpl cd = new SSCourseDaoImpl();
        List<SSCourse> cl = cd.findAll();
        int count = cl.size();
        System.out.println(count);
        Object[][] info = new Object[count][6];
        String[] title = {"课程号", "课程名", "学分", "授课教师", "授课地点", "共享课程"};
        for(int i=0; i<count; i++) {
            info[i][0] = cl.get(i).getCno();
            info[i][1] = cl.get(i).getCname();
            info[i][2] = cl.get(i).getSch();
            info[i][3] = cl.get(i).getTch();
            info[i][4] = cl.get(i).getUtc();
            if (cl.get(i).getShare().equals("1")) {
                info[i][5] = "是";
            } else {
                info[i][5] = "否";
            }
        }

        File file = new File("src/main/resources/db_A/classA.xml");
        if (file.isFile()) {
            file.delete();
        }
        Document document = DocumentHelper.createDocument();
        Element courses = document.addElement("classes");
        for (SSCourse c : cl) {
            Element course = courses.addElement("class");
            course.addElement("课程编号").addText(c.getCno());
            course.addElement("课程名称").addText(c.getCname());
            course.addElement("学分").addText(c.getSch());
            course.addElement("授课老师").addText(c.getTch());
            course.addElement("授课地点").addText(c.getUtc());
            course.addElement("共享").addText(c.getShare());
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
