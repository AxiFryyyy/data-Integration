package GUI.login;

import GUI.dao.SSSelectDaoImpl;
import GUI.entity.SSSelect;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelStudent extends JPanel implements ActionListener {
    JLabel Inputlabel;
    JTextField Inputtext;
    JButton Findbt;
    JScrollPane scpDemo;
    JTableHeader jth;
    JTable tabDemo;
    public SelStudent() {
        this.setSize(650, 350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        Inputlabel=new JLabel("请输入学号");
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
        String sno = Inputtext.getText();
        System.out.println(sno);

        SSSelectDaoImpl sd = new SSSelectDaoImpl();
        List<SSSelect> sl = sd.findStudent(sno);
        int count = sl.size();
        System.out.println(count);
        if (count != 0) {
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
        } else {
            JOptionPane.showMessageDialog(null, "您查询的学生不存在，请重新输入");
        }
    }
}
