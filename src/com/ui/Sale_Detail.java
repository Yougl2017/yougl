package com.ui;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;


import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import com.dao.conn.Dao;


public class Sale_Detail extends JDialog {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField_Start;
	private JTextField textField_End;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Sale_Detail frame = new Sale_Detail();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Sale_Detail() {
		java.sql.Date date=new java.sql.Date(System.currentTimeMillis());
		
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		setBounds(100, 100, 742, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel select_p = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lb_dh = new JLabel("单号：");
		
		JButton btn_selectall = new JButton("查询全部");
		
		JLabel label = new JLabel("从");
		
		textField_Start = new JTextField();
		//textField_Start.setText("2018-01-01");
		textField_Start.setColumns(10);
		
		JLabel label_1 = new JLabel("到");
		
		textField_End = new JTextField();
		textField_End.setColumns(10);
		
		
		
		textField_End.setText(date.toString());
		date.setMonth(0);
		date.setDate(1);
		textField_Start.setText(date.toString());
		
		JButton Button_Select = new JButton("查询");
		GroupLayout gl_select_p = new GroupLayout(select_p);
		gl_select_p.setHorizontalGroup(
			gl_select_p.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_select_p.createSequentialGroup()
					.addGroup(gl_select_p.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_select_p.createSequentialGroup()
							.addGap(24)
							.addComponent(lb_dh)
							.addGap(27)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_Start, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_End, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
							.addComponent(Button_Select))
						.addGroup(gl_select_p.createSequentialGroup()
							.addContainerGap(580, Short.MAX_VALUE)
							.addComponent(btn_selectall)))
					.addGap(35))
		);
		gl_select_p.setVerticalGroup(
			gl_select_p.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_select_p.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_select_p.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lb_dh)
						.addComponent(label)
						.addComponent(textField_Start, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(textField_End, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Button_Select))
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(btn_selectall))
		);
		select_p.setLayout(gl_select_p);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(select_p, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(select_p, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
					.addGap(8))
		);
		
		
		tableModel=new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"单号", "公司名", "电话", "联系人", "订货日期", "总金额", "数量", "New column"
			}
		);
		table = new JTable(tableModel){
		    public boolean isCellEditable(int rowIndex, int ColIndex){
	             return false;
	            }
	    } ;
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		//查询全部
		btn_selectall.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				List list = Dao.getPInfos();
				Iterator iterator=list.iterator();
				updateTable(iterator, tableModel);
			}
		});
	
	    //查询
		Button_Select.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		DoublePrint();
	}
	
	

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
	//重新打印
	private void DoublePrint(){
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if( e.getClickCount()==2){
					int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置 
					String cellVal=(String)(tableModel.getValueAt(row,0)); 
					System.out.println(cellVal);
					JasperDesign jasperDesign;
					try {
						jasperDesign = JRXmlLoader.load("report3.jrxml");
					
			    	JasperReport jasperReport=JasperCompileManager.compileReport(jasperDesign);
			    	HashMap p=new HashMap();
			    	p.put("name", "郑泽游");
			    	JRResultSetDataSource rsd= new JRResultSetDataSource(Dao.query("select sgs,sphone,name,zblsh,dname,dw,dj,gg,sl,zje,bz,zsl,mj from printdate where zblsh='"+cellVal+"'"));
			    	JasperPrint  jasperprint=JasperFillManager.fillReport(jasperReport, p,rsd);
			    	JasperViewer  jr=new JasperViewer (jasperprint,false);
			    	jr.setVisible(true);
			    	jr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
			    	catch (JRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
	}
}
