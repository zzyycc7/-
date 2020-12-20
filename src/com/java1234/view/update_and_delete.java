package com.java1234.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.java1234.dao.RoomDao;
import com.java1234.model.Room;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;

public class update_and_delete extends JInternalFrame {
	private JTextField s_roomname;
	private JTable roomtable;

	private DbUtil dbutil=new DbUtil();
	private RoomDao roomdao=new RoomDao();
	private JTextField idTxt;
	private JTextField sizeTxt;
	private JTextField nameTxt;
	private JTextField locTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					update_and_delete frame = new update_and_delete();
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
	public update_and_delete() {
		setTitle("修改/删除");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 722, 668);
		
		JLabel label = new JLabel("会议室名：");
		label.setFont(new Font("宋体", Font.PLAIN, 17));
		
		s_roomname = new JTextField();
		s_roomname.setFont(new Font("宋体", Font.PLAIN, 17));
		s_roomname.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomsearch(e);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u4FEE\u6539/\u5220\u9664", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(122)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(s_roomname, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addComponent(button))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(72)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 541, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(82, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(label)
						.addComponent(s_roomname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("编号：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1 = new JLabel("大小：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_2 = new JLabel("会议室名：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_3 = new JLabel("地    址：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		idTxt = new JTextField();
		idTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		idTxt.setEditable(false);
		idTxt.setColumns(10);
		
		sizeTxt = new JTextField();
		sizeTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		sizeTxt.setColumns(10);
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		nameTxt.setColumns(10);
		
		locTxt = new JTextField();
		locTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		locTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomUpdate(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomDelete(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sizeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnNewButton)
							.addGap(82)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(nameTxt, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(locTxt, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
							.addGap(35))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_2)
						.addComponent(idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(sizeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_3)
						.addComponent(locTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		roomtable = new JTable();
		roomtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				roommousePressed(e);
			}
		});
		roomtable.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		roomtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u4F1A\u8BAE\u5BA4\u540D", "\u5927\u5C0F", "\u5730\u5740"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(roomtable);
		getContentPane().setLayout(groupLayout);

		roomtable.setRowHeight(40);
		roomtable.getTableHeader().setFont(new Font("Dialog", 0, 19)); 
		this.filltable(new Room());
	}
	
	private void roomDelete(ActionEvent evt) {
		// TODO Auto-generated method stub
		String id=idTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择需要删除的记录");
			return;
		}
		Connection con=null;
		try {
		con=dbutil.getCon();
		int countnum=roomdao.delete(con, id);
		if(countnum==1) {
			JOptionPane.showMessageDialog(null, "删除成功");
			this.resetvalue();
			this.filltable(new Room());
		}else {
			JOptionPane.showMessageDialog(null, "删除失败");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void roomUpdate(ActionEvent evt) {
		// TODO Auto-generated method stub
		String id=idTxt.getText();
		String name=nameTxt.getText();
		String size=sizeTxt.getText();
		String loc=locTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "请选择需要修改的记录");
			return;
		}
		if(StringUtil.isEmpty(name)||StringUtil.isEmpty(size)||StringUtil.isEmpty(loc)) {
			JOptionPane.showMessageDialog(null, "不能填写空值");
			return;
		}
		Room room=new Room(Integer.parseInt(id),Integer.parseInt(size),name,loc);
		Connection con=null;
		try {
		con=dbutil.getCon();
		int countnum=roomdao.update(con, room);
		if(countnum==1) {
			JOptionPane.showMessageDialog(null, "修改成功");
			this.resetvalue();
			this.filltable(new Room());
		}else {
			JOptionPane.showMessageDialog(null, "修改失败");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbutil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void roommousePressed(MouseEvent evt) {
		// TODO Auto-generated method stub
		int row=roomtable.getSelectedRow();
		idTxt.setText((String)roomtable.getValueAt(row, 0));
		nameTxt.setText((String)roomtable.getValueAt(row, 1));
		sizeTxt.setText((String)roomtable.getValueAt(row, 2));
		locTxt.setText((String)roomtable.getValueAt(row, 3));
	}

	private void roomsearch(ActionEvent evt) {
		// TODO Auto-generated method stub
		String s_roomname=this.s_roomname.getText();
		Room room=new Room();
		room.setName(s_roomname);
		this.filltable(room);
	}
	private void resetvalue() {
		this.idTxt.setText("");
		this.nameTxt.setText("");
		this.sizeTxt.setText("");
		this.locTxt.setText("");
	}
	private void filltable(Room room){
		DefaultTableModel dtm=(DefaultTableModel)roomtable.getModel();
		dtm.setRowCount(0);
		Connection con =null;
		try {
			con=dbutil.getCon();
			ResultSet rs=roomdao.list(con, room);
			while(rs.next()) {
				Vector v=new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("name"));
				v.add(rs.getString("size"));
				v.add(rs.getString("location"));
				dtm.addRow(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbutil.closeCon(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
