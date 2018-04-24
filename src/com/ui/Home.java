package com.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.JMenu;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1024, 768);
		frame.setTitle("环华玻璃");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //最大化
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("系统");
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem_1 = new JMenuItem("客户资料");
		mnNewMenu.add(menuItem_1);
		menuItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				Custom_Info custom=new Custom_Info();
				desktopPane.add(custom);
				custom.setMaximumSize(desktopPane.getMaximumSize());
				try {
					custom.setMaximum(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JMenuItem menuItem_2 = new JMenuItem("商品资料");
		mnNewMenu.add(menuItem_2);
		
		JMenu menu = new JMenu("业务");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("销售");
		menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				SaleMain home=new SaleMain();
				desktopPane.add(home);
			}
		});
		
		
	}

}
