import javax.swing.ImageIcon;
import javax.swing.JButton;

public class XOField extends JButton{
	ImageIcon X,O;
	public int pos1, pos2;
	
	
	public XOField(int p1, int p2)
	{
	 X = new ImageIcon(this.getClass().getResource("X.png"));
	 O = new ImageIcon(this.getClass().getResource("O.png"));
	 setIcon(null);
	 pos1 = p1;
	 pos2 = p2;
	}
	
	public void changeIcon(char icon)
	{
		if(icon == 'O')
			setIcon(O);
		if(icon == 'X')
			setIcon(X);
	}
}
