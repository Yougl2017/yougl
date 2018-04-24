package com.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.dao.conn.Dao;

public class HomeMain {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tablemodel;
	private JPanel Buttonpanel;
	private JButton NewButton;
	private JButton SelectButton;

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
					HomeMain window = new HomeMain();
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
	public HomeMain() {
		initialize();
		NewAction();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("环华系统");
		frame.setBounds(0, 0, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //最大化
		
		Buttonpanel = new JPanel();
		frame.getContentPane().add(Buttonpanel, BorderLayout.NORTH);
		Buttonpanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
        NewButton = new JButton("新建订单");
		NewButton.setSize(400, 400);
		NewButton.setHorizontalAlignment(SwingConstants.LEADING);
		Buttonpanel.add(NewButton);
		
		SelectButton = new JButton("查询订单");
		Buttonpanel.add(SelectButton);
		
		JScrollPane ContentscrollPane = new JScrollPane();
		frame.getContentPane().add(ContentscrollPane, BorderLayout.CENTER);
		
		//表格式处理
	
		tablemodel= new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
						"订单号","公司名","联系人","联系电话","总金额","面积","备注"
				}
			);
		table = new JTable(tablemodel);
		ContentscrollPane.setColumnHeaderView(table);
		ContentscrollPane.setViewportView(table);
		
		List list = Dao.getPInfos();
		Iterator iterator=list.iterator();
		updateTable(iterator, tablemodel);
		
	}
	//新建订单
    public void NewAction(){
    	
    	NewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Sale_JDialog sale_jdialog = new Sale_JDialog(frame,true);
				sale_jdialog.setModalityType(ModalityType.APPLICATION_MODAL);
			}
		});
    	
    	
    }
    //查询信息
    private void updateTable(Iterator iterator, final DefaultTableModel dftm) {
		int num = dftm.getRowCount();
		for (int i = 0; i < num; i++){
			dftm.removeRow(0);
		}
		while (iterator.hasNext()) {
			
			Vector vector=new Vector();
			List view=(List) iterator.next();
			vector.addAll(view);
			dftm.addRow(vector);
		}
	}

}
