package abaAbrir;

import funcionalidades.*;

public class ListenerAbrirEnv {

	public static String clicouBotaoGerar(byte[] msgCript, byte[] chaveCript,
			byte[] chavePrivRSA, String algoritmo, String nomeArqMsgDecif) {

		// Verifica se o arquivo de mensagem criptografada esta vazio ou se o usu�rio n�o o selecionou
		if (msgCript == null || msgCript.length == 0)
		{
			return "1 - Arquivo de texto vazio/n�o selecionado.";
		}

		// Verifica se o arquivo de chave criptografada esta vazio ou se o usu�rio n�o o selecionou
		else
		if (chaveCript == null || chaveCript.length == 0)
		{
			return "1 - Arquivo de chave sim�trica criptografada vazio/n�o selecionado.";
		}

		// Verifica se o arquivo da chave privada RSA esta vazio ou se o usu�rio n�o o selecionou
		else
		if (chavePrivRSA == null || chavePrivRSA.length == 0)
		{
			return "1 - Arquivo de chave RSA vazio/n�o selecionado.";
		}

		// Verifica se o arquivo da chave privada RSA utiliza o padr�o de chaves geradas pela nossa aplica��o (header e footer)
		else
		if (new String(chavePrivRSA).trim().startsWith("-----BEGIN PRIVATE KEY-----") == false ||
			new String(chavePrivRSA).trim().endsWith("-----END PRIVATE KEY-----") == false)
		{
			return "1 - Arquivo de chave RSA fora do padr�o da aplica��o.";
		}

		// Verifica se foi escolhido um algoritmo
		else
		if (algoritmo == null || algoritmo.isBlank() == true || algoritmo.equals("-"))
		{
			return "1 - Algoritmo de criptografia n�o selecionado.";
		}

		// Verifica se o nome arquivo de mensagem decifrada esta vazio
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
			case "AES":
			{
				// Decifra o envelope, depois a criptografia simetrica
				chaveSimet   = AlgoritmoRSA.decifrar(chaveCript, chavePrivRSA);
				textoEmClaro = AlgoritmoAES.decifrar(msgCript  , chaveSimet);
				break;
			}

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