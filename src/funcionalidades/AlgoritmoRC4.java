package funcionalidades;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AlgoritmoRC4 {

	public static byte[][] cifrar(byte[] textoEmClaro) {

		/* Dados produzidos pela cifragem
		 * dados[0] = texto criptografado
		 * dados[1] = chave simetrica criptografada
		 */
		byte[][] dados = new byte[2][2];

		try {

			// Criando chave simetrica aleatoria de 256 bits
			KeyGenerator gerador = KeyGenerator.getInstance("RC4");
			gerador.init(256);

			SecretKey chaveSimetrica = gerador.generateKey();

			// Cifragem do texto em claro
			Cipher cifrador = Cipher.getInstance("RC4");
			cifrador.init(Cipher.ENCRYPT_MODE, chaveSimetrica);
			dados[0] = cifrador.doFinal(textoEmClaro);

			// Criando a versao em bytes da chave simetrica
			dados[1] = chaveSimetrica.getEncoded();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dados;
	}


	public static byte[] decifrar(byte[] textoCript, byte[] chaveSimet, StringBuilder resultado) {
		byte[] textoEmClaro = new byte[2];

		try {

			// Conversao de tipo da chave simetrica: byte[] => SecretKey
			SecretKey chaveSimetrica = new SecretKeySpec(chaveSimet, 0, chaveSimet.length, "RC4");

			// Decifragem do texto em claro
			Cipher cifrador = Cipher.getInstance("RC4");
			cifrador.init(Cipher.DECRYPT_MODE, chaveSimetrica);
			textoEmClaro = cifrador.doFinal(textoCript);

		} catch (Exception e) {
			resultado.delete(0, resultado.length());
			resultado.append("2 - Ocorreu um erro na decifragem do arquivo.");
		}

		return textoEmClaro;
	}
}