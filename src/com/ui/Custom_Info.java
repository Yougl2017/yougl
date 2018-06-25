package com.ui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dao.bean.Company;
import com.dao.conn.Dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Custom_Info extends JInternalFrame {
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField CName_textField;
	private JTextField Contact_textField;
	private JTextField PY_textField;
	private JTextField Phone_textField;
	private JTextField Address_textField;
	private JTextField Remark_textField;
	private JLabel EID_Label;
	private JButton NUserButton;
	private JButton ColseButton;
	private JButton SaveButton;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Custom_Info() {
		//初始化
		initialize();
		
		//加载信息
		Load_Table();
		
		//选择信息
		Table_Action();
		//新增信息
		New_Uer_Info();
		//保存信息
		Save_Info();
		
		
		//关闭窗口
		Close_Win();

	}
	//初始化窗口
	private void initialize() {
		setTitle("客户信息");
		setBounds(0, 0, 800, 900);
		setClosable(true);
		setMaximizable(true);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2);
		
		JLabel CName_Lable = new JLabel("公司名称：");
		panel_2.add(CName_Lable);
		
		CName_textField = new JTextField();
		panel_2.add(CName_textField);
		CName_textField.setColumns(40);
		
		JLabel PY_Label = new JLabel("拼音：");
		panel_2.add(PY_Label);
		
		PY_textField = new JTextField();
		panel_2.add(PY_textField);
		PY_textField.setColumns(10);
		
		JLabel Contact_Label = new JLabel("联系人：");
		panel_2.add(Contact_Label);
		
		Contact_textField = new JTextField();
		panel_2.add(Contact_textField);
		Contact_textField.setColumns(10);
		
		JLabel Id_Label = new JLabel("编号:");
		panel_2.add(Id_Label);
		
		EID_Label = new JLabel("");
		panel_2.add(EID_Label);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3);
		
		JLabel Phone_Label = new JLabel("联系电话：");
		panel_3.add(Phone_Label);
		
		Phone_textField = new JTextField();
		panel_3.add(Phone_textField);
		Phone_textField.setColumns(18);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel.add(panel_4);
		
		JLabel Address_Label = new JLabel("联系地址：");
		panel_4.add(Address_Label);
		
		Address_textField = new JTextField();
		panel_4.add(Address_textField);
		Address_textField.setColumns(50);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_5.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel.add(panel_5);
		
		JLabel Remark_Label = new JLabel("备    注：");
		panel_5.add(Remark_Label);
		
		Remark_textField = new JTextField();
		panel_5.add(Remark_textField);
		Remark_textField.setColumns(80);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
		tableModel= new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"编号", "公司名", "首拼", "联系人", "联系电话", "联系地址", "备注"
				}
			);
		
		table = new JTable(tableModel){
		    public boolean isCellEditable(int rowIndex, int ColIndex){
	             return false;
	            }
		};
		table.setEnabled(true);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		JPanel Button_panel = new JPanel();
		FlowLayout fl_Button_panel = (FlowLayout) Button_panel.getLayout();
		fl_Button_panel.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(Button_panel, BorderLayout.SOUTH);
		
		NUserButton = new JButton("新增");
		Button_panel.add(NUserButton);
		
		SaveButton = new JButton("保存");
		Button_panel.add(SaveButton);
		
		ColseButton = new JButton("关闭");
		Button_panel.add(ColseButton);
		setVisible(true);
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
    //加载表信息
    public void Load_Table(){
    	tableModel.setRowCount(0);
    	List list = Dao.getKhInfos("");
		Iterator iterator=list.iterator();
		updateTable(iterator, tableModel);
    }
    //选择表信息
    public void Table_Action(){
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
  					String Id=(String)(tableModel.getValueAt(row,0)); 
  					EID_Label.setText(Id);
  					String name=(String)(tableModel.getValueAt(row,1)); 
  					CName_textField.setText(name);
  					String py=(String)(tableModel.getValueAt(row,2)); 
  					PY_textField.setText(py);
  					String contacts=(String)(tableModel.getValueAt(row,3)); 
  					Contact_textField.setText(contacts);
  					String phone=(String)(tableModel.getValueAt(row,4)); 
  					Phone_textField.setText(phone);
  					String address=(String)(tableModel.getValueAt(row,5));
  					Address_textField.setText(address);
  					String remark=(String)(tableModel.getValueAt(row,6));
  					Remark_textField.setText(remark);
  					
				}
			}
		});
    }
    
    //新增用户
    public void New_Uer_Info(){
    	NUserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CName_textField.setText("");
				PY_textField.setText("");
				Contact_textField.setText("");
				Address_textField.setText("");
				Remark_textField.setText("");
				Phone_textField.setText("");
				int tid=(Integer.valueOf(tableModel.getValueAt(tableModel.getRowCount()-1, 0).toString())+1);
				String id = String.valueOf(tid);
				EID_Label.setText(id);
				
			}
		});
    }
    //保存信息
    public void Save_Info(){
    	SaveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String id=EID_Label.getText();
				String cname = CName_textField.getText();
				String contacts = Contact_textField.getText();
				String address = Address_textField.getText();
				String phone = Phone_textField.getText();
				String remark =Remark_textField.getText();
				String py =PY_textField.getText();
				
				if (id==""){
					JOptionPane.showMessageDialog(null, "无数据 可保存", "提示",JOptionPane.CLOSED_OPTION);
				}else{
					Company company = new Company(Integer.valueOf(id),cname,contacts,address,phone,remark,py);
					boolean rs=Dao.addCompany(company);
					if (rs==true){
						JOptionPane.showMessageDialog(null, "保存成功", "提示",JOptionPane.CLOSED_OPTION);
						Load_Table();
					}else{
						JOptionPane.showMessageDialog(null, "保存失败！！！！", "提示",JOptionPane.CLOSED_OPTION);
					}
				}
				
			}
		});
    }
    
    //关闭窗口
    public void Close_Win(){
    	ColseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
    	
    }
}
