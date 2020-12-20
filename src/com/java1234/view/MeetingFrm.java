package com.java1234.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.java1234.dao.MeetingDao;
import com.java1234.dao.RoomDao;
import com.java1234.dao.UserDao;
import com.java1234.model.Meeting;
import com.java1234.model.Room;
import com.java1234.model.User;
import com.java1234.util.DbUtil;
import com.java1234.util.StringUtil;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;

public class MeetingFrm extends JInternalFrame {
	private JTextField dateTxt;
	private JTable meetingtable;
	private JComboBox startbox;
	private JComboBox endbox;
	private JTextArea reasonTxt;
	private JScrollPane scroll2;
	
	private DbUtil dbutil=new DbUtil();
	private MeetingDao meetingdao=new MeetingDao();
	private RoomDao roomdao=new RoomDao();
	private UserDao userdao=new UserDao();
	private int useridget;
	private String meetingid;
	private JTextField RoomnameTxt;
	private JTextField memberTxt;
	private JTable meetings;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeetingFrm frame = new MeetingFrm();
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
	public MeetingFrm(User user) {
		this();
		Connection con=null;
		try {
			con=dbutil.getCon();
			useridget=userdao.findid(con, user);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbutil.closeCon(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		this.fixmeeting();
	}
	public MeetingFrm() {
		setBackground(new Color(230, 230, 250));
		setTitle("会议助手");
		setBounds(0, 0, 1910, 940);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 248, 255));
		panel.setBorder(new TitledBorder(null, "\u5FEB\u901F\u67E5\u8BE2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBorder(new TitledBorder(null, "\u6211\u7684\u4F1A\u8BAE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(315)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 619, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(228, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(170)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, 0, 0, Short.MAX_VALUE))
					.addContainerGap(246, Short.MAX_VALUE))
		);
		
		JScrollPane scroll2 = new JScrollPane();
		
		JButton btnNewButton_1 = new JButton("刷新");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh(e);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JButton btnNewButton_2 = new JButton("提前结束");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endmeeting(e);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 17));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addGap(84)
							.addComponent(btnNewButton_2)
							.addGap(169))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(scroll2, GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scroll2, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		meetings = new JTable();
		meetings.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row=meetings.getSelectedRow();
				meetingid=(String) meetings.getValueAt(row, 5);
			}
		});
		meetings.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u65E5\u671F", "\u5F00\u59CB\u65F6\u95F4", "\u7ED3\u675F\u65F6\u95F4", "\u5730\u70B9", "\u4F1A\u8BAE\u5185\u5BB9", "\u4F1A\u8BAE\u53F7"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		meetings.setFont(new Font("宋体", Font.PLAIN, 17));
		meetings.getTableHeader().setFont(new Font("Dialog", 0, 19)); 
		meetings.setRowHeight(40);
		scroll2.setViewportView(meetings);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("结束时间：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 17));
		
		endbox = new JComboBox();
		endbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm=(DefaultTableModel)meetingtable.getModel();
				dtm.setRowCount(0);
			}
		});
		endbox.setFont(new Font("宋体", Font.PLAIN, 17));
		endbox.setEditable(true);
		
		startbox = new JComboBox();
		startbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm=(DefaultTableModel)meetingtable.getModel();
				dtm.setRowCount(0);
				RoomnameTxt.setText("");
			}
		});
		startbox.setFont(new Font("宋体", Font.PLAIN, 17));
		startbox.setEditable(true);
		
		JLabel lblNewLabel_1 = new JLabel("开始时间：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel = new JLabel("会议日期：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 17));
		
		dateTxt = new JTextField();
		dateTxt.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				clearbar();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				clearbar();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				clearbar();
			}
		});
		dateTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		dateTxt.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnNewButton = new JButton("预约");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookaroom(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findroom(e);
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_3 = new JLabel("会议室名：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 17));
		
		RoomnameTxt = new JTextField();
		RoomnameTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		RoomnameTxt.setEditable(false);
		RoomnameTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("使用原因：");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 17));
		
		reasonTxt = new JTextArea();
		reasonTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		
		JLabel lblNewLabel_5 = new JLabel("参会人数：");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 17));
		
		memberTxt = new JTextField();
		memberTxt.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				clearbar();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				clearbar();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				clearbar();
			}
		});
		memberTxt.setFont(new Font("宋体", Font.PLAIN, 17));
		memberTxt.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(button)
							.addGap(36)
							.addComponent(btnNewButton))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(RoomnameTxt, 150, 150, Short.MAX_VALUE)
								.addComponent(startbox, 0, 150, Short.MAX_VALUE)
								.addComponent(endbox, 0, 150, Short.MAX_VALUE)
								.addComponent(dateTxt)))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_5)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(memberTxt))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblNewLabel_4)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(reasonTxt, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))))
					.addGap(97)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(dateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(startbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(endbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(RoomnameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
									.addComponent(reasonTxt, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
									.addGap(18))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(30)
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_5)
										.addComponent(memberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(36)
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(button))
							.addGap(23))))
		);
		
		meetingtable = new JTable();
		meetingtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mousepressTable(e);
			}
		});
		meetingtable.setFont(new Font("宋体", Font.PLAIN, 17));
		meetingtable.getTableHeader().setFont(new Font("Dialog", 0, 19)); 
		meetingtable.setRowHeight(40);
		scrollPane.setViewportView(meetingtable);
		meetingtable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7A7A\u95F2\u4F1A\u8BAE\u5BA4", "\u5BB9\u7EB3\u4EBA\u6570"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		
		this.fillbox();
	}
	
	protected void clearbar() {
		// TODO Auto-generated method stub
		this.RoomnameTxt.setText("");
		DefaultTableModel dtm=(DefaultTableModel)meetingtable.getModel();
		dtm.setRowCount(0);
	}

	private void endmeeting(ActionEvent evt) {
		// TODO Auto-generated method stub
		int result=JOptionPane.showConfirmDialog(null, "是否要取消会议？");
		if(result==0) {
			Connection con =null;
			try {
				con=dbutil.getCon();
				meetingdao.delete(con, meetingid);
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
		fixmeeting();
	}

	private void refresh(ActionEvent evt) {
		// TODO Auto-generated method stub
		fixmeeting();
	}
	
	private void outoftime() {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2= new SimpleDateFormat("HH");
	    Date showdate = new Date(System.currentTimeMillis());
		String nowdate=sdf.format(showdate);
		String hours=sdf2.format(showdate);
		Connection con =null;
		try {
			con=dbutil.getCon();
			meetingdao.delete(con, nowdate, hours);
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
	
	private void fixmeeting() {
		outoftime();
		DefaultTableModel dtm=(DefaultTableModel)meetings.getModel();
		dtm.setRowCount(0);
		Connection con =null;
		try {
			con=dbutil.getCon();
			ResultSet rs1=meetingdao.list(con, useridget);
			while(rs1.next()) {
				Vector v=new Vector();
				v.add(rs1.getString("date"));
				v.add(rs1.getInt("start")+":00");
				v.add(rs1.getInt("end")+":00");
				v.add(roomdao.findroom(con,rs1.getInt("roomid")));
				v.add(rs1.getString("reason"));
				v.add(rs1.getString("id"));
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
	private void mousepressTable(MouseEvent evt) {
		// TODO Auto-generated method stub
		int row=meetingtable.getSelectedRow();
		RoomnameTxt.setText((String)meetingtable.getValueAt(row, 0));
	}

	private void bookaroom(ActionEvent evt) {
		// TODO Auto-generated method stub
		String ddate=this.dateTxt.getText();
		String reason=this.reasonTxt.getText();
		String rroom=this.RoomnameTxt.getText();
		if(StringUtil.isEmpty(ddate)) {
			JOptionPane.showMessageDialog(null, "日期不能为空");
			return;
		}
		if(StringUtil.isEmpty(reason)) {
			JOptionPane.showMessageDialog(null, "原因不能为空");
			return;
		}
		if(ddate.length()<10) {
			JOptionPane.showMessageDialog(null, "日期格式错误");
			return;
		}
		if(StringUtil.isEmpty(rroom)) {
			JOptionPane.showMessageDialog(null, "请选择一个会议室");
			return;
		}
		String sst=(String)startbox.getSelectedItem();
		String eet=(String)endbox.getSelectedItem();
		int starttime=Integer.valueOf(sst.substring(0,sst.indexOf(":")));
		int endtime=Integer.valueOf(eet.substring(0,eet.indexOf(":")));
		if(starttime>endtime) {
			JOptionPane.showMessageDialog(null, "时间设置错误");
			return;
		}
		Connection con=null;
		try {
			con=dbutil.getCon();
			Room room=new Room();
			room.setName(rroom);
			ResultSet rs_room=roomdao.findid(con, room);
			Meeting meeting=new Meeting();
			if(rs_room.next()) {
				meeting.setRoomid(rs_room.getInt("id"));
			}
			meeting.setDate(ddate);
			meeting.setStart(starttime);
			meeting.setEnd(endtime);
			meeting.setUserid(this.useridget);
			meeting.setReason(reason);
			int n=meetingdao.add(con,meeting);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				this.findroom(evt);
				this.fixmeeting();
			}else {
				JOptionPane.showMessageDialog(null, "添加失败！");
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

	private void findroom(ActionEvent evt) {
		// TODO Auto-generated method stub
		String ddate=this.dateTxt.getText();
		if(StringUtil.isEmpty(ddate)) {
			JOptionPane.showMessageDialog(null, "日期不能为空");
			DefaultTableModel dtm=(DefaultTableModel)meetingtable.getModel();
			dtm.setRowCount(0);
			return;
		}
		if(ddate.length()<10) {
			JOptionPane.showMessageDialog(null, "日期格式错误");
			DefaultTableModel dtm=(DefaultTableModel)meetingtable.getModel();
			dtm.setRowCount(0);
			return;
		}
		String sst=(String)startbox.getSelectedItem();
		String eet=(String)endbox.getSelectedItem();
		int starttime=Integer.valueOf(sst.substring(0,sst.indexOf(":")));
		int endtime=Integer.valueOf(eet.substring(0,eet.indexOf(":")));
	    SimpleDateFormat sdf= new SimpleDateFormat("HH");
	    Date showdate = new Date(System.currentTimeMillis());
		int nowhour=Integer.valueOf(sdf.format(showdate));
	    SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
	    Date showdate2 = new Date(System.currentTimeMillis());
		String nowdate=sdf2.format(showdate2);
		if(starttime>=endtime||(starttime<=nowhour&& nowdate.compareTo(ddate)==0)||nowdate.compareTo(ddate)>0) {
			JOptionPane.showMessageDialog(null, "时间设置错误");
			DefaultTableModel dtm=(DefaultTableModel)meetingtable.getModel();
			dtm.setRowCount(0);
			return;
		}
		Meeting meeting=new Meeting();
		meeting.setDate(ddate);
		meeting.setStart(starttime);
		meeting.setEnd(endtime);
		this.filltable(meeting);
	}

	private void fillbox() {
	    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
	    Date showdate = new Date(System.currentTimeMillis());
		dateTxt.setText(sdf.format(showdate));
		String[] times= {"7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00"};
		for(int i=0;i<15;i++) {
			startbox.addItem(times[i]);
			endbox.addItem(times[i]);
		}
	}
	private void filltable(Meeting meeting){
		DefaultTableModel dtm=(DefaultTableModel)meetingtable.getModel();
		dtm.setRowCount(0);
		Connection con =null;
		try {
			con=dbutil.getCon();
			ResultSet rs=meetingdao.search(con, meeting);
			String maxmember=this.memberTxt.getText();
			if(StringUtil.isEmpty(maxmember)) {
				JOptionPane.showMessageDialog(null, "参会人数不能为空");
				return;
			}
			if(Integer.valueOf(maxmember)<=0) {
				JOptionPane.showMessageDialog(null, "参会人数异常");
				return;
			}
			ResultSet rs2=roomdao.findid(con, rs,Integer.valueOf(maxmember));
			while(rs2.next()) {
				Vector v=new Vector();
				v.add(rs2.getString("name"));
				v.add(rs2.getString("size"));
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
