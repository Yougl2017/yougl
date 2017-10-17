package com.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.bean.Company;
import com.dao.bean.Goods;
import com.dao.bean.TempSaleDetail;
import com.dao.conn.Dao;
import com.other.Item;
import com.other.KeyShortcut;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dhd extends JFrame implements KeyShortcut {

	
	private JPanel contentPane;
	private JTextField text_slh;   //收据号
	private JTable table;
	public static JTextField textField_phone;  //联系电话
	public static JTextField textField_name;   //联系人
	public static JTextField textField_date;    //订货日期
	public static JComboBox comboBox_company = new JComboBox();
	public static JComboBox comboBox_product = new JComboBox();
	public static JTextField textField_price;    //商品单价
	public static JTextField textField_long;     //长
	public static JTextField textField_wide;     //宽
	private JLabel label_mj = new JLabel();       
	public JCheckBox checkBox_mb;
	public JRadioButton RadioButton_sb; 
	private static JTextField textField_mbprice;
	private static JTextField textField_perimeter;
	private static JTextField textField_zkprice;
	private JTextField textField_zkcount;
	private JTextField textField_kkprice;
	private JTextField textField_kkcount;
	private JTextField textField_surcharge;
	public JTextField textField_count;   //玻璃数量
	private JTextField textField_remark;
	public JRadioButton RadioButton_lc;
	public JRadioButton radioButton_lk;
	public JRadioButton radioButton_qt;
	public DefaultTableModel tableModel;
	public JButton button_print;
	private JCheckBox checkBox_zk;
	private JCheckBox checkBox_kk;
	private JButton button_clear;
	private JLabel detailId;
	private JLabel label_sum;
	private JLabel label_square;
	private String mb_remark="";

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public dhd() {
		setTitle("订货单");//标题 订货单
		setBounds(50, 0, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		//客户信息
		JPanel panel_kh_info = new JPanel();
		//标题模板
		JPanel panel_title = new JPanel();
		//表模块
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//商品模块
		JPanel panel_pro = new JPanel();
		
		//按钮模板
		JPanel panel_button = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE))
						.addComponent(panel_kh_info, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE)
						.addComponent(panel_title, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 854, Short.MAX_VALUE))
					.addGap(20))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_pro, GroupLayout.PREFERRED_SIZE, 854, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_title, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_kh_info, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_pro, GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_button, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_button.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));

		JButton button_repetition = new JButton("重打");
		button_repetition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		button_print = new JButton("打印");
		JButton button_new = new JButton("新建");
		JButton button_save = new JButton("保存");
		button_clear = new JButton("清空");
		JButton button_exit = new JButton("退出");
		 
		//按钮面板
		panel_button.add(button_repetition);
		panel_button.add(button_print);
		panel_button.add(button_new);
		panel_button.add(button_save);
		panel_button.add(button_clear);
		panel_button.add(button_exit);
		
		
		
		/******商品模块****/
		
		JLabel label_product = new JLabel("商品名称");
		
		JLabel label_price = new JLabel("单价：");
		textField_price = new JTextField();
		textField_price.setColumns(10);
		
		JLabel label_long = new JLabel("长：");
		textField_long = new JTextField();
		textField_long.setColumns(10);
		
		JLabel label_wide = new JLabel("宽：");
		textField_wide = new JTextField();
		textField_wide.setColumns(10);
		
		checkBox_mb = new JCheckBox("是否磨边");
		checkBox_mb.setSelected(false);
		
		ButtonGroup group = new ButtonGroup();
		RadioButton_sb = new JRadioButton("四边");
		RadioButton_sb.setEnabled(false);
		
		
		RadioButton_lc = new JRadioButton("二长");
		RadioButton_lc.setEnabled(false);
		
		
		radioButton_lk = new JRadioButton("二宽");
		radioButton_lk.setEnabled(false);
		
		radioButton_qt = new JRadioButton("其他");
		radioButton_qt.setEnabled(false);
		
		group.add(RadioButton_sb);
		group.add(RadioButton_lc);
		group.add(radioButton_lk);
		group.add(radioButton_qt);
		
		
		//磨边单价
		textField_mbprice = new JTextField();
		textField_mbprice.setColumns(10);
		textField_mbprice.setEditable(false);
		
		textField_perimeter = new JTextField();
		textField_perimeter.setColumns(10);
		textField_perimeter.setEditable(false);
		
		JLabel label_mbprice = new JLabel("单价");
		
		JLabel label_perimeter = new JLabel("周长");
		
		
		//钻孔
		checkBox_zk = new JCheckBox("钻孔");
		checkBox_zk.setSelected(false);
		
		JLabel label_zkprice = new JLabel("单价");
		textField_zkprice = new JTextField("0.00");
		textField_zkprice.setColumns(6);
		textField_zkprice.setEditable(false);
		
		JLabel label_zkcount = new JLabel("数量");
		textField_zkcount = new JTextField("0.00");
		textField_zkcount.setColumns(10);
		textField_zkcount.setEditable(false);
		
		JLabel label_8 = new JLabel("个");
		
		checkBox_kk = new JCheckBox("开口");
		
		JLabel label_kkprice = new JLabel("单价");
		
		textField_kkprice = new JTextField("0.00");
		textField_kkprice.setColumns(10);
		textField_kkprice.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("数量");
		
		
		textField_kkcount = new JTextField("0.00");
		textField_kkcount.setColumns(6);
		textField_kkcount.setEditable(false);
		
		JLabel label_11 = new JLabel("个");
		
		JLabel label_12 = new JLabel("附件费用");
		
		textField_surcharge = new JTextField("");
		textField_surcharge.setColumns(10);
		
		JCheckBox checkBox_ = new JCheckBox("标签");
		
		JLabel label_count = new JLabel("数量");
		
		textField_count = new JTextField("1");
		textField_count.setColumns(10);
		
		JLabel label_1 = new JLabel("备注");
		
		textField_remark = new JTextField();
		textField_remark.setColumns(10);
		
		detailId = new JLabel("明细序号");
		//detailId.s
		
		label_sum = new JLabel("金额");
		label_sum.setEnabled(false);
		
		label_square = new JLabel("");
		
		
		
		
		
		
		GroupLayout gl_panel_pro = new GroupLayout(panel_pro);
		gl_panel_pro.setHorizontalGroup(
			gl_panel_pro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_pro.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_pro.createSequentialGroup()
							.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_panel_pro.createSequentialGroup()
										.addComponent(checkBox_mb)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(RadioButton_sb)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(RadioButton_lc)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(radioButton_lk)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(radioButton_qt))
									.addGroup(gl_panel_pro.createSequentialGroup()
										.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_panel_pro.createSequentialGroup()
													.addComponent(checkBox_zk)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(label_zkprice)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(textField_zkprice, 0, 0, Short.MAX_VALUE))
												.addGroup(gl_panel_pro.createSequentialGroup()
													.addComponent(checkBox_kk)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(label_kkprice)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(textField_kkprice, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
											.addGroup(gl_panel_pro.createSequentialGroup()
												.addComponent(label_12)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textField_surcharge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addGap(24)
										.addGroup(gl_panel_pro.createParallelGroup(Alignment.TRAILING)
											.addComponent(label_zkcount)
											.addComponent(lblNewLabel))
										.addGap(18)
										.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel_pro.createSequentialGroup()
												.addGap(10)
												.addComponent(textField_kkcount, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
											.addComponent(textField_zkcount, 0, 0, Short.MAX_VALUE))))
								.addGroup(gl_panel_pro.createSequentialGroup()
									.addComponent(label_product)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox_product, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(detailId)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_pro.createSequentialGroup()
									.addComponent(label_long)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_long, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_11)
								.addComponent(label_8)
								.addGroup(gl_panel_pro.createSequentialGroup()
									.addComponent(label_mbprice)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_mbprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_pro.createSequentialGroup()
									.addComponent(label_wide)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_wide, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_price)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_count)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(31)
									.addComponent(label_mj)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_square)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(label_sum)
									.addGap(18))
								.addGroup(gl_panel_pro.createSequentialGroup()
									.addComponent(label_perimeter)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_perimeter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(352, Short.MAX_VALUE))))
						.addGroup(gl_panel_pro.createSequentialGroup()
							.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_pro.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField_remark, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE))
								.addComponent(checkBox_))
							.addContainerGap(168, Short.MAX_VALUE))))
		);
		gl_panel_pro.setVerticalGroup(
			gl_panel_pro.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_pro.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_pro.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_product)
						.addComponent(label_long)
						.addComponent(textField_long, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_wide)
						.addComponent(textField_wide, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_price)
						.addComponent(textField_price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_count)
						.addComponent(textField_count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_product, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_mj)
						.addComponent(detailId)
						.addComponent(label_sum)
						.addComponent(label_square))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_pro.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_mb)
						.addComponent(RadioButton_sb)
						.addComponent(RadioButton_lc)
						.addComponent(radioButton_lk)
						.addComponent(radioButton_qt)
						.addComponent(label_mbprice)
						.addComponent(textField_mbprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_perimeter)
						.addComponent(textField_perimeter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_pro.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_zk)
						.addComponent(label_zkprice)
						.addComponent(textField_zkprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_zkcount)
						.addComponent(textField_zkcount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_8))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_pro.createParallelGroup(Alignment.BASELINE)
						.addComponent(checkBox_kk)
						.addComponent(label_kkprice)
						.addComponent(textField_kkprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_kkcount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_11)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_pro.createParallelGroup(Alignment.LEADING)
						.addComponent(label_12)
						.addComponent(textField_surcharge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBox_)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_pro.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_remark, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(31, Short.MAX_VALUE))
		);
		panel_pro.setLayout(gl_panel_pro);
		//表
		tableModel= new DefaultTableModel(
				new Object[][] {
					
				},
				new String[] {
					"单号", "产品名称", "规格", "单位", "单价", "数量", "面积", "金额",
					"磨边单价", "磨边周长", "附件费", "备注", "总金额","序号"
				}
			);
		table = new JTable(tableModel);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(7).setPreferredWidth(85);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);
		table.getColumnModel().getColumn(13).setMaxWidth(0);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//单元格渲染器  
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		scrollPane.setViewportView(table);
		
		
		
		JLabel label_title = new JLabel("环华订货单");
		label_title.setFont(new Font("宋体", Font.BOLD, 17));
		panel_title.add(label_title);
		
		JLabel Label_lsh = new JLabel("单号：");
		
		text_slh = new JTextField();
		text_slh.setEditable(false);
		text_slh.setColumns(10);
		
		JLabel label_company = new JLabel("公司名称");
		
		JLabel label_phone = new JLabel("联系电话");
		
		JLabel label_name = new JLabel("联系人");
		
		JLabel label_date = new JLabel("订货日期");
		
		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		
		textField_name = new JTextField();
		textField_name.setColumns(10);
		
		textField_date = new JTextField();
		textField_date.setColumns(12);
		//获取当前时间
		textField_date.setText(datetime());
		
		initKehuField();   //初始化客户信息
		initSPField();     //初始化商品信息
		comboBox_company.setEditable(true);
		comboBox_company.setSelectedIndex(-1);
		comboBox_company.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				doselectaction();
			}
		});
		
		comboBox_product.setEditable(true);
	    comboBox_product.setSelectedIndex(-1);
	    comboBox_product.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				dospselectaction();
			}
		});
		
		
		GroupLayout gl_panel_kh_info = new GroupLayout(panel_kh_info);
		gl_panel_kh_info.setHorizontalGroup(
			gl_panel_kh_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_kh_info.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_kh_info.createParallelGroup(Alignment.LEADING, false)
						.addComponent(Label_lsh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_company, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_kh_info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_kh_info.createSequentialGroup()
							.addComponent(comboBox_company, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_phone)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(59)
							.addComponent(label_name)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(label_date)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(text_slh, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addGap(65))
		);
		gl_panel_kh_info.setVerticalGroup(
			gl_panel_kh_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_kh_info.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_kh_info.createParallelGroup(Alignment.BASELINE)
						.addComponent(Label_lsh)
						.addComponent(text_slh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_kh_info.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_kh_info.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_company)
							.addComponent(label_phone)
							.addComponent(label_name)
							.addComponent(label_date)
							.addComponent(textField_phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBox_company, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_kh_info.setLayout(gl_panel_kh_info);
		contentPane.setLayout(gl_contentPane);
		
	    lshmaxid();
		
	
		
		
	}

	/****初始化信息star******/
	
	private void initKehuField() {// 初始化客户信息
		List getkhInfos = Dao.getKhInfos();
		for (Iterator iter = getkhInfos.iterator(); iter.hasNext();) {
			List list = (List) iter.next();
			Item item = new Item();
			item.setId(list.get(0).toString().trim());
			item.setName(list.get(1).toString().trim());
			comboBox_company.addItem(item);
		
		}
	}
	// 初始化商品信息
	private void initSPField() {
		List getspInfos = Dao.getspInfos();
		for (Iterator iter = getspInfos.iterator(); iter.hasNext();) {
			List list = (List) iter.next();
			Item item = new Item();
			//item.setId(list.get(0).toString().trim());
			item.setName(list.get(1).toString().trim());
			comboBox_product.addItem(item);
			
		
		}
	}
	//加载客户信息
	/*************初始化信息end****************/
	public void doselectaction(){
		Item item=(Item)comboBox_company.getSelectedItem();
		Company company = Dao.getkhInfo(item);
		textField_name.setText(company.getCompanyName());
		textField_phone.setText(company.getCompanyPhone());
	}
	//加载商品信息
	public void dospselectaction(){
		Item item=(Item)comboBox_product.getSelectedItem();
		Goods Goods = Dao.getspInfo(item);
		textField_price.setText(Goods.getGoodsPrice());
		textField_long.setText("1000");
		textField_wide.setText("1000");
	
	}
	/**下拉框-文本框快捷键enter**/
	public static void key(JComboBox jcb,JTextField tf){
		Component[] components = jcb.getComponents();
		  for (int i = 0; i < components.length; i++) {
		   if (components[i] instanceof JComponent) {
		//
				components[i].addKeyListener(new KeyAdapter() {	
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
							if(e.getKeyCode()==KeyEvent.VK_ENTER){
								   tf.requestFocus();
							}
						
					}
				});
		   }
		  }
	
	}
	//文本框->下拉框
	public void text_jcomboxkey(JComboBox jcb,JTextField tf){
		tf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==tf){
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						jcb.requestFocus();
						
					}
				}
			}
		});
		
	}
	//文本框-文本框 快捷键enter
	public static void textf_key(JTextField f,JTextField t){
		f.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==f){
					if(e.getKeyCode()==KeyEvent.VK_ENTER){
						t.requestFocus();
						t.selectAll();  //全选内容
					}
				}
				
			}
		});
		
	}
	//单选快捷键 1/0
	public static void JCheckBoxKey(JCheckBox jcb){
		jcb.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==jcb){
					if(e.getKeyCode()==KeyEvent.VK_NUMPAD1){
						jcb.setSelected(true);
					}else if(e.getKeyCode()==KeyEvent.VK_NUMPAD0){
						jcb.setSelected(false);
					}
					
					
				}
			}
		});
		
	}
	//求平方
	public void keypress(DefaultTableModel tableModel1){
		textField_count.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==textField_count){
					if(e.getKeyCode()== KeyEvent.VK_ENTER){
						getSquare();
						checkBox_mb.requestFocus();
					
						
					}
				}
			}
		});
	}
	//求平方
	public void getSquare(){
		double sum=Double.valueOf(textField_long.getText())*0.001*Double.valueOf(textField_wide.getText())*0.001*Double.valueOf(textField_count.getText());
        label_mj.setForeground(Color.red);
		label_mj.setText(""+sum+"");
		label_square.setText("㎡");
		label_square.setForeground(Color.red);
		getmoney();
	}
	//求原材料的金额
	public void getmoney(){
		double sum = Double.valueOf(label_mj.getText())*Double.valueOf(textField_count.getText())*Double.valueOf(textField_price.getText());
		label_sum.setText(""+sum+"");
	}
	//勾选磨边
	public void isselect(JCheckBox jcb){
		ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Object obj=e.getItem();
				if (obj.equals(jcb)){
					if(jcb.isSelected()){
						textField_mbprice.setEditable(true);
						RadioButton_sb.setEnabled(true);
						RadioButton_lc.setEnabled(true);
						radioButton_lk.setEnabled(true);
						radioButton_qt.setEnabled(true);
						
						GetPerimeterFuntion();
						
						
					}
					else{
						textField_mbprice.setEditable(false);
						RadioButton_sb.setEnabled(false);
						RadioButton_lc.setEnabled(false);
						radioButton_lk.setEnabled(false);
						radioButton_qt.setEnabled(false);
						textField_perimeter.setEditable(false);
						textField_perimeter.setText("0.00");
						
					}
				}
				
			}
		};
		jcb.addItemListener(itemlistener);
		
	}
	//勾选钻孔
	public void zkisselect(){
            ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Object obj=e.getItem();
				if (obj.equals(checkBox_zk)){
					if(checkBox_zk.isSelected()){
						textField_zkprice.setEditable(true);
						textField_zkcount.setEditable(true);
					}
					else{
						textField_zkprice.setEditable(false);
						textField_zkcount.setEditable(false);
						textField_zkprice.setText("0.00");
						textField_zkcount.setText("0.00");
					}
				}
				
			}
		};
		checkBox_zk.addItemListener(itemlistener);
		
	}
	//勾选开口
	public void kkisselect(){
            ItemListener itemlistener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				Object obj=e.getItem();
				if (obj.equals(checkBox_kk)){
					if(checkBox_kk.isSelected()){
						textField_kkprice.setEditable(true);
						textField_kkcount.setEditable(true);
					}
					else{
						textField_kkprice.setEditable(false);
						textField_kkcount.setEditable(false);
						textField_kkprice.setText("0.00");
						textField_kkcount.setText("0.00");
					}
				}
				
			}
		};
		checkBox_kk.addItemListener(itemlistener);
	}
    //求周长
	public void GetPerimeter(){
		ActionListener action = new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GetPerimeterFuntion();				
			}
		};
		RadioButton_sb.addActionListener(action);
		RadioButton_lc.addActionListener(action);
		radioButton_lk.addActionListener(action);
		radioButton_qt.addActionListener(action);
		
	}
	//周长公式
	public void GetPerimeterFuntion(){
		double l=0;
		if(RadioButton_sb.isSelected()){
			l=(Integer.parseInt(textField_long.getText())+Integer.parseInt(textField_wide.getText()))*2*0.001;
			textField_perimeter.setEditable(false);
			textField_perimeter.setText(String.valueOf(l));
		}else if(RadioButton_lc.isSelected()){
			
			l=(Integer.parseInt(textField_long.getText()))*2*0.001;
			textField_perimeter.setEditable(false);
			textField_perimeter.setText(String.valueOf(l));	
		}else if (radioButton_lk.isSelected()){
			l=(Integer.parseInt(textField_wide.getText()))*2*0.001;
			textField_perimeter.setEditable(false);
			textField_perimeter.setText(String.valueOf(l));
			
		}else if (radioButton_qt.isSelected()){
			textField_perimeter.setEditable(true);
			
		}
	}
	//添加表数据
 	public void addTable(){
    	String[] rowValues={(String)text_slh.getText(),(String) comboBox_product.getSelectedItem().toString(),(String)textField_long.getText()+"*"+(String)textField_wide.getText(),"㎡",(String)textField_price.getText(),(String)textField_count.getText()
    			,(String)label_mj.getText(),(String)label_sum.getText(),(String)textField_mbprice.getText(),(String)textField_perimeter.getText(),
    			(String)textField_surcharge.getText(),"",(String)textField_remark.getText(),(String)detailId.getText()};
    	 tableModel.addRow(rowValues);
    	
    }
 	//删除表数据
	public void deleTable(){
		
		int row = table.getSelectedRow();
		tableModel.removeRow(row);
	}
 	//获取流水号
	public void lshmaxid(){
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		String lsh = Dao.getSellMainMaxId(date);
		text_slh.setText(lsh);
		
		
	}
	//获取明细Id
	public void detailId(){
		String Detailid=Dao.getTDedailMaxid();
		detailId.setText(Detailid);
		
	}
	//获取当前日期
	public String datetime(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
		String Time = dateFormat.format( now );
		System.out.print(Time);
		return Time;
		
	}
	//打印
	public void print(JButton button){
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
			    	JasperDesign jasperDesign=JRXmlLoader.load("report3.jrxml");
			    	//System.out.print("12");
			    	JasperReport jasperReport=JasperCompileManager.compileReport(jasperDesign);
			    	HashMap p=new HashMap();
			    	p.put("name", "郑泽游");
			    	JRResultSetDataSource rsd= new JRResultSetDataSource(Dao.query("select * from temp_sale_detail where zblsh='"+(String)text_slh.getText()+"'"));
			    	JasperPrint  jasperprint=JasperFillManager.fillReport(jasperReport, p,rsd);
			    	JasperViewer  jr=new JasperViewer (jasperprint,false);
			    	jr.setVisible(true);
			    	jr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
			    	clearGoods();
			
				} catch (JRException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		});
		
	}
	//删除
	public void Clear_Button(){
		button_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleTable();
			}
		});
	}
	//添加表数据
    public void New_Tabel(){
    	textField_remark.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource()==textField_remark){
					if (e.getKeyCode()==KeyEvent.VK_ENTER){
						addTable();
						InsertGoods();
						detailId();
						
						}
					
					
				}
			}
		});
    }
    //插入商品信息
    public void InsertGoods(){
    	String zblsh=text_slh.getText();
    	//String idm=
    	String dname=comboBox_product.getSelectedItem().toString();
    	//String dw = 
    	double dj = Double.valueOf( textField_price.getText());
    	double chang =Double.valueOf(textField_long.getText());
    	double kuang = Double.valueOf(textField_wide.getText());
    	String gg =textField_long.getText() + "*" + textField_wide.getText();
    	double sl = Double.valueOf(textField_count.getText());
    	String mblx = "9";
    	if (RadioButton_sb.isSelected()){
    		    mblx="0";
    	}else if (RadioButton_lc.isSelected()){
    		mblx="1";	
    	}else if (radioButton_lk.isSelected()){
    		mblx="2";
    	}else if (radioButton_qt.isSelected()){
    		mblx="3";
    	}
    	double mbdj = Double.valueOf(textField_mbprice.getText());
    	double mbzc = Double.valueOf(textField_perimeter.getText());
    	String zklx = "9";
    	double zkdj = Double.valueOf(textField_zkprice.getText());
    	double zksl = Double.valueOf(textField_zkcount.getText());
    	String kklx = "9";
    	double kkdj = Double.valueOf(textField_kkprice.getText());
    	double kksl = Double.valueOf(textField_kkcount.getText());
    	//String zje  = 
    	String bz  =textField_remark.getText();
    	TempSaleDetail tsaledetail= new TempSaleDetail(zblsh,"0",dname,"111",dj,chang,kuang,gg,sl,mblx,
    			mbdj,mbzc,zklx,zkdj,zksl,kklx,kkdj,kksl,2.0,bz); 
    	boolean rs = Dao.addSaleDetail(tsaledetail);
    	 
    	
    }
    //插入主表
    public void InsertMGoods(){
    	String zblsh=text_slh.getText();
    	String sgs=comboBox_company.getSelectedItem().toString();
    	String name=textField_name.getText();
    	String phone=textField_phone.getText();
    }
    //清空商品信息
    public void clearGoods(){
    	datetime();
    	lshmaxid();
    	detailId();
    	comboBox_company.setSelectedIndex(-1);
    	comboBox_product.setSelectedIndex(-1);
    	
    }
}
