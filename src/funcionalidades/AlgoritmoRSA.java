package funcionalidades;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class AlgoritmoRSA {

	public static String[] gerarChaves() {
		String[] parChaves = {"", ""};

		try {

			// Cria o gerador de chaves, e define o algoritmo e o tamanho da chave
			KeyPairGenerator gerador = KeyPairGenerator.getInstance("RSA");
			gerador.initialize(2048);

			// Gera o par de chaves RSA
			KeyPair par = gerador.generateKeyPair();

			// Divide o par em chavePublica e chavePrivada
			PublicKey chavePubl = par.getPublic();
			PrivateKey chavePriv = par.getPrivate();

			// Salva no vetor de chaves no formato Base64 com o padrao OpenSSL
			Base64.Encoder encoder = Base64.getEncoder();

			parChaves[0] = ("-----BEGIN PUBLIC KEY-----\n" +
							encoder.encodeToString(chavePubl.getEncoded()) +
							"\n-----END PUBLIC KEY-----\n");

			parChaves[1] = ("-----BEGIN PRIVATE KEY-----\n" +
							encoder.encodeToString(chavePriv.getEncoded()) +
							"\n-----END PRIVATE KEY-----\n");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return parChaves;
	}
}
