package funcionalidades;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class AlgoritmoRSA {
	
	public static byte[] cifrar (byte[] bytesChaveSimet, byte[] chavePublRSA) {
		byte[] bytesChaveSimetCript = new byte[2];
		
		try {
			
			// Conversao de tipo de "chavePublRSA": byte[] => Key
				// byte[] => String
				String strChavePubl = new String(chavePublRSA);
				
				// Remove as tags
				int tamTagInicial = "-----BEGIN PUBLIC KEY-----\n".length();
				int tamTagFinal   = "\n-----END PUBLIC KEY-----\n".length();
				
				String strChavePublSemTags = strChavePubl.substring
						(tamTagInicial, strChavePubl.length()-tamTagFinal);
				
				// Decodificacao da chave em Base64
				byte[] byteChavePubl = Base64.getDecoder().decode(strChavePublSemTags);
				
				// Geracao da chave (usando padrao X509)
				Key chavePubl = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(byteChavePubl));
				
				
			// Instancia o cifrador
			Cipher cifrador = Cipher.getInstance("RSA");
			cifrador.init(Cipher.ENCRYPT_MODE, chavePubl);
			
			// Cifragem da chave simetrica "bytesChaveSimet"
			bytesChaveSimetCript = cifrador.doFinal(bytesChaveSimet);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bytesChaveSimetCript;
	}

	
	public static byte[] decifrar (byte[] bytesChaveSimetCript, byte[] chavePrivRSA) {
		byte[] bytesChaveSimet = new byte[2];
		
		try {
			
			// Conversao de tipo de "chavePrivRSA": byte[] => Key
				// byte[] => String
				String strChavePriv = new String(chavePrivRSA);
				
				// Remove as tags
				int tamTagInicial = "-----BEGIN PRIVATE KEY-----\n".length();
				int tamTagFinal   = "\n-----END PRIVATE KEY-----\n".length();
				
				String strChavePrivSemTags = strChavePriv.substring
						(tamTagInicial, strChavePriv.length()-tamTagFinal);
				
				// Decodificacao da chave em Base64
				byte[] byteChavePriv = Base64.getDecoder().decode(strChavePrivSemTags);
				
				// Geracao da chave (usando padrao PKCS8)
				Key chavePriv = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(byteChavePriv));
		
			// Instancia o cifrador
			Cipher cifrador = Cipher.getInstance("RSA");
			cifrador.init(Cipher.DECRYPT_MODE, chavePriv);
			
			// Decifragem da chave simetrica "bytesChaveSimetCript"
			bytesChaveSimet = cifrador.doFinal(bytesChaveSimetCript);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bytesChaveSimet;
	}
	
	
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