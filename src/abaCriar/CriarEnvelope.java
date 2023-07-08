package abaCriar;

import funcionalidades.GerenciadorArquivos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CriarEnvelope {
	static JPanel painelCriaEnv, painelResultados;
	private static byte[] texto, chaveRSA;
	private static String algoritmo;


	public static JPanel criarPainel() {

		//------------------Criar Envelope------------------//
		painelCriaEnv = new JPanel();
		painelCriaEnv.setLayout(new BoxLayout(painelCriaEnv, BoxLayout.PAGE_AXIS));
		painelCriaEnv.setBackground(Color.WHITE);
		painelCriaEnv.setSize(50, 50);
		painelCriaEnv.setVisible(true);


			//---------------------Mensagem em claro---------------------//
			JPanel painelArqTextoEmClaro = new JPanel();

				// Label do arquivo de texto
				JLabel labelArqTextoEmClaro = new JLabel("                             "
						+ "Arquivo de texto:");

				// Subpainel com a coleta do arquivo de texto
				JPanel subpainelArqTextoEmClaro = new JPanel();

					// Area com o nome do arquivo
					JTextArea nomeArqTextoEmClaro = new JTextArea(0, 0);
					nomeArqTextoEmClaro.setBounds(120,0,200,20);
					nomeArqTextoEmClaro.setLineWrap(true);
					nomeArqTextoEmClaro.setEditable(false);
					nomeArqTextoEmClaro.setBackground(new Color(210, 210, 210));
					nomeArqTextoEmClaro.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Escolha do arquivo com a mensagem
					JButton botaoArqTextoEmClaro = new JButton("Procurar...");
					botaoArqTextoEmClaro.setSize(120, 30);
					botaoArqTextoEmClaro.setVisible(true);
					botaoArqTextoEmClaro.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							fc.setCurrentDirectory(new File("."));

							int i = fc.showOpenDialog(subpainelArqTextoEmClaro);
							if (i == JFileChooser.APPROVE_OPTION)
							{
								texto = GerenciadorArquivos.lerArq(fc.getSelectedFile());

								String path = fc.getSelectedFile().getPath();
								nomeArqTextoEmClaro.setText(path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1, path.length()));
							}
						}
					});

					// Preenche o subpainel com os componentes
					subpainelArqTextoEmClaro.add(botaoArqTextoEmClaro);
					subpainelArqTextoEmClaro.add(nomeArqTextoEmClaro);


				// Preenche o painel principal
				painelArqTextoEmClaro.add(labelArqTextoEmClaro);
				painelArqTextoEmClaro.add(subpainelArqTextoEmClaro);
			//---------------------Mensagem em claro---------------------//



			//------------------Chave RSA Destinatario-------------------//
			JPanel painelArqChaveRSAdest = new JPanel();

				// Label do arquivo com a chave publica
				JLabel labelArqChaveRSAdest = new JLabel("Chave Publica RSA (Destinatario):");

				// Subpainel com a coleta do arquivo com a chave
				JPanel subpainelArqChaveRSAdest = new JPanel();

					// Area com o nome do arquivo
					JTextArea nomeArqChaveRSAdest = new JTextArea(0, 0);
					nomeArqChaveRSAdest.setBounds(120,0,200,20);
					nomeArqChaveRSAdest.setLineWrap(true);
					nomeArqChaveRSAdest.setEditable(false);
					nomeArqChaveRSAdest.setBackground(new Color(210, 210, 210));
					nomeArqChaveRSAdest.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Escolha do arquivo com a chave publica RSA do destinatario
					JButton botaoArqChaveRSAdest = new JButton("Procurar...");
					botaoArqChaveRSAdest.setSize(120, 30);
					botaoArqChaveRSAdest.setVisible(true);
					botaoArqChaveRSAdest.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							fc.setCurrentDirectory(new File("."));

							int i = fc.showOpenDialog(subpainelArqChaveRSAdest);
							if (i == JFileChooser.APPROVE_OPTION)
							{
								chaveRSA = GerenciadorArquivos.lerArq(fc.getSelectedFile());

								String path = fc.getSelectedFile().getPath();
								nomeArqChaveRSAdest.setText(path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1, path.length()));
							}
						}
					});

					// Preenche o subpainel com os componentes
					subpainelArqChaveRSAdest.add(botaoArqChaveRSAdest);
					subpainelArqChaveRSAdest.add(nomeArqChaveRSAdest);


				// Preenche o painel principal
				painelArqChaveRSAdest.add(labelArqChaveRSAdest);
				painelArqChaveRSAdest.add(subpainelArqChaveRSAdest);
			//------------------Chave RSA Destinatario-------------------//



			//--------------------Escolha do Algoritmo-------------------//
			JPanel painelEscolhaAlg = new JPanel();

			// Escolha de um algoritmo simetrico para o envelope
			JLabel labelEscolhaAlg = new JLabel("Algoritmo simetrico:");
			String opcoesAlgSimetricos[] = {"-", "AES", "DES", "RC4"};

			JComboBox<String> caixaEscolhaAlg = new JComboBox<>(opcoesAlgSimetricos);
			caixaEscolhaAlg.setEditable(false);
			caixaEscolhaAlg.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					algoritmo = caixaEscolhaAlg.getSelectedItem().toString();
				}
			});

			// Preenche o painel principal
			painelEscolhaAlg.add(labelEscolhaAlg);
			painelEscolhaAlg.add(caixaEscolhaAlg);
			//--------------------Escolha do Algoritmo-------------------//



			//-----------------------Gerar Arquivos----------------------//
			JPanel painelGeracao = new JPanel();
			painelGeracao.setLayout(new BoxLayout(painelGeracao, BoxLayout.PAGE_AXIS));
			painelGeracao.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10));


				// Subpainel do arquivo de saida com a chave assinada
				JPanel subpainelArqChaveAss = new JPanel();

					// Label do arquivo de chave assinada
					JLabel labelArqChaveAss = new JLabel("Nome do Arquivo de Chave Assinada: ");

					// Nome do arquivo
					JTextArea nomeArqChaveAss = new JTextArea(0, 0);
					nomeArqChaveAss.setBounds(120,0,200,20);
					nomeArqChaveAss.setLineWrap(true);
					nomeArqChaveAss.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Preenche o subpainel com os componentes
					subpainelArqChaveAss.add(labelArqChaveAss);
					subpainelArqChaveAss.add(nomeArqChaveAss);


				// Subpainel do arquivo de saida com o texto criptografado
				JPanel subpainelArqTextoCript = new JPanel();

					// Label do arquivo de texto
					JLabel labelArqTextoCript = new JLabel("         Nome do Arquivo Criptografado: ");

					// Nome do arquivo
					JTextArea nomeArqTextoCript = new JTextArea(0, 0);
					nomeArqTextoCript.setBounds(120,0,200,20);
					nomeArqTextoCript.setLineWrap(true);
					nomeArqTextoCript.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Preenche o subpainel com os componentes
					subpainelArqTextoCript.add(labelArqTextoCript);
					subpainelArqTextoCript.add(nomeArqTextoCript);


				// Subpainel do botao de confirmacao
				JPanel subpainelBotaoGerar = new JPanel();

					// Botao de confirmacao
					JButton botaoGerarArqs = new JButton("Gerar");
					botaoGerarArqs.addActionListener(e -> {
						String retorno = ListenerCriarEnv.clicouBotaoGerar(texto, chaveRSA, 
								algoritmo, nomeArqChaveAss.getText(), 
								nomeArqTextoCript.getText());
						mostrarResultado(retorno);
					});


					// Preenche o subpainel com o componente
					subpainelBotaoGerar.add(botaoGerarArqs);


				// Preenche o painel principal
				painelGeracao.add(subpainelArqChaveAss);
				painelGeracao.add(subpainelArqTextoCript);
				painelGeracao.add(subpainelBotaoGerar);
			//-----------------------Gerar Arquivos----------------------//



			//--------------------Mostrar Resultados---------------------//
			painelResultados = new JPanel();
			//--------------------Mostrar Resultados---------------------//



			// Adiciona os paineis no Painel de Criacao de Envelope
			painelCriaEnv.add(painelArqTextoEmClaro);
			painelCriaEnv.add(painelArqChaveRSAdest);
			painelCriaEnv.add(painelEscolhaAlg);
			painelCriaEnv.add(painelGeracao);
			painelCriaEnv.add(painelResultados);
		//------------------Criar Envelope------------------//

		return painelCriaEnv;
	}



	public static void mostrarResultado(String result) {

		// Remove o Painel de Resultados da janela
		painelCriaEnv.setVisible(false);
		painelCriaEnv.remove(painelResultados);

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

			case 1:	// Operacao falhou
			{
				labelResult.setForeground(Color.RED);
				break;
			}

			default:
			{
				labelResult.setForeground(Color.BLUE);
				break;
			}
		}

		// Recria o Painel portando a mensagem de resultado
		painelResultados = new JPanel();
		painelResultados.add(labelResult);

		// Readiciona o Painel de Resultados na janela
		painelCriaEnv.add(painelResultados);
		painelCriaEnv.setVisible(true);
	}

}