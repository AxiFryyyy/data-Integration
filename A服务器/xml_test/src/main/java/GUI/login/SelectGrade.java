package GUI.login;

import GUI.dao.SSSelectDaoImpl;
import GUI.entity.SSSelect;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectGrade extends JPanel implements ActionListener{
    JButton Findbt;
    JScrollPane scpDemo;
    JTableHeader jth;
    JTable tabDemo;
    String sno;

    public SelectGrade(String sno) {
        this.sno = sno;

        this.setSize(650, 350);
        this.setLocation(100, 20);
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        Findbt=new JButton("查询");
        Findbt.setSize(80,30);
        Findbt.setLocation(10, 15);
        this.add(Findbt);
        Findbt.addActionListener(this);

        this.scpDemo = new JScrollPane();
        scpDemo.setBounds(10,60,630, 260);
        this.add(scpDemo);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.sno);

        SSSelectDaoImpl sd = new SSSelectDaoImpl();
        List<SSSelect> sl = sd.findStudent(this.sno);
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
        }
    }
}
