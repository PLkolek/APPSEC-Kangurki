package kangurki;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;

public class CRLValidation {
	public static void testCRLs() throws Exception {
		try (InputStream crlStream = new FileInputStream("resources/ThawteEVCA2006.crl");
				InputStream certStream = new FileInputStream("resources/ssl.allegro.der")) {
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509CRL crl = (X509CRL) cf.generateCRL(crlStream);
			X509Certificate cert = (X509Certificate) cf.generateCertificate(certStream);
			X509CRLEntry entry = crl.getRevokedCertificate(cert);
			if (entry == null) {
				System.out.println("Not revoked");
			} else {
				System.out.println("Revoked");
				System.out.println(entry);
			}
		}
	}
}
