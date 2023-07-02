package funcionalidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GerenciadorArquivos {

	public static void criarArq(String nomeArq, String conteudo) {

		// Caso o nome fornecido nao possua extensao, salva em .txt
		// Verifica extensoes de 3 e 4 letras
		boolean naoTemExt = true;
		if (nomeArq.lastIndexOf(".") != -1)
		{
			// Olha se o ultimo ponto + ext caracteres representam o final do nome
			// (extensao)
			// (nao exatamente confere se eh uma extensao ou nao)
			for (int ext = 3; ext <= 4; ext++)
			{
				if (nomeArq.lastIndexOf(".")+(ext+1) == nomeArq.length())
				{
					naoTemExt = false;
					break;
				}
			}
		}

		if (naoTemExt == true)
		{
			nomeArq += ".txt";
		}



		// Cria o arquivo com o nome e conteudo repassados
		File arq = new File(nomeArq);

		if (arq.exists() == false)
		{
			try {
				FileWriter arquivo = new FileWriter(nomeArq);
				arquivo.write(conteudo);
				arquivo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Caso exista um arquivo com esse nome,
		// cria o arquivo com o nome concatenado com um numero
		else
		{
			int copia = 1;
			while (copia > 0)
			{
				// Adiciona um numero no nome do arquivo
				String novoNomeArq = nomeArq.substring(0, nomeArq.lastIndexOf(".")) +
									 String.valueOf(copia) +
									 nomeArq.substring(nomeArq.lastIndexOf("."));

				// Cria o arquivo, caso n exista um arquivo com o novo nome
				arq = new File(novoNomeArq);
				if (arq.exists() == false)
				{
					try {
						FileWriter arquivo = new FileWriter(novoNomeArq);
						arquivo.write(conteudo);
						arquivo.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					break;
				}

				// Aumenta o contador
				copia++;
			}
		}
	}


	public static String lerArq(File arquivo) {
		String conteudo = "PLACEHOLDER";

		// Faz a leitura linha por linha do arquivo e salva em conteudo
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
