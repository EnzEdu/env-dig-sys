package funcionalidades;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriarArquivo {

	public static void criar(String nomeArq, String conteudo) {

		// Caso o nome fornecido nao possua extensao, salva em .txt
		// Verifica extensoes de 3 e 4 letras
		if (nomeArq.lastIndexOf(".") != nomeArq.length()-4 &&
			nomeArq.lastIndexOf(".") != nomeArq.length()-5)
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
				String novoNomeArq = nomeArq.substring(0, nomeArq.lastIndexOf(".")) +
									 String.valueOf(copia) +
									 nomeArq.substring(nomeArq.lastIndexOf("."));

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

				copia++;
			}
		}
	}
}