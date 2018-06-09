package com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.bean.Goods;
import com.dao.bean.Machine;
import com.dao.bean.Sale;
import com.dao.bean.SaleDetail;
import com.dao.conn.Dao;

import com.other.Item;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Sale_New extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField ramark_textField;
	private DefaultTableModel tablemodel;
	private JTable table;
	private JComboBox<Item> sale_comboBox;  //客户信息
	private JComboBox<Item> Prc_comboBox; //商品信息
	private JButton addButton;      //添加尺寸
	private JCheckBox zb_CheckBox;//直边
	private JCheckBox xb_checkBox;//斜边
	private JComboBox<?> blx_comboBox;//边类型
	private JCheckBox zk_checkBox;//钻孔
	private JSpinner zk_spinner;//钻孔数量
	private JCheckBox kk_checkBox;//开口
	private JSpinner kk_spinner;//开口数量
	public Sale_New sale=this;
	private JPanel panel_1;    //客户板块
	private JPanel panel_2;    //订单号板块
	private JLabel lblNewLabel;
	private JLabel Label_ddh;
	private JButton printButton;   //打印按钮
	private int issave=0;         //标记
	private JButton okButton;
	private JButton cancelButton;
	private JButton RemarkButton;
	private JComboBox<Item> zb_comboBox;  //磨边加工
	private JComboBox<Item> zk_comboBox;  //钻孔类型
	private JComboBox<Item> kk_comboBox;  //开口类型
	private JPopupMenu del_popupMenu;   //删除菜单

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 * @param b 
	 * @param frame 
	 */
	public Sale_New() {
	    this.setVisible(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setTitle("销售单");
	    
		setBounds(0, 0, 1024, 600);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);  //最大化
		tablemodel= new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
							"商品名称","长","宽","单价","单位","数量","面积","金额","周长","磨边单价","磨边","开口单价","开口","钻孔单价","钻孔","总金额","备注"
				}
			);
			
	    ///中间板
		 {
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable(tablemodel);
				table.getColumnModel().getColumn(0).setPreferredWidth(100);
				table.getColumnModel().getColumn(1).setPreferredWidth(60);
				table.getColumnModel().getColumn(2).setPreferredWidth(60);
				table.getColumnModel().getColumn(3).setPreferredWidth(60);
				table.getColumnModel().getColumn(4).setPreferredWidth(40);
				table.getColumnModel().getColumn(5).setPreferredWidth(40);
				table.getColumnModel().getColumn(6).setPreferredWidth(40);
				table.getColumnModel().getColumn(7).setPreferredWidth(60);
				table.getColumnModel().getColumn(8).setPreferredWidth(60);
				table.getColumnModel().getColumn(16).setPreferredWidth(300);
				scrollPane.setColumnHeaderView(table);
				scrollPane.setViewportView(table);
				table.getTableHeader().setReorderingAllowed(false); //不支持重新排列
				table.setEnabled(false);
				
			  
			}
		}
		
		 //底部按钮
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("保存");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Save_Info();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				printButton = new JButton("打印");
				buttonPane.add(printButton);
			}
			{
				RemarkButton = new JButton("标签打印");
				buttonPane.add(RemarkButton);
			}
			{
				cancelButton = new JButton("关闭");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		//客户信息板
		{
			JPanel Salepanel = new JPanel();
			getContentPane().add(Salepanel, BorderLayout.NORTH);
			Salepanel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JPanel panel = new JPanel();
				Salepanel.add(panel);
				panel.setLayout(new BorderLayout(0, 0));
				{
					panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.WEST);
					{
						JLabel sale_label = new JLabel("客户名称");
						panel_1.add(sale_label);
					}
					{
						sale_comboBox = new JComboBox<Item>();
						panel_1.add(sale_comboBox);
					}
				}
				{
					panel_2 = new JPanel();
					panel.add(panel_2, BorderLayout.EAST);
					{
						lblNewLabel = new JLabel("订单号：");
						panel_2.add(lblNewLabel);
					}
					{
						Label_ddh = new JLabel(Load_ddh_Info());
						Label_ddh.setForeground(Color.red);
						panel_2.add(Label_ddh);
					}
				}
			}
			
			//商品属性板
			{
				JPanel panel = new JPanel();
				FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
				flowLayout_1.setAlignment(FlowLayout.LEADING);
				Salepanel.add(panel);
				
				JLabel prc_label = new JLabel("商品名称");
				panel.add(prc_label);
				
				
				Prc_comboBox = new JComboBox<Item>();
				Prc_comboBox.setEditable(true);
				panel.add(Prc_comboBox);
				
				
				zb_CheckBox = new JCheckBox("直边");
				panel.add(zb_CheckBox);
				
				
				xb_checkBox = new JCheckBox("斜边");
				panel.add(xb_checkBox);
				
				
				zb_comboBox = new JComboBox();
				panel.add(zb_comboBox);
				
				
				blx_comboBox = new JComboBox();
				panel.add(blx_comboBox);
				blx_comboBox.setModel(new DefaultComboBoxModel(new String[] {"四边", "二长", "二短", "一长一短", "一长", "一短", "无"}));
				
				
				zk_checkBox = new JCheckBox("钻孔");
				panel.add(zk_checkBox);
				
				
				zk_comboBox = new JComboBox();
				panel.add(zk_comboBox);
				
				
				zk_spinner = new JSpinner();
				panel.add(zk_spinner);
				
				
				kk_checkBox = new JCheckBox("开口");
				panel.add(kk_checkBox);
				
				
				kk_comboBox = new JComboBox();
				panel.add(kk_comboBox);
				
				
				kk_spinner = new JSpinner();
				panel.add(kk_spinner);
				
			}
			
			//备注板
			{
				JPanel panel = new JPanel();
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.LEADING);
				Salepanel.add(panel);
				{
					JLabel remark_label = new JLabel("备注");
					panel.add(remark_label);
				}
				{
					ramark_textField = new JTextField();
					panel.add(ramark_textField);
					ramark_textField.setColumns(80);
				}
				{
					addButton = new JButton("添加尺寸");
					addButton.setHorizontalAlignment(SwingConstants.LEADING);
					panel.add(addButton);
				}
			}
			
			
			
		}
		//加载客户信息
		Load_Customer_Info();
		//加载商品信息
		Load_Product_Info();
		//加载备注信息
		Combox_Action();
		//加载加工信息
		isselect();
		//添加尺寸
		Add_Size_Info();
		//打印
		Print_Info();
		//标签打印
		Remark_Print_Info();
		//关闭
		Close_Info();
	}
    //加载客户信息
	public void Load_Customer_Info(){
		List getkhInfos = Dao.getKhInfos();
		for (Iterator iter = getkhInfos.iterator(); iter.hasNext();) {
			List list = (List) iter.next();
			Item item = new Item();
			item.setId(list.get(0).toString().trim());
			item.setName(list.get(1).toString().trim());
			sale_comboBox.addItem(item);
			sale_comboBox.setEditable(true);
			sale_comboBox.setSelectedIndex(-1);
		
		}
	}
	//加载商品信息
	public void Load_Product_Info(){
		List getspInfos = Dao.getspInfos();
		for (Iterator iter = getspInfos.iterator(); iter.hasNext();) {
			List list = (List) iter.next();
			Item item = new Item();
			//item.setId(list.get(0).toString().trim());
			item.setName(list.get(1).toString().trim());
			Prc_comboBox.addItem(item);
			Prc_comboBox.setSelectedIndex(-1);
			
		
		}
	}
	
	//加载备注信息
	public String Load_Remark_Info(){
		String remark="";
		if (zb_CheckBox.isSelected()){
			remark=remark+"直"+blx_comboBox.getSelectedItem().toString()+" ";	
			
		}else if (xb_checkBox.isSelected()){
			remark=remark+"斜"+blx_comboBox.getSelectedItem().toString()+" ";
		}else {
			remark="";
		}
		if (kk_checkBox.isSelected()){
		    remark=remark+"开口"+kk_spinner.getValue().toString()+"个"+" ";
		}
		if (zk_checkBox.isSelected()){
			remark=remark+"钻孔"+zk_spinner.getValue().toString()+"个"+" ";
		}
		return remark;
	}
	//添加尺寸
	public void Add_Size_Info(){
		
		
                addButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (Prc_comboBox.getSelectedIndex()==-1||sale_comboBox.getSelectedIndex()==-1||Prc_comboBox.getSelectedItem().toString()==""||sale_comboBox.getSelectedItem().toString()=="")
					{
						JOptionPane.showMessageDialog(null, "商品或客户不为空", "提示",JOptionPane.CLOSED_OPTION);
						
					}else{
					Map<String, String>  map= new HashMap<>();
					map.put("customer", sale_comboBox.getSelectedItem().toString());
					map.put("product",Prc_comboBox.getSelectedItem().toString());
					if (zb_CheckBox.isSelected()){
						map.put("mbsl", "直"+blx_comboBox.getSelectedItem().toString());
					}else if (xb_checkBox.isSelected()){
						map.put("mbsl", "斜"+blx_comboBox.getSelectedItem().toString());
					}else{
						map.put("mbsl", " ");
					}
					if(zk_checkBox.isSelected()){
						map.put("zksl", "钻孔"+zk_spinner.getValue().toString());
					}else {
						map.put("zksl", "");
					}
					if(kk_checkBox.isSelected()){
						map.put("kksl", "开口"+kk_spinner.getValue().toString());
					}else{
						map.put("kksl", "");
					}
						
						
						
					Sale_Dialog_Size sale_dialog_size= new Sale_Dialog_Size(map,sale);
					}
				}
			});
			
		
			
			
		}
	
	//添加表数据
	public void addData(List list){
		Item item=(Item)Prc_comboBox.getSelectedItem();
		Goods Goods = Dao.getspInfo(item); 
		Machine machine = new Machine();
		Machine zk_machine=new Machine();
		Machine kk_machine=new Machine();
		if(zb_CheckBox.isSelected()==true|| xb_checkBox.isSelected()==true){
			Item mb_item=(Item)zb_comboBox.getSelectedItem();
			machine= Dao.getMachineInfo(mb_item);
		}else{
		    
		    machine.setPrice(0);
		}
		
		Item zk_item=(Item)zk_comboBox.getSelectedItem();
		if (kk_checkBox.isSelected()){
			zk_machine= Dao.getMachineInfo(zk_item);
		}else{
			
			zk_machine.setPrice(0);
		}
		
		Item kk_item=(Item)kk_comboBox.getSelectedItem();
		if(kk_checkBox.isSelected()==true){
			kk_machine= Dao.getMachineInfo(kk_item);
		}else{
			
			kk_machine.setPrice(0);
		}
		
		for (int i=0;i<list.size();i=i+4){
			List<String> list_info=new ArrayList<String>();
			list_info.add(Goods.getGoodsName());      //1产品
			list_info.add((String) list.get(i));      //2长
			list_info.add((String) list.get(i+1));   //3宽
			list_info.add(Goods.getGoodsPrice());    //4单价
			list_info.add("㎡");                     //5单位
			list_info.add((String) list.get(i+2));  //6数量
			
			//不足0.25平方 按0.25平方计算
			String temp;
			double t=Double.parseDouble((String)list.get(i))*Double.parseDouble((String)list.get(i+1))*0.001*0.001;
			if (t>=0.25){
				temp=String.valueOf(String.format("%.3f",Double.parseDouble((String)list.get(i))*Double.parseDouble((String)list.get(i+1))*
						Double.parseDouble((String)list.get(i+2))*0.001*0.001));	
			}else{
				temp=String.valueOf(String.format("%.3f",0.25*Double.parseDouble((String)list.get(i+2))));
			}
			
			 
			
			
			
			list_info.add(temp);  //7面积
			String temp_count=String.valueOf(String.format("%.2f",Double.parseDouble(Goods.getGoodsPrice())*
					Double.parseDouble(temp)));
			list_info.add(temp_count);  //8玻璃金额
			
			String temp_long=String.valueOf(String.format("%.2f",Double.parseDouble((String)list.get(i))*2*0.001+
					Double.parseDouble((String)list.get(i+1))*2*0.001));
			
			list_info.add(temp_long);    //9周长
			
			list_info.add(String.valueOf(machine.getPrice())); //10磨边单价
			 
			
			String temp_mb;
			if (zb_CheckBox.isSelected()|| xb_checkBox.isSelected()){
				if (blx_comboBox.getSelectedItem().toString()=="四边"){
					temp_mb=String.valueOf(String.format("%.2f",Double.parseDouble((String)list.get(i))*2*0.001+
							Double.parseDouble((String)list.get(i+1))*2*0.001));
				}else if (blx_comboBox.getSelectedItem().toString()=="二长"){
					
					temp_mb=String.valueOf(String.format("%.2f",Double.parseDouble((String)list.get(i))*2*0.001));
				}else if (blx_comboBox.getSelectedItem().toString()=="二短"){
					temp_mb=String.valueOf(String.format("%.2f",
							Double.parseDouble((String)list.get(i+1))*2*0.001));
					
				}else if (blx_comboBox.getSelectedItem().toString()=="一长一短"){
					temp_mb=String.valueOf(String.format("%.2f",Double.parseDouble((String)list.get(i))*0.001+
							Double.parseDouble((String)list.get(i+1))*0.001));
				}else if (blx_comboBox.getSelectedItem().toString()=="一长"){
					temp_mb=String.valueOf(String.format("%.2f",Double.parseDouble((String)list.get(i))*0.001));
				}else if (blx_comboBox.getSelectedItem().toString()=="一短"){
					temp_mb=String.valueOf(String.format("%.2f",Double.parseDouble((String)list.get(i+1))*0.001));
				}else {
					temp_mb="0.00";
				}
				
			}else {
				temp_mb="0.00";
			}
			
			temp_mb=String.valueOf(Double.valueOf(temp_mb)*Double.valueOf(list.get(i+2).toString()));
	
			
			list_info.add(temp_mb); // 11磨边
			
			list_info.add(String.valueOf(kk_machine.getPrice()));  //12开口单价
			String temp_kk;
			if (kk_checkBox.isSelected()){
				temp_kk=String.valueOf(Integer.valueOf(kk_spinner.getValue().toString())*Integer.valueOf(list.get(i+2).toString()));
			}else{
				temp_kk="0";
						
			}
			
			
			list_info.add(temp_kk);  //13开口
			
			
			list_info.add(String.valueOf(zk_machine.getPrice())); //14钻孔单价
			String temp_zk;
			if (zk_checkBox.isSelected()){
				temp_zk=String.valueOf(Integer.valueOf(zk_spinner.getValue().toString())*Integer.valueOf(list.get(i+2).toString()));
			}else{
				temp_zk="0";
						
			}
			
			list_info.add(temp_zk);  //15 钻孔
			
			String temp_sum=String.valueOf(String.format("%.2f",Double.parseDouble(temp_count)+Double.parseDouble(temp_mb)*machine.getPrice()+Double.parseDouble(temp_kk)*kk_machine.getPrice()+Double.parseDouble(temp_zk)*zk_machine.getPrice()));
			
			list_info.add(temp_sum);  //总金额
			
			list_info.add(ramark_textField.getText()+String.valueOf( list.get(i+3)));
			Object[] row=list_info.toArray();
			tablemodel.addRow(row);
			
			
		}
		
	}
    //触发事件备注信息
	public void Combox_Action(){
		ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				ramark_textField.setText(Load_Remark_Info());
			}
		};
		ChangeListener clistener= new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				ramark_textField.setText(Load_Remark_Info());
			}
		};
		zb_CheckBox.addItemListener(itemlistener);
		xb_checkBox.addItemListener(itemlistener);
		kk_checkBox.addItemListener(itemlistener);
		zk_checkBox.addItemListener(itemlistener);
		blx_comboBox.addItemListener(itemlistener);
		kk_spinner.addChangeListener(clistener);
		zk_spinner.addChangeListener(clistener);
		
	}
    //加载订单号
	public String Load_ddh_Info(){
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		String lsh = Dao.getSellMainMaxId(date);
		return lsh;
    }
    //保存信息
	public void Save_Info(){
		if (issave==0){
			InsertDate();
			JOptionPane.showMessageDialog(null, "保存成功", "提示",JOptionPane.CLOSED_OPTION);
			sale_comboBox.setEnabled(false);
			Prc_comboBox.setEnabled(false);
			++issave;
			okButton.setEnabled(false);
		}else {
			JOptionPane.showMessageDialog(null, "已保存，不可修改", "提示",JOptionPane.CLOSED_OPTION);
			okButton.setEnabled(false);
		}
		
	}
	//插入数据库
	public void InsertDate(){
		int row = tablemodel.getRowCount();
		int col = tablemodel.getColumnCount();
		String[][] value = new String[row][col]; 
		
		for(int i = 0; i < row; i++){  
            for(int j = 0; j < col; j++){  
                value[i][j] = tablemodel.getValueAt(i, j).toString();  
            }  
        } 
		for(int i = 0; i < row; i++){  
			SaleDetail saledetail= new SaleDetail(Label_ddh.getText(),
					                               "0",
					                               tablemodel.getValueAt(i, 0).toString(),
					                               "㎡",
					                               Double.valueOf(tablemodel.getValueAt(i, 3).toString()),
					                               Double.valueOf(tablemodel.getValueAt(i, 1).toString()),
					                               Double.valueOf(tablemodel.getValueAt(i, 2).toString()),
					                               tablemodel.getValueAt(i, 1).toString()+"*"+tablemodel.getValueAt(i, 2).toString(),
					                               Integer.valueOf(tablemodel.getValueAt(i, 5).toString()),
					                               "",
					                               Double.valueOf(tablemodel.getValueAt(i, 9).toString()),
	    			                               Double.valueOf(tablemodel.getValueAt(i, 10).toString()),
	    			                               " ",
	    			                               Double.valueOf(tablemodel.getValueAt(i, 11).toString()),
	    			                               Integer.valueOf(tablemodel.getValueAt(i, 12).toString().trim()),
	    			                               "",
	    			                               Double.valueOf(tablemodel.getValueAt(i, 13).toString()),
	    			                               Integer.valueOf(tablemodel.getValueAt(i, 14).toString().trim()),
	    			                               Double.valueOf(tablemodel.getValueAt(i, 15).toString().trim()),
	    			                               tablemodel.getValueAt(i, 16).toString()); 
	    	boolean rs = Dao.addSaleDetail(saledetail);
        }
		
		//主表
		String zblsh= Load_ddh_Info();
		Sale sale= new Sale(zblsh,sale_comboBox.getSelectedItem().toString(),"","","5","6","7","8","9","10","11","12","13","14","15","16");
    	boolean rs=Dao.addSale(sale);
    	Dao.addRemark(sale);  //标签打印
    	
		
		
	}
	//打印信息
	public void Print_Info(){
		printButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String lsh = Label_ddh.getText();
				JasperDesign jasperDesign;
					try {
						jasperDesign = JRXmlLoader.load("report3.jrxml");
					
			    	JasperReport jasperReport=JasperCompileManager.compileReport(jasperDesign);
			    	HashMap p=new HashMap();
			    	p.put("name", "郑泽游");
			    	JRResultSetDataSource rsd= new JRResultSetDataSource(Dao.query("select sgs,sphone,name,zblsh,dname,dw,dj,gg,sl,zje,bz,zsl,mj from printdate where zblsh='"+lsh+"'"));
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
		});
	    
	}
	//关闭信息
	public void Close_Info(){
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
	            dispose();
			}
		});
	}
	
	//加载加工信息
	public void Machin_Info(String lx,JComboBox jcb){
		jcb.removeAllItems();
		List getMachineInfos = Dao.getMachineInfo(lx);
		for (Iterator iter = getMachineInfos.iterator(); iter.hasNext();) {
			List list = (List) iter.next();
			Item item = new Item();
			item.setId(list.get(0).toString().trim());
			item.setName(list.get(1).toString().trim());
			jcb.addItem(item);
			jcb.setEditable(true);
			jcb.setSelectedIndex(-1);
		
		}
	}
    //打印标签  
	public void Remark_Print_Info(){
		RemarkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String lsh = Label_ddh.getText();
				JasperDesign jasperDesign;
					try {
						jasperDesign = JRXmlLoader.load("tag.jrxml");
					
			    	JasperReport jasperReport=JasperCompileManager.compileReport(jasperDesign);
			    	HashMap p=new HashMap();
			    	p.put("name", "郑泽游");
			    	JRResultSetDataSource rsd= new JRResultSetDataSource(Dao.query("select * from printremark where zid='"+lsh+"'"));
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
		});
	}
	
	//勾选加工信息
	public void isselect(){
		ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Object obj = e.getItem();
				if (obj.equals(zb_CheckBox)){
					if (zb_CheckBox.isSelected()){
						zb_comboBox.setEnabled(true);
					    xb_checkBox.setEnabled(false);
					   Machin_Info("直边",zb_comboBox);
					}else{
						xb_checkBox.setEnabled(true);
						zb_comboBox.removeAllItems();
						zb_comboBox.setEnabled(false);
					}
				}else if (obj.equals(xb_checkBox)) {
					if (xb_checkBox.isSelected()){
					    zb_CheckBox.setEnabled(false);
					    zb_comboBox.setEnabled(true);
					    zb_comboBox.removeAllItems();
					    Machin_Info("斜边",zb_comboBox);
					}else{
						zb_CheckBox.setEnabled(true);
						zb_comboBox.removeAllItems();
						zb_comboBox.setEnabled(false);
					}
				}
				if (obj.equals(zk_checkBox)){
					if (zk_checkBox.isSelected()){
						zk_comboBox.setEnabled(true);
						zk_comboBox.removeAllItems();
						Machin_Info("钻孔",zk_comboBox);
						zk_spinner.setEnabled(true);
						
					}else{
						zk_comboBox.setEnabled(false);
						zk_spinner.setEnabled(false);
					}
					
				}
				if (obj.equals(kk_checkBox)){
					if (kk_checkBox.isSelected()){
						kk_comboBox.setEnabled(true);
						kk_comboBox.removeAllItems();
					    Machin_Info("开口",kk_comboBox);
					    kk_spinner.setEnabled(true);
					}else {
						kk_comboBox.setEnabled(true);
						kk_comboBox.removeAllItems();
						kk_spinner.setEnabled(false);
					}
				}
			}
		};
		zb_CheckBox.addItemListener(itemlistener);
		xb_checkBox.addItemListener(itemlistener);
		kk_checkBox.addItemListener(itemlistener);
		zk_checkBox.addItemListener(itemlistener);
		
	}
	//右击删除信息
	public void Dele_Info(){
		
	}
	//删除菜单
	private void createPopupMenu() {  
        del_popupMenu = new JPopupMenu();  
          
        JMenuItem delMenItem = new JMenuItem();  
        delMenItem.setText("  删除  ");  
        delMenItem.addActionListener(new java.awt.event.ActionListener() {  
            public void actionPerformed(java.awt.event.ActionEvent evt) {  
                //该操作需要做的事  
            }  
        });  
        del_popupMenu.add(delMenItem);  
    } 
}
