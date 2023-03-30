package GUI.login;

import GUI.dao.SSSelectDaoImpl;
import GUI.entity.SSCourse;
import GUI.entity.SSSelect;
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

public class SelCourse extends JPanel implements ActionListener {
    JLabel Inputlabel;
    JTextField Inputtext;
    JButton Findbt;
    JScrollPane scpDemo;
    JTableHeader jth;
    JTable tabDemo;
    public SelCourse() {
        this.setSize(650, 350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        Inputlabel=new JLabel("请输入课程号");
        Inputlabel.setSize(100,30);
        Inputlabel.setLocation(100, 20);
        this.add(Inputlabel);

        Inputtext=new JTextField();
        Inputtext.setSize(160,30);
        Inputtext.setLocation(200, 20);
        this.add(Inputtext);

        Findbt=new JButton("查询");
        Findbt.setSize(90,28);
        Findbt.setLocation(420, 20);
        this.add(Findbt);
        Findbt.addActionListener(this);

        this.scpDemo = new JScrollPane();
        scpDemo.setBounds(10,60,630, 260);
        this.add(scpDemo);

        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cno = Inputtext.getText();
        System.out.println(cno);

        SSSelectDaoImpl sd = new SSSelectDaoImpl();
        List<SSSelect> sl = sd.findCourse(cno);
        int count = sl.size();
        System.out.println(count);
        if (count != 0) {
            Object[][] info = new Object[count][3];
            String[] title = {"学号", "姓名", "分数"};
            for (int i=0; i<count; i++) {
                info[i][0] = sl.get(i).getSno();
                info[i][1] = sl.get(i).getSname();
                info[i][2] = sl.get(i).getGrade();
            }

            File file = new File("src/main/resources/db_A/select_A.xml");
            if (file.isFile()) {
                file.delete();
            }
            Document document = DocumentHelper.createDocument();
            Element selects = document.addElement("selects");
            for (SSSelect s : sl) {
                Element select = selects.addElement("select").addAttribute("cno", (s.getCno() + ""));
                select.addElement("sno").addText(s.getSno());
                select.addElement("grade").addText(s.getGrade());
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
        } else {
            JOptionPane.showMessageDialog(null, "您查询的课程不存在，请重新输入");
        }
    }
}
