package com.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.dao.conn.Dao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SaleMain extends JInternalFrame {
	
	
	private JTable table;
	private DefaultTableModel tablemodel;
	private JPanel Buttonpanel;
	private JButton NewButton;
	private JButton SelectButton;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public SaleMain() {
		initialize();
		DoublePrint();
		NewAction();
		Select_Info();

	}
	
	private void initialize() {
		
		setTitle("订货单");
		setBounds(0, 0, 800, 900);
		setClosable(true);
		setMaximizable(true);
		setVisible(true);
		
		
		
		Buttonpanel = new JPanel();
		getContentPane().add(Buttonpanel, BorderLayout.NORTH);
		Buttonpanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
        NewButton = new JButton("新建订单");
		NewButton.setSize(400, 400);
		NewButton.setHorizontalAlignment(SwingConstants.LEADING);
		Buttonpanel.add(NewButton);
		
		SelectButton = new JButton("查询订单");
		Buttonpanel.add(SelectButton);
		
		JScrollPane ContentscrollPane = new JScrollPane();
		getContentPane().add(ContentscrollPane, BorderLayout.CENTER);
		
		//表格式处理
	
		tablemodel= new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
						"订单号","公司名","联系人","联系电话","总金额","面积","订货时间"
				}
			);
		table = new JTable(tablemodel){
		    public boolean isCellEditable(int rowIndex, int ColIndex){
	             return false;
	            }
	    } ;
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
				Sale_New sale_new = new Sale_New();
			}
		});
    	
    	
    }
    //查询信息
    public void Select_Info(){
    	SelectButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List list = Dao.getPInfos();
				Iterator iterator=list.iterator();
				updateTable(iterator, tablemodel);
			}
		});
    	
    }
    //加载信息
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
  					String cellVal=(String)(tablemodel.getValueAt(row,0)); 
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
