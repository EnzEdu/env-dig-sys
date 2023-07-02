package abaGerar;

import funcionalidades.*;

public class ListenerGerarChv {

	public static String clicouBotaoGerar(String nomeChavePubl, String nomeChavePriv) {

		// Verifica se o nome do arquivo de chave publica esta vazio
		if (nomeChavePubl == null || nomeChavePubl.isBlank() == true)
		{
			return "1 - Arquivo de chave publica vazio.";
		}

		// Verifica se o nome do arquivo de chave privada esta vazio
		else
		if (nomeChavePriv == null || nomeChavePriv.isBlank() == true)
		{
			return "1 - Arquivo de chave privada vazio.";
		}


		/* Recebe os resultados da geracao das Chaves
		 * resultChaves[0] = chave publica
		 * resultChaves[1] = chave privada
		*/
		String[] resultChaves = {"", ""};
		//resultChaves = AlgoritmoRSA();


		// Ordena a escrita dos arquivos resultantes
		GerenciadorArquivos.criarArq(nomeChavePubl, resultChaves[0]);
		GerenciadorArquivos.criarArq(nomeChavePriv, resultChaves[1]);


		/* ---DEBUG---
		System.out.println("nomeChavePubl:\n\"\n" + nomeChavePubl + "\n\"\n\n" +
				"chavePubl:\n\"\n" + resultChaves[0] + "\n\"\n\n" +
				"nomeChavePriv:\n\"\n" + nomeChavePriv + "\n\"\n\n" +
				"chavePriv:\n\"\n" + resultChaves[1] + "\n\"\n\n" +
				"Gerou!"); */
		return "0 - Arquivos gerados com sucesso!";
	}
}