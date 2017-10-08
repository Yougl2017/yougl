package com.other;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

public interface KeyShortcut {
	
	
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
					}
				}
				
			}
		});
		
	}

}
