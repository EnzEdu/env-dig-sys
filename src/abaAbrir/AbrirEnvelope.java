package abaAbrir;

import funcionalidades.GerenciadorArquivos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.io.File;

public class AbrirEnvelope {
	static JPanel painelAbreEnv, painelResultados;
	private static byte[] msgCript, chaveCript, chavePrivRSA;
	private static String algoritmo;


	public static JPanel criarPainel() {

		//------------------Abrir Envelope------------------//
		painelAbreEnv = new JPanel();
		painelAbreEnv.setLayout(new BoxLayout(painelAbreEnv, BoxLayout.PAGE_AXIS));
		painelAbreEnv.setBackground(Color.WHITE);
		painelAbreEnv.setSize(50, 50);
		painelAbreEnv.setVisible(true);


			//------------------Mensagem Criptografada------------------//
			JPanel painelArqCript = new JPanel();

				// Label do arquivo criptografado
				JLabel labelArqCript = new JLabel("                    Arquivo criptografado:");

				// Subpainel com a coleta do arquivo
				JPanel subpainelArqCript = new JPanel();

					// Area com o nome do arquivo
					JTextArea nomeArqCript = new JTextArea(0, 0);
					nomeArqCript.setBounds(120,0,200,20);
					nomeArqCript.setLineWrap(true);
					nomeArqCript.setEditable(false);
					nomeArqCript.setBackground(new Color(210, 210, 210));
					nomeArqCript.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Escolha do arquivo com a mensagem
					JButton botaoArqCript = new JButton("Procurar...");
					botaoArqCript.setSize(120, 30);
					botaoArqCript.setVisible(true);
					botaoArqCript.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							fc.setCurrentDirectory(new File("."));

							int i = fc.showOpenDialog(subpainelArqCript);
							if (i == JFileChooser.APPROVE_OPTION)
							{
								msgCript = GerenciadorArquivos.lerArq(fc.getSelectedFile());

								String path = fc.getSelectedFile().getPath();
								nomeArqCript.setText(path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1, path.length()));
							}
						}
					});

					// Preenche o subpainel com os componentes
					subpainelArqCript.add(botaoArqCript);
					subpainelArqCript.add(nomeArqCript);


				// Preenche o painel principal
				painelArqCript.add(labelArqCript);
				painelArqCript.add(subpainelArqCript);
			//------------------Mensagem Criptografada------------------//



			//--------------------Chave Criptografada--------------------//
			JPanel painelChaveCript = new JPanel();

				// Label da chave criptografada
				JLabel labelChaveCript = new JLabel("                      Chave criptografada:");

				// Subpainel com a coleta do arquivo
				JPanel subpainelChaveCript = new JPanel();

					// Area com o nome do arquivo
					JTextArea nomeChaveCript = new JTextArea(0, 0);
					nomeChaveCript.setBounds(120,0,200,20);
					nomeChaveCript.setLineWrap(true);
					nomeChaveCript.setEditable(false);
					nomeChaveCript.setBackground(new Color(210, 210, 210));
					nomeChaveCript.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Escolha do arquivo com a mensagem
					JButton botaoChaveCript = new JButton("Procurar...");
					botaoChaveCript.setSize(120, 30);
					botaoChaveCript.setVisible(true);
					botaoChaveCript.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							fc.setCurrentDirectory(new File("."));

							int i = fc.showOpenDialog(subpainelChaveCript);
							if (i == JFileChooser.APPROVE_OPTION)
							{
								chaveCript = GerenciadorArquivos.lerArq(fc.getSelectedFile());

								String path = fc.getSelectedFile().getPath();
								nomeChaveCript.setText(path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1, path.length()));
							}
						}
					});

					// Preenche o subpainel com os componentes
					subpainelChaveCript.add(botaoChaveCript);
					subpainelChaveCript.add(nomeChaveCript);


				// Preenche o painel principal
				painelChaveCript.add(labelChaveCript);
				painelChaveCript.add(subpainelChaveCript);
			//--------------------Chave Criptografada--------------------//



			//------------------Chave RSA Destinatario-------------------//
			JPanel painelArqChavePrivDest = new JPanel();

				// Label do arquivo com a chave privada
				JLabel labelArqChavePrivDest = new JLabel("Chave Privada RSA (Destinatario):");

				// Subpainel com a coleta do arquivo
				JPanel subpainelArqChavePrivDest = new JPanel();

					// Area com o nome do arquivo
					JTextArea nomeArqChavePrivDest = new JTextArea(0, 0);
					nomeArqChavePrivDest.setBounds(120,0,200,20);
					nomeArqChavePrivDest.setLineWrap(true);
					nomeArqChavePrivDest.setEditable(false);
					nomeArqChavePrivDest.setBackground(new Color(210, 210, 210));
					nomeArqChavePrivDest.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Escolha do arquivo com a mensagem
					JButton botaoArqChavePrivDest = new JButton("Procurar...");
					botaoArqChavePrivDest.setSize(120, 30);
					botaoArqChavePrivDest.setVisible(true);
					botaoArqChavePrivDest.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JFileChooser fc = new JFileChooser();
							fc.setCurrentDirectory(new File("."));

							int i = fc.showOpenDialog(subpainelArqChavePrivDest);
							if (i == JFileChooser.APPROVE_OPTION)
							{
								chavePrivRSA = GerenciadorArquivos.lerArq(fc.getSelectedFile());

								String path = fc.getSelectedFile().getPath();
								nomeArqChavePrivDest.setText(path.substring(path.lastIndexOf(System.getProperty("file.separator")) + 1, path.length()));
							}
						}
					});

					// Preenche o subpainel com os componentes
					subpainelArqChavePrivDest.add(botaoArqChavePrivDest);
					subpainelArqChavePrivDest.add(nomeArqChavePrivDest);


				// Preenche o painel principal
				painelArqChavePrivDest.add(labelArqChavePrivDest);
				painelArqChavePrivDest.add(subpainelArqChavePrivDest);
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



			//-----------------------Gerar Arquivo-----------------------//
			JPanel painelGeraArqDecif = new JPanel();
			painelGeraArqDecif.setLayout(new BoxLayout(painelGeraArqDecif, BoxLayout.PAGE_AXIS));
			painelGeraArqDecif.setBorder(BorderFactory.createEmptyBorder(32, 10, 0, 10));

				// Subpainel do arquivo de saida com a mensagem decifrada
				JPanel subpainelArqDecif = new JPanel();

					// Label do arquivo
					JLabel labelArqDecif = new JLabel("Nome do Arquivo com a Mensagem Decifrada: ");

					// Nome do arquivo
					JTextArea nomeArqDecif = new JTextArea(0, 0);
					nomeArqDecif.setBounds(120,0,200,20);
					nomeArqDecif.setLineWrap(true);
					nomeArqDecif.setBorder(BorderFactory.createLineBorder(Color.BLACK));

					// Preenche o subpainel com os componentes
					subpainelArqDecif.add(labelArqDecif);
					subpainelArqDecif.add(nomeArqDecif);


				// Subpainel do botao de gerar arquivo
				JPanel subpainelBotaoGeraArqDecif = new JPanel();

					// Botao de confirmacao
					JButton botaoGeraArqDecif = new JButton("Gerar");
					botaoGeraArqDecif.addActionListener(e -> {
							String retorno = ListenerAbrirEnv.clicouBotaoGerar
									(msgCript, chaveCript, chavePrivRSA, algoritmo,
									 nomeArqDecif.getText());
							mostrarResultado(retorno);
					});

					// Preenche o subpainel com os componentes
					subpainelBotaoGeraArqDecif.add(botaoGeraArqDecif);


				// Preenche o painel principal
				painelGeraArqDecif.add(subpainelArqDecif);
				painelGeraArqDecif.add(subpainelBotaoGeraArqDecif);
			//-----------------------Gerar Arquivo-----------------------//



			//--------------------Mostrar Resultados---------------------//
			painelResultados = new JPanel();
			//--------------------Mostrar Resultados---------------------//



			// Adiciona os paineis no Painel de Criacao de Envelope
			painelAbreEnv.add(painelArqCript);
			painelAbreEnv.add(painelChaveCript);
			painelAbreEnv.add(painelArqChavePrivDest);
			painelAbreEnv.add(painelEscolhaAlg);
			painelAbreEnv.add(painelGeraArqDecif);
			painelAbreEnv.add(painelResultados);
		//------------------Abrir Envelope------------------//

		return painelAbreEnv;
	}


	public static void mostrarResultado(String result) {

		// Remove o Painel de Resultados da janela
		painelAbreEnv.setVisible(false);
		painelAbreEnv.remove(painelResultados);

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
		painelResultados.add(labelResult);

		// Readiciona o Painel de Resultados na janela
		painelAbreEnv.add(painelResultados);
		painelAbreEnv.setVisible(true);
	}

}