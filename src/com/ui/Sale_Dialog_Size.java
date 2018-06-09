package com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class Sale_Dialog_Size extends JDialog {

	private  JScrollPane contentPanel = new JScrollPane();
	private JTable table;
	private DefaultTableModel tablemodel;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public Sale_Dialog_Size(Map<String, String> map,Sale_New sale) {
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 667, 414);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEADING);
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel(map.get("customer"));
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel(map.get("product"));
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel(map.get("mbsl"));
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel(map.get("zksl"));
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel(map.get("kksl"));
				panel.add(lblNewLabel_4);
			}
			{
				JButton button = new JButton("新增行");
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						tablemodel.addRow(addrow(tablemodel));
					}
				});
				panel.add(button);
			}
		}
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			tablemodel= new DefaultTableModel(
					new Object[][] {
						{null, null, null,null}
					},
					new String[] {
								"长","宽","数量","备注"
					}
				);
				
			table = new JTable(tablemodel);
			table.setFillsViewportHeight(true);  //填充高度
			table.setColumnSelectionAllowed(true);
			setFocusMoveHorizontal(table);
			contentPanel.setColumnHeaderView(table);
			contentPanel.setViewportView(table);
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (table.getCellEditor() != null){
							table.getCellEditor().stopCellEditing();
							
						}
						sale.addData(AddData());
						Sale_Dialog_Size.this.dispose();  //关闭窗口
						
					}
				});
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
    //回车跳转至按钮
	public   static   void  setFocusMoveHorizontal(JTable table)  {
        table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).
            put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0 ),  " selectNextColumnCell " ); 
    }
	//新增表行
	public  Vector  addrow(DefaultTableModel tablemodel){
		int r = tablemodel.getRowCount();  //行
		int c = tablemodel.getColumnCount();  //列

		//创建行向量
		Vector newRow = new Vector();

		//循环增加行向量的数据。为什么要循环，且用当前列数为上限？
		//因为循环的目的是使增加的行数和现有的列数能同步，比如现在有2列，想要增加一行，如果不用循环，则在第一列新增一行，用了循环就可以是增加行的时候每一列的对应行数都增加。
		for(int i=0;i<c;i++)   //小于当前列数
		{
		    newRow.add("");  
		}
													
		return newRow;
	}
	//表数据写入数组
	public List AddData(){
		List<String> numdata = new ArrayList<String>();
	    for (int count = 0; count < tablemodel.getRowCount(); count++) {
	        if (tablemodel.getValueAt(count, 0).toString()=="" || tablemodel.getValueAt(count, 1).toString()==""
	        ){
	        	break;
	        }
	      numdata.add(tablemodel.getValueAt(count, 0).toString());
	      numdata.add(tablemodel.getValueAt(count, 1).toString());
	      numdata.add(tablemodel.getValueAt(count, 2).toString());
	      if (tablemodel.getValueAt(count, 3)==(null)){
	    	  tablemodel.setValueAt(" ", count, 3);
	      }
	      numdata.add(tablemodel.getValueAt(count, 3).toString());
	      
	    }
 
	    return numdata;
	    
	}

}
