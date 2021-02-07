package experiments;

import java.awt.*; 
import java.awt.event.*;

class PrintTest extends Frame{ 

	PrintTest(String s) { 
	
	super(s);
	setSize(400, 400); 
	setVisible(true); 
	
	}
	
	public void simplePrint() { 
	
		PrintJob pj = getToolkit().getPrintJob(this, "JobTitle", null); 
		
		if (pj != null) {
		
			Graphics pg = pj.getGraphics(); 
			
			if (pg != null) { 
				print(pg); 
				pg.dispose();
			
			} else System.err.println("Graphics's null"); 
			pj.end();
		
		} else System.err.println("Job's null"); 
	} 
	
	public void paint(Graphics g) {
		
		g.setFont(new Font("Serif", Font.ITALIC, 30)); 
		g.setColor(Color.black); 
		g.drawArc(0, 100, 200, 200, 0, 360); 
		g.drawString("Страница 1", 100, 100); 
	} 
	
	public static void main(String[] args) {
	
		PrintTest pt = new PrintTest(" Простой пример печати");
		pt.Conc();
/*		pt.simplePrint();
		pt.addWindowListener(new WindowAdapter(){
	
			public void windowClosing(WindowEvent ev){
	
				System.exit(0); 
			}
		}); 
	*/
		
	} 
	
	void Conc() {
		
		String a = "12345678123412341234123456780000";
		String b;
		
		b = (a.substring(0, 8) + '-' + a.substring(8, 12) + '-' + a.substring(12, 16) + '-' + a.substring(16, 20) + '-' + a.substring(20, 32));
		System.out.println(a);
		System.out.println(b);
	}
}