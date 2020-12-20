package com.java1234.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.java1234.model.User;

public class MainFrm extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table=null;
	private String nowusername;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm(User user) {
		this();
		nowusername=user.getUserName();
		setTitle("欢迎，"+nowusername);
		MeetingFrm meetingfrm=new MeetingFrm(user);
		table.setLayer(meetingfrm, -1);
		meetingfrm.setVisible(true);
		table.add(meetingfrm);
	}
	public MainFrm() {
		setTitle("欢迎");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 474);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("选项");
		mnNewMenu.setFont(new Font("宋体", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("会议室");
		mnNewMenu_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 17));
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改/删除");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update_and_delete und=new update_and_delete();
				und.setVisible(true);
				table.add(und);
			}
		});
		mntmNewMenuItem.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 17));
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JMenuItem menuItem = new JMenuItem("添加");
		menuItem.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 17));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addroom addroom=new Addroom();
				addroom.setVisible(true);
				table.add(addroom);
			}
		});
		menuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_2.add(menuItem);
		
		JMenuItem menuItem_2 = new JMenuItem("安全退出");
		menuItem_2.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 17));
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=JOptionPane.showConfirmDialog(null, "是否退出？");
				if(result==0) {
					dispose();
				}
			}
		});
		mnNewMenu.add(menuItem_2);
		
		JMenu mnNewMenu_1 = new JMenu("关于我们");
		mnNewMenu_1.setFont(new Font("宋体", Font.PLAIN, 20));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("一天速成JAVA");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resume dgdf=new resume();
				dgdf.setVisible(true);
				table.add(dgdf);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 17));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JDesktopPane();
		table.setBackground(Color.WHITE);
		contentPane.add(table);
		
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
}
