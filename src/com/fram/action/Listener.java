package com.fram.action;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Listener {
    public void addTable(DefaultTableModel tableModel,JTextField dh,JComboBox name,JTextField l
    		,JTextField g,String dw,JTextField  dj,String je,JTextField mbzc,JTextField mbdj,String fjf,JTextField remark
    		,String zje 
    		){
    	//tableModel=new DefaultTableModel();
    	String[] rowValues={(String)dh.getText(),(String)name.getSelectedItem(),(String)l.getText()+"*"+(String)g.getText(),"平方",(String)dj.getText()
    			,je,(String)mbzc.getText(),(String)mbdj.getText(),fjf,(String)remark.getText()};
    	 tableModel.addRow(rowValues);
    	
    }
}
