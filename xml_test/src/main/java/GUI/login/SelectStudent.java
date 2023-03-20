package GUI.login;

import GUI.dao.SSStudentDaoImpl;
import GUI.entity.SSStudent;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        tabDemo = new JTable(info, title);
        jth = tabDemo.getTableHeader();
        scpDemo.getViewport().add(tabDemo);
    }
}
