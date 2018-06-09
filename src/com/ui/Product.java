package com.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.dao.bean.Company;
import com.dao.bean.Goods;
import com.dao.conn.Dao;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

public class Product extends JInternalFrame {

	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField GName_textField;
	private JTextField PY_textField;
	private JTextField Type_textField;
	private JTextField Remark_textField;
	private JLabel EID_Label;
	private JButton NUserButton;
	private JButton ColseButton;
	private JButton SaveButton;
	private JSpinner Price_spinner;
	private JTextField specs_textField;
	private JTextField Price_textField;
	private JCheckBox is_checkBox;
	

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Product() {
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
		
		JLabel GName_Lable = new JLabel("商品名称：");
		panel_2.add(GName_Lable);
		
		GName_textField = new JTextField();
		panel_2.add(GName_textField);
		GName_textField.setColumns(40);
		
		JLabel PY_Label = new JLabel("拼音：");
		panel_2.add(PY_Label);
		
		PY_textField = new JTextField();
		panel_2.add(PY_textField);
		PY_textField.setColumns(10);
		
		JLabel Id_Label = new JLabel("编号:");
		panel_2.add(Id_Label);
		
		EID_Label = new JLabel("");
		panel_2.add(EID_Label);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_3.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel.add(panel_3);
		
		JLabel Price_Label = new JLabel("价    格：");
		panel_3.add(Price_Label);
		
		Price_spinner = new JSpinner();
		Price_spinner.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(10000), new Float(1)));
		panel_3.add(Price_spinner);
		
		JLabel label = new JLabel("规格：");
		panel_3.add(label);
		
		specs_textField = new JTextField();
		panel_3.add(specs_textField);
		specs_textField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel.add(panel_4);
		
		JLabel type_Label = new JLabel("类    型：");
		panel_4.add(type_Label);
		
		Type_textField = new JTextField();
		panel_4.add(Type_textField);
		Type_textField.setColumns(20);
		
		is_checkBox = new JCheckBox("是否有效");
		is_checkBox.setSelected(true);
		panel_4.add(is_checkBox);
		
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
					"编号", "商品名称", "单价", "规格", "类型","是否有效","首拼", "备注"
				}
			);
		
		table = new JTable(tableModel){
		    public boolean isCellEditable(int rowIndex, int ColIndex){
	             return false;
	            }
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
    	List list = Dao.getspInfos1();
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
  					EID_Label.setText(Id);  //编号
  					String name=(String)(tableModel.getValueAt(row,1)); 
  					GName_textField.setText(name);  //商品名称
  					String price=(String)(tableModel.getValueAt(row,2)); 
  					Price_spinner.setValue(Double.valueOf(price));//单价
  					String specs=(String)(tableModel.getValueAt(row,3));
  					specs_textField.setText(specs);  //规格
  					String type=(String)(tableModel.getValueAt(row,4));
  					Type_textField.setText(type);  //类型
  					
  					String isable=(String)(tableModel.getValueAt(row,5));
  				
  					if (isable.equals("0")){
  						is_checkBox.setSelected(true);
  				
  					}else {
  						is_checkBox.setSelected(false);
  					}// 判断有效
  					
  					String py=(String)(tableModel.getValueAt(row,6)); 
  					PY_textField.setText(py);   //py
  					
  					String remark=(String)(tableModel.getValueAt(row,7));
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
				GName_textField.setText("");
				PY_textField.setText("");
				Type_textField.setText("");
				Remark_textField.setText("");
				Price_spinner.setValue(0);
				int tid=(Integer.valueOf(tableModel.getValueAt(tableModel.getRowCount()-1, 0).toString())+1);
				String id = String.valueOf(tid);
				EID_Label.setText(id);
				is_checkBox.setSelected(true);
				
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
				String Gname = GName_textField.getText();
				String able;
		        boolean isable=is_checkBox.isSelected();
		        if (isable==true){
		        	able="0";
		        }else {
		        	able="1";
		        }
				String Type = Type_textField.getText();
				String Price = Price_spinner.getValue().toString();
				String specs = specs_textField.getText();
				String remark =Remark_textField.getText();
				String py =PY_textField.getText();
				
				if (id==""){
					JOptionPane.showMessageDialog(null, "无数据 可保存", "提示",JOptionPane.CLOSED_OPTION);
				}else{
					Goods goods = new Goods(Integer.valueOf(id),Gname,Price,specs,"00",Type,able,remark,py);
					boolean rs=Dao.addProduct(goods);
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
