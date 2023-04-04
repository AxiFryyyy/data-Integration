package GUI.login;

import GUI.dao.SSStudentDaoImpl;
import GUI.entity.SSStudent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindStudent extends JPanel implements ActionListener {
    JLabel Inputlabel;
    JTextField Inputtext;
    JButton Findbt;
    JLabel Snolabel;
    JLabel Sexlabel;
    JLabel Namelabel;
    JLabel Instlabel;
    JTextField Snotext;
    JTextField Nametext;
    JTextField Insttext;
    JTextField Sextext;

    public FindStudent() {
        this.setSize(650,350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        Snolabel =new JLabel("学号");
        Snolabel.setSize(100,30);
        Snolabel.setLocation(100, 120);
        this.add(Snolabel);

        Snotext =new JTextField();
        Snotext.setSize(120,30);
        Snotext.setLocation(220, 120);
        this.add(Snotext);

        Namelabel =new JLabel("姓名");
        Namelabel.setSize(100,30);
        Namelabel.setLocation(100, 160);
        this.add(Namelabel);

        Nametext =new JTextField();
        Nametext.setSize(120,30);
        Nametext.setLocation(220, 160);
        this.add(Nametext);

        Sexlabel =new JLabel("性别");
        Sexlabel.setSize(100,30);
        Sexlabel.setLocation(100, 200);
        this.add(Sexlabel);

        Sextext =new JTextField();
        Sextext.setSize(120, 30);
        Sextext.setLocation(220, 200);
        this.add(Sextext);

        Instlabel =new JLabel("院系");
        Instlabel.setSize(100,30);
        Instlabel.setLocation(100, 240);
        this.add(Instlabel);

        Insttext =new JTextField();
        Insttext.setSize(120, 30);
        Insttext.setLocation(220, 240);
        this.add(Insttext);


        Inputlabel=new JLabel("请输入学号");
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
        String sno = Inputtext.getText();
        System.out.println(sno);

        SSStudentDaoImpl sd = new SSStudentDaoImpl();
        SSStudent student = sd.findOne(sno);

        if (student != null) {
            Snotext.setText(student.getSno());
            Nametext.setText(student.getSname());
            Sextext.setText(student.getSex());
            Insttext.setText(student.getInst());
        } else {
            JOptionPane.showMessageDialog(null, "您查询的学生不存在，请重新输入");
        }
    }
}
