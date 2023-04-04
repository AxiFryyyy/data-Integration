package GUI.login;

import GUI.dao.SSCourseDaoImpl;
import GUI.dao.SSSelectDaoImpl;
import GUI.dao.SSStudentDaoImpl;
import GUI.entity.SSCourse;
import GUI.entity.SSSelect;
import GUI.entity.SSStudent;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddCourse extends JPanel implements ActionListener{
    JLabel Inputlabel;
    JTextField Inputtext;
    JButton bt;
    JScrollPane scpDemo;
    JTableHeader jth;
    JTable tabDemo;
    String sno;

    public AddCourse(String sno) {
        this.sno = sno;

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

        bt=new JButton("选课");
        bt.setSize(80, 30);
        bt.setLocation(420, 20);
        this.add(bt);
        bt.addActionListener(this);

        this.scpDemo = new JScrollPane();
        scpDemo.setBounds(10,60,630, 260);
        this.add(scpDemo);

        this.setVisible(true);

        SSSelectDaoImpl sd = new SSSelectDaoImpl();
        List<SSSelect> sl = sd.findStudent(this.sno);
        int count = sl.size();
        System.out.println(count);
        Object[][] info = new Object[count][3];
        String[] title = {"课程号", "课程名", "分数"};
        for (int i=0; i<count; i++) {
            info[i][0] = sl.get(i).getCno();
            info[i][1] = sl.get(i).getCname();
            info[i][2] = sl.get(i).getGrade();
        }

        tabDemo = new JTable(info, title);
        jth = tabDemo.getTableHeader();
        scpDemo.getViewport().add(tabDemo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cno = Inputtext.getText();
        System.out.println(cno);

        SSStudentDaoImpl studentDao = new SSStudentDaoImpl();
        SSStudent student = studentDao.findOne(this.sno);
        if (student.getInst().equals("数学系")) {
            SSSelectDaoImpl selectDao = new SSSelectDaoImpl();
            selectDao.addCourse(cno, this.sno);
        }

        SSSelectDaoImpl sd = new SSSelectDaoImpl();
        List<SSSelect> sl = sd.findStudent(this.sno);
        int count = sl.size();
        System.out.println(count);
        Object[][] info = new Object[count][3];
        String[] title = {"课程号", "课程名", "分数"};
        for (int i=0; i<count; i++) {
            info[i][0] = sl.get(i).getCno();
            info[i][1] = sl.get(i).getCname();
            info[i][2] = sl.get(i).getGrade();
        }

        tabDemo = new JTable(info, title);
        jth = tabDemo.getTableHeader();
        scpDemo.getViewport().add(tabDemo);
    }
}
