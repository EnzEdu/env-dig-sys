package envelope;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

public class Tela {
	
	static JFrame tela;

	public static void criaTela() {
		tela = new JFrame();
		
		//-------------------------Tela---------------------//
		tela.setTitle("env-dig-sys");
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setSize(800, 600);
		
		tela.getContentPane().setLayout(new BorderLayout());
		tela.getContentPane().setBackground(Color.WHITE);
		//-------------------------Tela---------------------//
		
		tela.setVisible(true);
	}
}
