package abaAbrir;

import funcionalidades.*;

public class ListenerAbrirEnv {

	public static String clicouBotaoGerar(byte[] msgCript, byte[] chaveCript,
			byte[] chavePrivRSA, String algoritmo, String nomeArqMsgDecif) {

		// Verifica se o arquivo de mensagem criptografada esta vazio
		if (msgCript == null || msgCript.length == 0)
		{
			return "1 - Arquivo de texto vazio.";
		}

		// Verifica se o arquivo de chave criptografada esta vazio
		else
		if (chaveCript == null || chaveCript.length == 0)
		{
			return "1 - Arquivo de chave simétrica criptografada vazio.";
		}

		// Verifica se o arquivo da chave privada RSA esta vazio
		else
		if (chavePrivRSA == null || chavePrivRSA.length == 0)
		{
			return "1 - Arquivo de chave RSA vazio.";
		}

		// Verifica se o arquivo da chave privada RSA utiliza o padrão OpenSSL
		else
		if (new String(chavePrivRSA).trim().startsWith("-----BEGIN PRIVATE KEY-----") == false ||
			new String(chavePrivRSA).trim().endsWith("-----END PRIVATE KEY-----") == false)
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
		StringBuilder msgResultado = new StringBuilder("");
		byte[] chaveSimet = new byte[2];
		byte[] textoEmClaro = new byte[2];
		switch (algoritmo)
		{
			case "AES":
			{
				// Decifra o envelope, depois a criptografia simetrica
				chaveSimet   = AlgoritmoRSA.decifrar(chaveCript, chavePrivRSA);
				textoEmClaro = AlgoritmoAES.decifrar(msgCript, chaveSimet, msgResultado);
				break;
			}

			case "DES":
			{
				// Decifra o envelope, depois a criptografia simetrica
				chaveSimet   = AlgoritmoRSA.decifrar(chaveCript, chavePrivRSA);
				textoEmClaro = AlgoritmoDES.decifrar(msgCript, chaveSimet, msgResultado);
				break;
			}

			case "RC4":
			{
				// Decifra o envelope, depois a criptografia simetrica
				chaveSimet   = AlgoritmoRSA.decifrar(chaveCript, chavePrivRSA);
				textoEmClaro = AlgoritmoRC4.decifrar(msgCript, chaveSimet, msgResultado);
				break;
			}

			default:
			{
				break;
			}
		}


		/* ---DEBUG---
		System.out.println("msgCript:\n\"\n" + new String(msgCript) + "\n\"\n\n" +
				"chavePrivRSA:\n\"\n" + new String(chavePrivRSA) + "\n\"\n\n" +
				"algoritmo:\n\"\n" + algoritmo + "\n\"\n\n" +
				"Gerou!"); */


		// Ordena a escrita dos arquivos resultantes,
		// caso nao haja erros no processo de decifragem
		if (Character.getNumericValue(msgResultado.charAt(0)) != 2)
		{
			GerenciadorArquivos.criarArq(nomeArqMsgDecif, textoEmClaro);
			return "0 - Arquivo gerado com sucesso!";
		}
		else
		{
			return msgResultado.toString();
		}
	}
}