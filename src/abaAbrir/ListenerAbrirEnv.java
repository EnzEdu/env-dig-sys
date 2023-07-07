package abaAbrir;

import funcionalidades.*;

public class ListenerAbrirEnv {

	public static String clicouBotaoGerar(byte[] msgCript, byte[] chaveCript,
			byte[] chavePrivRSA, String algoritmo, String nomeArqMsgDecif) {

		// Instancia as versoes em String para verificacoes
		String strMsgCript = new String(msgCript);
		String strChavePrivRSA = new String(chavePrivRSA);
		String strChaveCript = new String(chaveCript);

		// Verifica se o arquivo de mensagem criptografada esta vazio
		if (strMsgCript == null || strMsgCript.isBlank() == true)
		{
			return "1 - Arquivo de texto vazio.";
		}

		// Verifica se o arquivo da chave privada RSA esta vazio
		else
		if (strChavePrivRSA == null || strChavePrivRSA.isBlank() == true)
		{
			return "1 - Arquivo de chave RSA vazio.";
		}

		// Verifica se o arquivo da chave privada RSA utiliza o padrão OpenSSL
		else
		if (strChavePrivRSA.trim().startsWith("-----BEGIN PRIVATE KEY-----") == false ||
			strChavePrivRSA.trim().endsWith("-----END PRIVATE KEY-----") == false)
		{
			return "1 - Arquivo de chave RSA fora do padrão OpenSSL.";
		}

		// Verifica se o arquivo de chave criptografada esta vazio
		else
		if (strChaveCript == null || strChaveCript.isBlank() == true)
		{
			return "1 - Arquivo de chave simétrica criptografada vazio.";
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
		byte[] chaveSimet = new byte[2];
		byte[] textoEmClaro = new byte[2];
		switch (algoritmo)
		{
			/*
			case "AES":
			{
				// Decifra o envelope, depois a criptografia simetrica
				chaveSimet   = AlgoritmoRSA.decifrar(chaveCript, chavePrivRSA);
				textoEmClaro = AlgoritmoAES.decifrar(msgCript  , chaveSimet);
				break;
			}
			*/
			case "DES":
			{
				// Decifra o envelope, depois a criptografia simetrica
				chaveSimet   = AlgoritmoRSA.decifrar(chaveCript, chavePrivRSA);
				textoEmClaro = AlgoritmoDES.decifrar(msgCript  , chaveSimet);
				break;
			}

			case "RC4":
			{
				// Decifra o envelope, depois a criptografia simetrica
				chaveSimet   = AlgoritmoRSA.decifrar(chaveCript, chavePrivRSA);
				textoEmClaro = AlgoritmoRC4.decifrar(msgCript  , chaveSimet);
				break;
			}

			default:
			{
				break;
			}
		}


		// Ordena a escrita dos arquivos resultantes
		GerenciadorArquivos.criarArq(nomeArqMsgDecif, textoEmClaro);


		/* ---DEBUG---
		System.out.println("msgCript:\n\"\n" + new String(msgCript) + "\n\"\n\n" +
				"chavePrivRSA:\n\"\n" + new String(chavePrivRSA) + "\n\"\n\n" +
				"algoritmo:\n\"\n" + algoritmo + "\n\"\n\n" +
				"Gerou!"); */
		return "0 - Arquivo gerado com sucesso!";
	}
}