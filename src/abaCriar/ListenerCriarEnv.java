package abaCriar;

import funcionalidades.*;

public class ListenerCriarEnv {

	public static String clicouBotaoGerar(byte[] texto, byte[] chavePublRSA,
			String algoritmo, String nomeArqChaveAss, String nomeArqTextoCript) {

		// Verifica se o arquivo de texto esta vazio ou se o usuário não o selecionou
		if (texto == null || texto.length == 0)
		{
			return "1 - Arquivo de texto vazio/não selecionado.";
		}

		// Verifica se o arquivo da chave publica RSA esta vazio ou se o usuário não o selecionou
		else
		if (chavePublRSA == null || chavePublRSA.length == 0)
		{
			return "1 - Arquivo de chave RSA vazio/não selecionado.";
		}

		// Verifica se o arquivo da chave publica RSA utiliza o padrão da nossa aplicação (header e footer)
		else
		if (new String(chavePublRSA).trim().startsWith("-----BEGIN PUBLIC KEY-----") == false ||
			new String(chavePublRSA).trim().endsWith("-----END PUBLIC KEY-----") == false)
		{
			return "1 - Arquivo de chave RSA fora do padrão da aplicação.";
		}

		// Verifica se foi escolhido um algoritmo
		else
		if (algoritmo == null || algoritmo.isBlank() == true || algoritmo.equals("-"))
		{
			return "1 - Algoritmo de criptografia não selecionado.";
		}

		// Verifica se o nome do arquivo da chave assinada esta vazio
		else
		if (nomeArqChaveAss == null || nomeArqChaveAss.isBlank() == true)
		{
			return "1 - Nome do arquivo com a chave assinada vazio.";
		}

		// Verifica se o nome do arquivo da chave assinada esta vazio
		else
		if (nomeArqTextoCript == null || nomeArqTextoCript.isBlank() == true)
		{
			return "1 - Nome do arquivo com o texto criptografado vazio.";
		}


		/* Recebe os resultados da criptografia com base no algoritmo escolhido
		 * dadosAlgoritmo[0] = texto criptografado
		 * dadosAlgoritmo[1] = chave simetrica em claro
		 * chaveSimetCript   = chave simetrica criptografada
		*/
		byte[][] dadosAlgoritmo = new byte[1][2];
		byte[] chaveSimetCript = new byte[2];
		switch (algoritmo)
		{
			case "AES":
			{
				// Realiza a criptografia simetrica, depois o envelope
				dadosAlgoritmo  = AlgoritmoAES.cifrar(texto);
				chaveSimetCript = AlgoritmoRSA.cifrar(dadosAlgoritmo[1], chavePublRSA);
				break;
			}

			case "DES":
			{
				// Realiza a criptografia simetrica, depois o envelope
				dadosAlgoritmo  = AlgoritmoDES.cifrar(texto);
				chaveSimetCript = AlgoritmoRSA.cifrar(dadosAlgoritmo[1], chavePublRSA);
				break;
			}

			case "RC4":
			{
				// Realiza a criptografia simetrica, depois o envelope
				dadosAlgoritmo  = AlgoritmoRC4.cifrar(texto);
				chaveSimetCript = AlgoritmoRSA.cifrar(dadosAlgoritmo[1], chavePublRSA);
				break;
			}

			default:
			{
				break;
			}
		}


		// Ordena a escrita dos arquivos resultantes
		GerenciadorArquivos.criarArq(nomeArqTextoCript, dadosAlgoritmo[0]);
		GerenciadorArquivos.criarArq(nomeArqChaveAss, chaveSimetCript);


		/* ---DEBUG---
		System.out.println("texto:\n\"\n" + new String(texto) + "\n\"\n\n" +
				"chave:\n\"\n" + new String(chavePublRSA) + "\n\"\n\n" +
				"algoritmo:\n\"\n" + algoritmo + "\n\"\n\n" +
				"nomeArqChaveAss:\n\"\n" + nomeArqChaveAss + "\n\"\n\n" +
				"nomeArqTextoCript:\n\"\n" + nomeArqTextoCript + "\n\"\n\n" +
				"Gerou!"); */
		return "0 - Arquivos gerados com sucesso!";
	}
}