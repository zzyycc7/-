package com.java1234.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class LogonFrm extends JFrame {

	private JPanel contentPane;
	private final JLabel label = new JLabel("");
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;

	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogonFrm frame = new LogonFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogonFrm() {
		label.setIcon(new ImageIcon(LogonFrm.class.getResource("/images/loginlogo.png")));
		setResizable(false);
		setTitle("会议室管理系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("宋体", Font.BOLD, 20));
		label_1.setIcon(null);
		
		JLabel label_2 = new JLabel("密  码：");
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("Consolas", Font.PLAIN, 17));
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Consolas", Font.PLAIN, 17));
		
		JButton btnNewButton = new JButton("登陆");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerfromed(e);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(187, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(216)
					.addComponent(btnNewButton)
					.addGap(49)
					.addComponent(btnNewButton_1)
					.addContainerGap(200, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(188, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
					.addGap(152))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		this.setLocationRelativeTo(null);
	}

	private void loginActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String userName=this.userNameTxt.getText();
		String password=new String(this.passwordTxt.getPassword());
		if(StringUtil.isEmpty(userName)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		User user=new User(userName,password);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con, user);
			if(currentUser!=null) {
				dispose();
				if(currentUser.getUserName().equals("admin")) {
					new MainFrm(currentUser).setVisible(true);
				}else {
					new UserFrm(currentUser).setVisible(true);
				}
			}else {
				JOptionPane.showMessageDialog(null, "用户名或密码错误！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void resetValueActionPerfromed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
	}
}
