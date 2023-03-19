package GUI.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login {
    public Login() {
        JFrame f = new JFrame("数学系课程管理系统");
        f.setLayout(new FlowLayout());
        f.setBounds(300,400,500,200);
        //创建Lable标签对象
        JButton j1 = new JButton("请输入0-4的数字");
        //创建输入框
        TextField tf = new TextField(40);
        f.add(tf);
        f.add(j1);
        tf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e1) {
                j1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e2) {
                        char ch = e1.getKeyChar();
                        switch (ch){
                            case '0':
                                j1.setText("zero");
                                break;
                            case '1':
                                j1.setText("one");
                                break;
                            case '2':
                                j1.setText("two");
                                break;
                            case '3':
                                j1.setText("three");
                                break;
                            case '4':
                                j1.setText("four");
                                break;
                        }
                    }
                });
            }
        });
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}

