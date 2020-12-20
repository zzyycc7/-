package com.java1234.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class MyMeeting extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyMeeting frame = new MyMeeting();
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
	public MyMeeting() {
		setBounds(100, 100, 450, 300);

	}

}
