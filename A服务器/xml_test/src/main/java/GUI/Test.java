package GUI;

import GUI.dao.SSUserDaoImpl;
import GUI.entity.SSUser;
import GUI.login.Login;
import GUI.login.LoginStudent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame{
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        JFrame jf = new JFrame("登陆界面");
        //设置框架的宽度和高度
        jf.setSize(400,300);
        jf.setLocation(600, 300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //创建面板，类似于HTML的div标签
        JPanel panel = new JPanel();
        //添加面板
        jf.add(panel);
        //调用用户定义的方法并添加组件到面板
        placeComponents(panel);
        //设置界面可见
        jf.setVisible(true);
    }

    public static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        //创建JLabel
        JLabel userlabel = new JLabel("账号:");
        userlabel.setBounds(60,45,100,40);
        panel.add(userlabel);
        //创建文本域用于用户输入
        JTextField userText = new JTextField(20);
        userText.setBounds(115,50,200,30);
        panel.add(userText);
        //输入密码的文本域
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(60,115,100,40);
        panel.add(passwordLabel);
        //密码框--未用真正密码框实现
        JTextField passwordText = new JTextField(20);
        passwordText.setBounds(115,120,200,30);
        panel.add(passwordText);
        //创建登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.setBounds(160,190,80,25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user_name = userText.getText();
                String user_pwd = passwordText.getText();
                System.out.println(user_name);
                System.out.println(user_pwd);
                SSUserDaoImpl ud = new SSUserDaoImpl();
                SSUser user = ud.findOne(user_name, user_pwd);
                if (user!=null && user.getAuth().equals("1   ")) {
                    new Login();
                } else if (user!=null && user.getAuth().equals("2   ")) {
                    new LoginStudent(user.getName());
                } else {
                    //登录失败这里会弹出来一个报错的小对话框
                    JOptionPane.showMessageDialog(null, "请检查账号和密码", "登录失败", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
