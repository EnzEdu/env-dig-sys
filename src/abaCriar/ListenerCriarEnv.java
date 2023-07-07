package abaCriar;

import funcionalidades.*;

public class ListenerCriarEnv {

	public static String clicouBotaoGerar(byte[] texto, byte[] chavePublRSA,
			String algoritmo, String nomeArqChaveAss, String nomeArqTextoCript) {

		// Instancia as versoes em String para verificacoes
		String strTexto = new String(texto);
		String strChavePublRSA = new String(chavePublRSA);

		// Verifica se o arquivo de texto esta vazio
		if (strTexto == null || strTexto.isBlank() == true)
		{
			return "1 - Arquivo de texto vazio.";
		}

		// Verifica se o arquivo da chave publica RSA esta vazio
		else
		if (strChavePublRSA == null || strChavePublRSA.isBlank() == true)
		{
			return "1 - Arquivo de chave RSA vazio.";
		}

		// Verifica se o arquivo da chave publica RSA utiliza o padrão OpenSSL
		else
		if (strChavePublRSA.trim().startsWith("-----BEGIN PUBLIC KEY-----") == false ||
			strChavePublRSA.trim().endsWith("-----END PUBLIC KEY-----") == false)
		{
			return "1 - Arquivo de chave RSA fora do padrão OpenSSL.";
		}

		// Verifica se foi escolhido um algoritmo
		else
		if (algoritmo == null || algoritmo.isBlank() == true || algoritmo.equals("-"))
		{
			return "1 - Algoritmo de criptografia não escolhido.";
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
			/*
			case "AES":
			{
				// Realiza a criptografia simetrica, depois o envelope
				dadosAlgoritmo  = AlgoritmoAES.cifrar(texto);
				chaveSimetCript = AlgoritmoRSA.cifrar(dadosAlgoritmo[1], chavePublRSA);
				break;
			}
			*/ /*
			case "DES":
			{
				// Realiza a criptografia simetrica, depois o envelope
				dadosAlgoritmo  = AlgoritmoDES.cifrar(texto);
				chaveSimetCript = AlgoritmoRSA.cifrar(dadosAlgoritmo[1], chavePublRSA);
				break;
			}
			*/
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