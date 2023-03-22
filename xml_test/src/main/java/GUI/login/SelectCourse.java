package GUI.login;

import GUI.dao.SSCourseDaoImpl;
import GUI.entity.SSCourse;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        tabDemo = new JTable(info, title);
        jth = tabDemo.getTableHeader();
        scpDemo.getViewport().add(tabDemo);
    }
}
