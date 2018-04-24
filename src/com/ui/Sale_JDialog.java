package com.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Choice;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;

import com.dao.conn.Dao;
import com.other.Item;

import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class Sale_JDialog extends JDialog{
	private JTextField ramark_textField;
	private DefaultTableModel tablemodel;
	private JTable table;
	private JComboBox sale_comboBox;  //客户信息
	private JComboBox Prc_comboBox; //商品信息
	private JButton addButton;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			Sale_JDialog dialog = new Sale_JDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 * @param b 
	 * @param frame 
	 */
	public Sale_JDialog(JFrame frame, boolean b) {
	    setVisible(true);
	    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    setModalityType(ModalityType.APPLICATION_MODAL);
	    setTitle("销售单");
	    setModal(true);
		setBounds(0, 0, 1024, 600);
		
		tablemodel= new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
							"商品名称","规格","单位","数量","总金额","面积","周长","开口","钻孔","备注"
				}
			);
			
	    ///中间板
		 {
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable(tablemodel);
				scrollPane.setColumnHeaderView(table);
				scrollPane.setViewportView(table);
			}
		}
		
		 //底部按钮
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
				FlowLayout flowLayout = (FlowLayout) panel.getLayout();
				flowLayout.setAlignment(FlowLayout.LEADING);
				Salepanel.add(panel);
				{
					JLabel sale_label = new JLabel("客户名称");
					panel.add(sale_label);
				}
				{
					sale_comboBox = new JComboBox();
					panel.add(sale_comboBox);
				}
			}
			
			//商品属性板
			{
				JPanel panel = new JPanel();
				FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
				flowLayout_1.setAlignment(FlowLayout.LEADING);
				Salepanel.add(panel);
				{
					JLabel prc_label = new JLabel("商品名称");
					panel.add(prc_label);
				}
				{
					Prc_comboBox = new JComboBox();
					Prc_comboBox.setEditable(true);
					panel.add(Prc_comboBox);
				}
				{
					JCheckBox zb_CheckBox = new JCheckBox("直边");
					panel.add(zb_CheckBox);
				}
				{
					JCheckBox xb_checkBox = new JCheckBox("斜边");
					panel.add(xb_checkBox);
				}
				{
					JComboBox blx_comboBox = new JComboBox();
					panel.add(blx_comboBox);
					blx_comboBox.setModel(new DefaultComboBoxModel(new String[] {"四边", "二长", "二短", "一长一短", "一长", "一短", "无"}));
				}
				{
					JCheckBox zk_checkBox = new JCheckBox("钻孔");
					panel.add(zk_checkBox);
				}
				{
					JSpinner zk_spinner = new JSpinner();
					panel.add(zk_spinner);
				}
				{
					JCheckBox kk_checkBox = new JCheckBox("开口");
					panel.add(kk_checkBox);
				}
				{
					JSpinner kk_spinner = new JSpinner();
					panel.add(kk_spinner);
				}
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
		//添加尺寸
		Add_Size_Info();
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(true);
				frame.setEnabled(true);
				frame.requestFocusInWindow();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		} );
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
					//Sale_Dialog_Size sale_dialog_size= new Sale_Dialog_Size(map);
					}
				}
			});
			
		
			
			
		}
		
}
