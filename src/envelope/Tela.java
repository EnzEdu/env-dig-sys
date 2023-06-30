package envelope;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Tela {
	static JFrame tela;
	static JTabbedPane painelOpcoes;
	
	static JPanel painelCriaEnv;
	static JButton botaoArq1, botaoArq2;
	static JTextArea nomeArq1, nomeArq2;
	
	private static String texto, chaveRSA, algoritmo;
	
	public static void criaTela() {
		tela = new JFrame();
		
		
		//-------------------------Tela---------------------//
		tela.setTitle("env-dig-sys");
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setSize(800, 600);
		
		tela.getContentPane().setLayout(new GridBagLayout());
		tela.getContentPane().setBackground(Color.PINK);
		//tela.getContentPane().setBackground(Color.BLACK);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		//-------------------------Tela---------------------//

	
		
		//-------------------Painel de Opcoes---------------//
		painelOpcoes = new JTabbedPane();
		painelOpcoes.setBackground(Color.RED);
		//-------------------Painel de Opcoes---------------//
		
		
		
		//------------------Criar Envelope------------------//
		painelCriaEnv = new JPanel();
		painelCriaEnv.setLayout(new BoxLayout(painelCriaEnv, BoxLayout.PAGE_AXIS));
		painelCriaEnv.setBackground(Color.GREEN);
        painelCriaEnv.setSize(50, 50);
        painelCriaEnv.setVisible(true);
		
        
			//---------------------Mensagem em claro---------------------//
        	JPanel painelArquivoMsg = new JPanel();
        	
        	// Escolha do arquivo com a mensagem
        	botaoArq1 = new JButton("Procurar...");
            botaoArq1.setSize(120, 30);
        	botaoArq1.setVisible(true);
        	botaoArq1.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
				    JFileChooser fc = new JFileChooser();
				    int i = fc.showOpenDialog(tela);
				    
				    if (i == JFileChooser.APPROVE_OPTION)
				    {
				        texto = lerConteudo(fc.getSelectedFile());
				        nomeArq1.setText(fc.getSelectedFile().getPath());
				    }
        	    }
        	});
			
        	// Nome do arquivo
			nomeArq1 = new JTextArea(0, 0);
			nomeArq1.setBounds(120,0,200,20);
			nomeArq1.setLineWrap(true);
			nomeArq1.setEditable(false);

			// Preenche o painel com os componentes criados
			painelArquivoMsg.add(botaoArq1);
			painelArquivoMsg.add(nomeArq1);
			
			
			
			//------------------Chave RSA Destinatario-------------------//
        	JPanel painelArquivoRSAdest = new JPanel();
        	
        	// Escolha do arquivo com a chave publica RSA do destinatario
        	botaoArq2 = new JButton("Procurar...");
            botaoArq2.setSize(120, 30);
        	botaoArq2.setVisible(true);
        	botaoArq2.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
				    JFileChooser fc = new JFileChooser();
				    int i = fc.showOpenDialog(tela);
				    
				    if (i == JFileChooser.APPROVE_OPTION)
				    {
				        chaveRSA = lerConteudo(fc.getSelectedFile());
				        nomeArq2.setText(fc.getSelectedFile().getPath());
				    }
        	    }
        	});
			
        	// Nome do arquivo
			nomeArq2 = new JTextArea(0, 0);
			nomeArq2.setBounds(120,0,200,20);
			nomeArq2.setLineWrap(true);
			nomeArq2.setEditable(false);

			// Preenche o painel com os componentes criados
			painelArquivoRSAdest.add(botaoArq2);
			painelArquivoRSAdest.add(nomeArq2);
			//-------------------------Chave RSA-------------------------//
			
			
			
			//--------------------Escolha do Algoritmo-------------------//
			JPanel painelEscolhaAlg = new JPanel();
			
			// Escolha de um algoritmo simetrico para o envelope
			JLabel textoAlg = new JLabel("Algoritmo simetrico:");
			String opcoesAlgSimetricos[] = {"AES", "DES", "RC4"};
			
			JComboBox caixaEscolha = new JComboBox(opcoesAlgSimetricos);
			caixaEscolha.setEditable(false);
			caixaEscolha.addActionListener(new ActionListener() {
        	    @Override
        	    public void actionPerformed(ActionEvent e) {
				    algoritmo = caixaEscolha.getSelectedItem().toString();
        	    }
        	});
			
			// Preenche o painel com os componentes criados
			painelEscolhaAlg.add(textoAlg);
			painelEscolhaAlg.add(caixaEscolha);
			//--------------------Escolha do Algoritmo-------------------//
			
			
			
			//-----------------------Gerar Arquivos----------------------//
			JPanel painelGeracao = new JPanel();
			
			// Botao de confirmacao (placeholder)
			JButton botaoGerarArqs = new JButton("Imprimir");
			botaoGerarArqs.addActionListener(e -> System.out.println(texto + "\n" + chaveRSA + "\n" + algoritmo + "\n" + "Gerou!"));
			
			// Preenche o painel com os componentes criados
			painelGeracao.add(botaoGerarArqs);
			//-----------------------Gerar Arquivos----------------------//
			
			
			
			// Adiciona os subpaineis no Painel de Criacao de Envelope
			JLabel textoMsg = new JLabel("Escolha o arquivo de texto:");
			painelCriaEnv.add(textoMsg);
			painelCriaEnv.add(painelArquivoMsg);
			
			textoMsg = new JLabel("Adicione a chave RSA publica do destinatario:");
			painelCriaEnv.add(textoMsg);
			painelCriaEnv.add(painelArquivoRSAdest);
			
			painelCriaEnv.add(painelEscolhaAlg);
			painelCriaEnv.add(painelGeracao);
		//------------------Criar Envelope------------------//
		

		// Adiciona os paineis de funcionalidades ao Painel de Opcoes
		painelOpcoes.addTab("Criar envelope", painelCriaEnv);
		
		
		// Adiciona o Painel de Opcoes no frame principal
		tela.add(painelOpcoes, c);
		tela.setVisible(true);
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
