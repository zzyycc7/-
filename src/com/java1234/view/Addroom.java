package com.java1234.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.java1234.dao.RoomDao;
import com.java1234.model.Room;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class Addroom extends JInternalFrame {
	private JTextField nameTxt;
	private JTextField sizeTxt;
	private JTextField locTxt;

	private DbUtil dbutil=new DbUtil();
	private RoomDao roomdao=new RoomDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addroom frame = new Addroom();
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
	public Addroom() {
		setIconifiable(true);
		setClosable(true);
		setTitle("添加会议室");
		setBounds(100, 100, 542, 370);
		
		JLabel lblNewLabel = new JLabel("会议室名：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("大    小：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("地    址：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		nameTxt.setColumns(10);
		
		sizeTxt = new JTextField();
		sizeTxt.setFont(new Font("Consolas", Font.PLAIN, 17));
		sizeTxt.setColumns(10);
		
		locTxt = new JTextField();
		locTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		locTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addValue(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValue(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(115)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(btnNewButton)
							.addGap(54)
							.addComponent(btnNewButton_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(locTxt, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
								.addComponent(sizeTxt)
								.addComponent(nameTxt))))
					.addContainerGap(133, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(77)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(sizeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(locTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(86, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	private void addValue(ActionEvent evt) {
		// TODO Auto-generated method stub
		String roomname=this.nameTxt.getText();
		String roomsize1=this.sizeTxt.getText();
		String roomloc=this.locTxt.getText();
		if(StringUtil.isEmpty(roomname)||StringUtil.isEmpty(roomloc)||StringUtil.isEmpty(roomsize1)) {
			JOptionPane.showMessageDialog(null, "不能为空！");
			return;
		}
		int roomsize=Integer.valueOf(roomsize1);
		Room room=new Room(roomsize, roomname, roomloc);
		Connection con=null;
		try {
			con=dbutil.getCon();
			int n=roomdao.add(con,room);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				this.nameTxt.setText("");
				this.sizeTxt.setText("");
				this.locTxt.setText("");
			}else {
				JOptionPane.showMessageDialog(null, "添加失败！");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败！");
		}finally{
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void resetValue(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.nameTxt.setText("");
		this.sizeTxt.setText("");
		this.locTxt.setText("");
	}

}
