package GUI.login;

import GUI.dao.SSCourseDaoImpl;
import GUI.entity.SSCourse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindCourse extends JPanel implements ActionListener {
    JLabel Inputlabel;
    JTextField Inputtext;
    JButton Findbt;
    JLabel Cnolabel;
    JLabel Cnamelabel;
    JLabel Schlabel;
    JLabel Tchlabel;
    JLabel Utclabel;
    JLabel Sharelabel;
    JTextField Cnotext;
    JTextField Cnametext;
    JTextField Schtext;
    JTextField Tchtext;
    JTextField Utctext;
    JTextField Sharetext;

    public FindCourse() {
        this.setSize(650,350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        Cnolabel =new JLabel("课程号");
        Cnolabel.setSize(100,30);
        Cnolabel.setLocation(100, 90);
        this.add(Cnolabel);

        Cnotext =new JTextField();
        Cnotext.setSize(120,30);
        Cnotext.setLocation(220, 90);
        this.add(Cnotext);

        Cnamelabel =new JLabel("课程名");
        Cnamelabel.setSize(100,30);
        Cnamelabel.setLocation(100, 130);
        this.add(Cnamelabel);

        Cnametext =new JTextField();
        Cnametext.setSize(120,30);
        Cnametext.setLocation(220, 130);
        this.add(Cnametext);

        Schlabel =new JLabel("学分");
        Schlabel.setSize(100,30);
        Schlabel.setLocation(100, 170);
        this.add(Schlabel);

        Schtext =new JTextField();
        Schtext.setSize(120, 30);
        Schtext.setLocation(220, 170);
        this.add(Schtext);

        Tchlabel =new JLabel("授课教师");
        Tchlabel.setSize(100,30);
        Tchlabel.setLocation(100, 210);
        this.add(Tchlabel);

        Tchtext =new JTextField();
        Tchtext.setSize(120, 30);
        Tchtext.setLocation(220, 210);
        this.add(Tchtext);

        Utclabel = new JLabel("授课地点");
        Utclabel.setSize(100, 30);
        Utclabel.setLocation(100, 250);
        this.add(Utclabel);

        Utctext = new JTextField();
        Utctext.setSize(120, 30);
        Utctext.setLocation(220, 250);
        this.add(Utctext);

        Sharelabel = new JLabel("共享课程");
        Sharelabel.setSize(100, 30);
        Sharelabel.setLocation(100, 290);
        this.add(Sharelabel);

        Sharetext = new JTextField();
        Sharetext.setSize(120, 30);
        Sharetext.setLocation(220, 290);
        this.add(Sharetext);

        Inputlabel=new JLabel("请输入课程号");
        Inputlabel.setSize(100,30);
        Inputlabel.setLocation(100, 45);
        this.add(Inputlabel);

        Inputtext=new JTextField();
        Inputtext.setSize(160,30);
        Inputtext.setLocation(200, 45);
        this.add(Inputtext);

        Findbt=new JButton("查询");
        Findbt.setSize(90,28);
        Findbt.setLocation(420, 45);
        this.add(Findbt);
        Findbt.addActionListener(this);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cno = Inputtext.getText();
        System.out.println(cno);

        SSCourseDaoImpl cd = new SSCourseDaoImpl();
        SSCourse course = cd.findOne(cno);

        if (course != null) {
            Cnotext.setText(course.getCno());
            Cnametext.setText(course.getCname());
            Schtext.setText(course.getSch());
            Tchtext.setText(course.getTch());
            Utctext.setText(course.getUtc());
            if (course.getShare().equals("1")) {
                Sharetext.setText("是");
            } else {
                Sharetext.setText("否");
            }

        } else {
            JOptionPane.showMessageDialog(null, "您查询的课程不存在，请重新输入");
        }
    }
}
