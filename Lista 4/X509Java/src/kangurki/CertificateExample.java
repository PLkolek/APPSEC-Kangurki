package kangurki;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.security.auth.x500.X500Principal;

public class CertificateExample {
	private static String[] usages = new String[] { "digitalSignature", "nonRepudiation", "keyEncipherment",
			"dataEncipherment", "keyAgreement", "keyCertSign", "cRLSign", "encipherOnly", "decipherOnly" };

	public static void testCertificate() throws Exception {
		try (InputStream certStream = new FileInputStream("resources/ssl.allegro.der")) {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) cf.generateCertificate(certStream);
			boolean[] certUsages = cert.getKeyUsage();
			for (int i = 0; i < certUsages.length; i++) {
				if (certUsages[i])
					System.out.println(usages[i]);
			}
			System.out.println(cert.getNotBefore());
			System.out.println(cert.getNotAfter());
			X500Principal subject = cert.getSubjectX500Principal();
			System.out.println(subject.getName());

		}
	}
}
