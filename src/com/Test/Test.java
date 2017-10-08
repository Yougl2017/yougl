package com.Test;

import com.ui.dhd;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        dhd Dhd=new dhd();
        Dhd.setVisible(true);
        Dhd.key(Dhd.comboBox_company, Dhd.textField_phone);
        Dhd.textf_key(Dhd.textField_phone, Dhd.textField_name);
        Dhd.textf_key(Dhd.textField_name, Dhd.textField_date);
        Dhd.key(Dhd.comboBox_product, Dhd.textField_long);
        Dhd.textf_key(Dhd.textField_long, dhd.textField_wide);
        Dhd.textf_key(Dhd.textField_wide, Dhd.textField_price);
        Dhd.textf_key(Dhd.textField_price, Dhd.textField_count);
        Dhd.keypress(Dhd.tableModel);
        Dhd.isselect(Dhd.checkBox_mb);
        Dhd.text_jcomboxkey(Dhd.comboBox_product, Dhd.textField_date);
        Dhd.JCheckBoxKey(Dhd.checkBox_mb);
        Dhd.print(Dhd.button_print);
        Dhd.GetPerimeter();
        Dhd.zkisselect();
        Dhd.kkisselect();
        Dhd.New_Tabel();
        Dhd.Clear_Button();
        Dhd.detailId();
	}
	

}
