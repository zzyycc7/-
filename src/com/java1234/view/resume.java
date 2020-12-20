package com.java1234.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;

public class resume extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resume frame = new resume();
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
	public resume() {
		setClosable(true);
		setBounds(100, 100, 482, 338);
		
		JLabel lbljava = new JLabel("一天速成的JAVA，求求多给点分吧！");
		lbljava.setFont(new Font("宋体", Font.PLAIN, 25));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lbljava)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(123)
					.addComponent(lbljava)
					.addContainerGap(149, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

}
