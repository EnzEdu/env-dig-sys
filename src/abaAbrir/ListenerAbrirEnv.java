package abaAbrir;

import funcionalidades.*;

public class ListenerAbrirEnv {

	public static String clicouBotaoGerar(String msgCript, String chavePrivRSA,
			String algoritmo, String nomeArqMsgDecif) {

		// Verifica se o arquivo de mensagem criptografada esta vazio
		if (msgCript == null || msgCript.isBlank() == true)
		{
			return "1 - Arquivo de texto vazio.";
		}

		// Verifica se o arquivo da chave privada RSA esta vazio
		else
		if (chavePrivRSA == null || chavePrivRSA.isBlank() == true)
		{
			return "1 - Arquivo de chave RSA vazio.";
		}

		// Verifica se o arquivo da chave privada RSA utiliza o padrão OpenSSL
		else
		if (chavePrivRSA.trim().startsWith("-----BEGIN PRIVATE KEY-----") == false ||
			chavePrivRSA.trim().endsWith("-----END PRIVATE KEY-----") == false)
		{
			return "1 - Arquivo de chave RSA fora do padrão OpenSSL.";
		}

		// Verifica se foi escolhido um algoritmo
		else
		if (algoritmo == null || algoritmo.isBlank() == true || algoritmo.equals("-"))
		{
			return "1 - Algoritmo de criptografia não escolhido.";
		}

		// Verifica se o arquivo de mensagem decifrada esta vazio
		else
		if (nomeArqMsgDecif == null || nomeArqMsgDecif.isBlank() == true)
		{
			return "1 - Nome do arquivo com o texto decifrado vazio.";
		}


		// Recebe o texto decifrado com base no algoritmo escolhido
		String resultEnvelope = "";
		switch (algoritmo)
		{
			/*
			case "AES":
			{
				resultEnvelope = AlgoritmoAES.decifrar(texto, chaveRSA);
				break;
			}
			*/ /*
			case "DES":
			{
				resultEnvelope = AlgoritmoDES.decifrar(texto, chaveRSA);
				break;
			}
			*/ /*
			case "RC4":
			{
				resultEnvelope = AlgoritmoRC4.decifrar(texto, chaveRSA);
				break;
			}
			*/
			default:
			{
				break;
			}
		}


		// Ordena a escrita dos arquivos resultantes
		GerenciadorArquivos.criarArq(nomeArqMsgDecif, resultEnvelope);


		/* ---DEBUG---
		System.out.println("msgCript:\n\"\n" + msgCript + "\n\"\n\n" +
				"chavePrivRSA:\n\"\n" + chavePrivRSA + "\n\"\n\n" +
				"algoritmo:\n\"\n" + algoritmo + "\n\"\n\n" +
				"Gerou!"); */
		return "0 - Arquivo gerado com sucesso!";
	}
}
