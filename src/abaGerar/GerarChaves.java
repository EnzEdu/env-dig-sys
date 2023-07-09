package abaGerar;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GerarChaves {
	static JPanel painelGeraChv, painelResultados;

	public static JPanel criarPainel() {

		//------------------Gerar Par de Chaves RSA------------------//
		painelGeraChv = new JPanel();
		painelGeraChv.setLayout(new BoxLayout(painelGeraChv, BoxLayout.PAGE_AXIS));
		painelGeraChv.setBackground(Color.WHITE);
		painelGeraChv.setSize(50, 50);
		painelGeraChv.setVisible(true);


			//----------------------Chave Publica--------------------//
			JPanel painelArqChavePubl = new JPanel();
			painelArqChavePubl.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));

				// Label do arquivo com a chave
				JLabel labelArqChavePubl = new JLabel("Nome do Arquivo com a Chave RSA Publica: ");

				// Subpainel do arquivo de saida com a chave
				JPanel subpainelArqChavePubl = new JPanel();

					// Area com o nome do arquivo
					JTextArea nomeArqChavePubl = new JTextArea(0, 0);
					nomeArqChavePubl.setBounds(120,0,200,20);
					nomeArqChavePubl.setLineWrap(true);
					nomeArqChavePubl.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Preenche o subpainel com os componentes
					subpainelArqChavePubl.add(nomeArqChavePubl);


				// Preenche o painel principal
				painelArqChavePubl.add(labelArqChavePubl);
				painelArqChavePubl.add(subpainelArqChavePubl);
			//----------------------Chave Publica--------------------//



			//----------------------Chave Privada--------------------//
			JPanel painelArqChavePriv = new JPanel();
			painelArqChavePriv.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));

				// Label do arquivo com a chave
				JLabel labelArqChavePriv = new JLabel("Nome do Arquivo com a Chave RSA Privada: ");

				// Subpainel do arquivo de saida com a chave
				JPanel subpainelArqChavePriv = new JPanel();

					// Area com o nome do arquivo
					JTextArea nomeArqChavePriv = new JTextArea(0, 0);
					nomeArqChavePriv.setBounds(120,0,200,20);
					nomeArqChavePriv.setLineWrap(true);
					nomeArqChavePriv.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Preenche o subpainel com os componentes
					subpainelArqChavePriv.add(nomeArqChavePriv);


				// Preenche o painel principal
				painelArqChavePriv.add(labelArqChavePriv);
				painelArqChavePriv.add(subpainelArqChavePriv);
			//----------------------Chave Privada--------------------//



			//---------------------Gerar Arquivos--------------------//
			JPanel painelBotaoGeraArqsRSA = new JPanel();
			painelBotaoGeraArqsRSA.setBorder(BorderFactory.createEmptyBorder(44, 10, 0, 10));


				// Botao de confirmacao
				JButton botaoGeraArqsRSA = new JButton("Gerar");
				botaoGeraArqsRSA.addActionListener(e -> {
						String retorno = ListenerGerarChv.clicouBotaoGerar
								(nomeArqChavePubl.getText(), 
								 nomeArqChavePriv.getText());
						mostrarResultado(retorno);
				});


				// Preenche o painel principal
				painelBotaoGeraArqsRSA.add(botaoGeraArqsRSA);
			//-----------------------Gerar Arquivos----------------------//



			//--------------------Mostrar Resultados---------------------//
			painelResultados = new JPanel();
			painelResultados.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 10));
			//--------------------Mostrar Resultados---------------------//



			// Adiciona os paineis no Painel de Geracao de Chaves
			painelGeraChv.add(painelArqChavePubl);
			painelGeraChv.add(painelArqChavePriv);
			painelGeraChv.add(painelBotaoGeraArqsRSA);
			painelGeraChv.add(painelResultados);
		//------------------Gerar Par de Chaves RSA------------------//
		return painelGeraChv;
	}



	public static void mostrarResultado(String result) {

		// Remove o Painel de Resultados da janela
		painelGeraChv.setVisible(false);
		painelGeraChv.remove(painelResultados);

		// Label com a mensagem de resultado da criacao dos arquivos
		JLabel labelResult = new JLabel(result.substring(4));

		// Tratamento da cor da mensagem com base no primeiro caractere
		int tipoRetorno = Character.getNumericValue(result.charAt(0));
		switch (tipoRetorno)
		{
			case 0:	// Operacao sucedida
			{
				labelResult.setForeground(Color.GREEN);
				break;
			}

			case 1:	// Erro nos arquivos
			{
				labelResult.setForeground(Color.RED);
				break;
			}

			case 2: // Erro nos algoritmos
			{
				labelResult.setForeground(Color.ORANGE);
				break;
			}

			default:
			{
				labelResult.setForeground(Color.BLACK);
				break;
			}
		}

		// Recria o Painel portando a mensagem de resultado
		painelResultados = new JPanel();
		painelResultados.setBorder(BorderFactory.createEmptyBorder(2, 10, 0, 10));
		painelResultados.add(labelResult);

		// Readiciona o Painel de Resultados na janela
		painelGeraChv.add(painelResultados);
		painelGeraChv.setVisible(true);
	}
}