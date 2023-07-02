package abaCriar;

import funcionalidades.*;

public class ListenerCriarEnv {

	public static String clicouBotaoGerar(String texto, String chaveRSA,
			String algoritmo, String nomeArqChaveAss, String nomeArqTextoCript) {

		// Verifica se o arquivo de texto esta vazio
		if (texto == null || texto.isBlank() == true)
		{
			return "1 - Arquivo de texto vazio.";
		}

		// Verifica se o arquivo da chave publica RSA esta vazio
		else
		if (chaveRSA == null || chaveRSA.isBlank() == true)
		{
			return "1 - Arquivo de chave RSA vazio.";
		}

		// Verifica se o arquivo da chave publica RSA utiliza o padrão OpenSSL
		else
		if (chaveRSA.trim().startsWith("-----BEGIN PUBLIC KEY-----") == false ||
			chaveRSA.trim().endsWith("-----END PUBLIC KEY-----") == false)
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


		/* Recebe os resultados do Envelope com base no algoritmo escolhido
		 * resultEnvelope[0] = chave assinada
		 * resultEnvelope[1] = texto criptografado
		*/
		String[] resultEnvelope = {"", ""};
		switch (algoritmo)
		{
			/*
			case "AES":
			{
				resultEnvelope = AlgoritmoAES(texto, chaveRSA);
				break;
			}
			*/ /*
			case "DES":
			{
				resultEnvelope = AlgoritmoDES(texto, chaveRSA);
				break;
			}
			*/ /*
			case "RC4":
			{
				resultEnvelope = AlgoritmoRC4(texto, chaveRSA);
				break;
			}
			*/
			default:
			{
				break;
			}
		}


		// Ordena a escrita dos arquivos resultantes
		GerenciadorArquivos.criarArq(nomeArqChaveAss  , resultEnvelope[0]);
		GerenciadorArquivos.criarArq(nomeArqTextoCript, resultEnvelope[1]);


		/* ---DEBUG---
		System.out.println("texto:\n\"\n" + texto + "\n\"\n\n" +
				"chave:\n\"\n" + chaveRSA + "\n\"\n\n" +
				"algoritmo:\n\"\n" + algoritmo + "\n\"\n\n" +
				"nomeArqChaveAss:\n\"\n" + nomeArqChaveAss + "\n\"\n\n" +
				"nomeArqTextoCript:\n\"\n" + nomeArqTextoCript + "\n\"\n\n" +
				"Gerou!"); */
		return "0 - Arquivos gerados com sucesso!";
	}
}