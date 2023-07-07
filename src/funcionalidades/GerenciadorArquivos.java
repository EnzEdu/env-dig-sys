package funcionalidades;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GerenciadorArquivos {

	public static void criarArq(String nomeArq, byte[] conteudo) {

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
//				File arquivo = new File(nomeArq);
				FileOutputStream escritor = new FileOutputStream(arq);
				escritor.write(conteudo);
				escritor.close();
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
						FileOutputStream escritor = new FileOutputStream(arq);
						escritor.write(conteudo);
						escritor.close();
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


	public static byte[] lerArq(File arquivo) {
		byte[] conteudo = new byte[(int) arquivo.length()];

		// Faz a leitura linha por linha do arquivo e salva em conteudo
		try {
			FileInputStream leitor = new FileInputStream(arquivo);
			leitor.read(conteudo);
			leitor.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conteudo;
	}
}