package envelope;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import abaCriar.CriarEnvelope;
import abaGerar.GerarChaves;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class Janela {
	static JFrame janela;
	static JTabbedPane painelOpcoes;
	static JPanel painelCriaEnv, painelGeraChv;

	public static void criaJanela() {
		janela = new JFrame();


		//------------------------Janela---------------------//
		janela.setTitle("env-dig-sys");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(800, 600);

		janela.getContentPane().setLayout(new GridBagLayout());
		janela.getContentPane().setBackground(Color.PINK);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;


			//-------------------Painel de Opcoes---------------//
			painelOpcoes = new JTabbedPane();
			painelOpcoes.setBackground(Color.RED);
			//-------------------Painel de Opcoes---------------//


				//-------------------Painel Gerar Chaves-------------------//
				painelGeraChv = GerarChaves.criarPainel();
				//-------------------Painel Gerar Chaves-------------------//


				//------------------Painel Criar Envelope------------------//
				painelCriaEnv = CriarEnvelope.criarPainel();
				//------------------Painel Criar Envelope------------------//

				//------------------Painel Abrir Envelope------------------//
				//painelAbreEnv = AbrirEnvelope.criarPainel();
				//------------------Painel Abrir Envelope------------------//


				// Adiciona os paineis de funcionalidades ao painel principal
				painelOpcoes.addTab("Gerar chaves", painelGeraChv);
				painelOpcoes.addTab("Criar envelope", painelCriaEnv);
				//painelOpcoes.addTab("Abrir envelope", painelAbreEnv);


			// Adiciona o Painel de Opcoes no frame principal
			janela.add(painelOpcoes, c);
			janela.setVisible(true);
		//------------------------Janela---------------------//
	}
}