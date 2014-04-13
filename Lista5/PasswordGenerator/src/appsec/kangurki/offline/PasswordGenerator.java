package appsec.kangurki.offline;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordGenerator {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Console c = System.console();
		c.printf("Password: \n");
		char[] password = c.readPassword();
		c.printf("Iters: \n");
		int iters = Integer.parseInt(c.readLine().trim());
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte[] passBytes = Base64.getDecoder().decode(new String(password));
		for (int i = 0; i < iters; i++) {
			passBytes = md.digest(passBytes);
		}
		c.printf("Wpisz to: %s\n", Base64.getEncoder()
				.encodeToString(passBytes));

	}
}
