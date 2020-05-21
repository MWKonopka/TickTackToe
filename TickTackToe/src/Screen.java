import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	boolean check = false;
	JButton button_00 = new JButton("0,0");
	JButton button_01 = new JButton("0,1");
	JButton button_02 = new JButton("0,2");
	JButton button_10 = new JButton("1,0");
	JButton button_11 = new JButton("1,1");
	JButton button_12 = new JButton("1,2");
	JButton button_20 = new JButton("2,0");
	JButton button_21 = new JButton("2,1");
	JButton button_22 = new JButton("2,2");
	
	GridLayout layout = new GridLayout(3,3);
	JPanel panel = new JPanel();

	int r_out,c_out;
	
	void ret_00(){
		r_out = 0;
		c_out = 0;
	}
	
	void ret_01(){
		r_out = 0;
		c_out = 1;
	}
	
	void ret_02(){
		r_out = 0;
		c_out = 2;
	}
	
	void ret_10(){
		r_out = 1;
		c_out = 0;
	}
	
	void ret_11(){
		r_out = 1;
		c_out = 1;
	}
	
	void ret_12(){
		r_out = 1;
		c_out = 2;
	}
	
	void ret_20(){
		r_out = 2;
		c_out = 0;
	}
	
	void ret_21(){
		r_out = 2;
		c_out = 1;
	}
	
	void ret_22(){
		r_out = 2;
		c_out = 2;
	}
	
	class AL_00 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_00();
			
		}
		
	}
	
	class AL_01 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_01();
		}
		
	}
	
	class AL_02 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_02();
		}
		
	}
	
	class AL_10 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_10();
		}
		
	}
	
	class AL_11 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_11();
		}
		
	}
	
	class AL_12 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_12();
		}
		
	}
	
	class AL_20 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_20();
		}
		
	}
	
	class AL_21 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_21();
		}
		
	}
	
	class AL_22 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			check = true;
			ret_22();
		}
		
	}
	
	public Screen() throws HeadlessException {
		
		panel.setLayout(layout);
		
		button_00.addActionListener(new AL_00());
		button_01.addActionListener(new AL_01());
		button_02.addActionListener(new AL_02());
		button_10.addActionListener(new AL_10());
		button_11.addActionListener(new AL_11());
		button_12.addActionListener(new AL_12());
		button_20.addActionListener(new AL_20());
		button_21.addActionListener(new AL_21());
		button_22.addActionListener(new AL_22());
		
		panel.add(button_00);
		panel.add(button_01);
		panel.add(button_02);
		panel.add(button_10);
		panel.add(button_11);
		panel.add(button_12);
		panel.add(button_20);
		panel.add(button_21);
		panel.add(button_22);
		
		this.add(panel);
	}

	public Screen(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Screen(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Screen(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
