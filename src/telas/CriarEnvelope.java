package telas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CriarEnvelope {
	static JButton botaoArq1, botaoArq2;
	static JTextArea ceNomeArqEntr1, ceNomeArqEntr2, ceNomeArqSaida1, ceNomeArqSaida2;
	private static String texto, chaveRSA, algoritmo;


	public static JPanel criarPainel() {
		JPanel painelCriaEnv = new JPanel();


		//------------------Criar Envelope------------------//
		painelCriaEnv = new JPanel();
		painelCriaEnv.setLayout(new BoxLayout(painelCriaEnv, BoxLayout.PAGE_AXIS));
		painelCriaEnv.setBackground(Color.GREEN);
		painelCriaEnv.setSize(50, 50);
		painelCriaEnv.setVisible(true);


			//---------------------Mensagem em claro---------------------//
			JPanel painelArqTextoEmClaro = new JPanel();

				// Label do arquivo de texto
				JLabel labelArqTextoEmClaro = new JLabel("                             Arquivo de texto:");

				// Subpainel com a coleta do arquivo de texto
				JPanel subpainelArqTextoEmClaro = new JPanel();

					// Escolha do arquivo com a mensagem
					botaoArq1 = new JButton("Procurar...");
					botaoArq1.setSize(120, 30);
					botaoArq1.setVisible(true);
					botaoArq1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							int i = fc.showOpenDialog(subpainelArqTextoEmClaro);

							if (i == JFileChooser.APPROVE_OPTION)
							{
								texto = lerConteudo(fc.getSelectedFile());

								String path = fc.getSelectedFile().getPath();
								ceNomeArqEntr1.setText(path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1, path.length()));
							}
						}
					});

					// Nome do arquivo
					ceNomeArqEntr1 = new JTextArea(0, 0);
					ceNomeArqEntr1.setBounds(120,0,200,20);
					ceNomeArqEntr1.setLineWrap(true);
					ceNomeArqEntr1.setEditable(false);
					ceNomeArqEntr1.setBackground(new Color(210, 210, 210));
					ceNomeArqEntr1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Preenche o subpainel com os componentes
					subpainelArqTextoEmClaro.add(botaoArq1);
					subpainelArqTextoEmClaro.add(ceNomeArqEntr1);


				// Preenche o painel principal
				painelArqTextoEmClaro.add(labelArqTextoEmClaro);
				painelArqTextoEmClaro.add(subpainelArqTextoEmClaro);
			//---------------------Mensagem em claro---------------------//



			//------------------Chave RSA Destinatario-------------------//
			JPanel painelArqChaveRSAdest = new JPanel();

				// Label do arquivo com a chave publica
				JLabel labelArqChaveRSAdest = new JLabel("Chave Pública RSA (Destinatário):");

				// Subpainel com a coleta do arquivo com a chave
				JPanel subpainelArqChaveRSAdest = new JPanel();

					// Escolha do arquivo com a chave publica RSA do destinatario
					botaoArq2 = new JButton("Procurar...");
					botaoArq2.setSize(120, 30);
					botaoArq2.setVisible(true);
					botaoArq2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							int i = fc.showOpenDialog(subpainelArqChaveRSAdest);

							if (i == JFileChooser.APPROVE_OPTION)
							{
								chaveRSA = lerConteudo(fc.getSelectedFile());

								String path = fc.getSelectedFile().getPath();
								ceNomeArqEntr2.setText(path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1, path.length()));
							}
						}
					});

					// Nome do arquivo
					ceNomeArqEntr2 = new JTextArea(0, 0);
					ceNomeArqEntr2.setBounds(120,0,200,20);
					ceNomeArqEntr2.setLineWrap(true);
					ceNomeArqEntr2.setEditable(false);
					ceNomeArqEntr2.setBackground(new Color(210, 210, 210));
					ceNomeArqEntr2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Preenche o subpainel com os componentes
					subpainelArqChaveRSAdest.add(botaoArq2);
					subpainelArqChaveRSAdest.add(ceNomeArqEntr2);


				// Preenche o painel principal
				painelArqChaveRSAdest.add(labelArqChaveRSAdest);
				painelArqChaveRSAdest.add(subpainelArqChaveRSAdest);
			//-------------------------Chave RSA-------------------------//



			//--------------------Escolha do Algoritmo-------------------//
			JPanel painelEscolhaAlg = new JPanel();

			// Escolha de um algoritmo simetrico para o envelope
			JLabel textoAlg = new JLabel("Algoritmo simétrico:");
			String opcoesAlgSimetricos[] = {"AES", "DES", "RC4"};

			JComboBox caixaEscolha = new JComboBox(opcoesAlgSimetricos);
			caixaEscolha.setEditable(false);
			caixaEscolha.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					algoritmo = caixaEscolha.getSelectedItem().toString();
				}
			});

			// Preenche o painel principal
			painelEscolhaAlg.add(textoAlg);
			painelEscolhaAlg.add(caixaEscolha);
			//--------------------Escolha do Algoritmo-------------------//



			//-----------------------Gerar Arquivos----------------------//
			JPanel painelGeracao = new JPanel();
			painelGeracao.setLayout(new BoxLayout(painelGeracao, BoxLayout.PAGE_AXIS));
			painelGeracao.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));


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

				// Botao de confirmacao (placeholder)
				JButton botaoGerarArqs = new JButton("Gerar");
				botaoGerarArqs.addActionListener(e -> System.out.println(
						"texto:\n\"\n" + texto + "\n\"\n\n" + 
						"chave:\n\"\n" + chaveRSA + "\n\"\n\n" + 
						"algoritmo:\n\"\n" + algoritmo + "\n\"\n\n" + 
						"nomeArqChaveAss:\n\"\n" + nomeArqChaveAss.getText() + "\n\"\n\n" +
						"nomeArqTextoCript:\n\"\n" + nomeArqTextoCript.getText() + "\n\"\n\n" +
						"Gerou!"));


				// Preenche o subpainel com o componente
				subpainelBotaoGerar.add(botaoGerarArqs);


			// Preenche o painel principal
			painelGeracao.add(subpainelArqChaveAss);
			painelGeracao.add(subpainelArqTextoCript);
			painelGeracao.add(subpainelBotaoGerar);
			//-----------------------Gerar Arquivos----------------------//



			// Adiciona os paineis no Painel de Criacao de Envelope
			painelCriaEnv.add(painelArqTextoEmClaro);
			painelCriaEnv.add(painelArqChaveRSAdest);
			painelCriaEnv.add(painelEscolhaAlg);
			painelCriaEnv.add(painelGeracao);
		//------------------Criar Envelope------------------//

		return painelCriaEnv;
	}



public static String lerConteudo(File arquivo) {
	String conteudo = "PLACEHOLDER";

	String path = arquivo.getPath();
	try {
		BufferedReader leitor = new BufferedReader(new FileReader(path));
		String linha = "", texto = "";

		while ((linha = leitor.readLine()) != null) {
			texto += linha + "\n";
		}

		conteudo = texto;
		leitor.close();
	}
	catch (Exception e) {
		e.printStackTrace();
	}

	return conteudo;
}
}