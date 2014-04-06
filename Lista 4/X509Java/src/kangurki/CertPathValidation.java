package kangurki;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.List;

public class CertPathValidation {
	public static void testCertPath() throws Exception {
		FileInputStream fis = new FileInputStream("resources/ssl.allegro.crt");
		CertificateFactory cf = CertificateFactory.getInstance("X.509");

		// generate certification chain
		@SuppressWarnings("unchecked")
		List<X509Certificate> c = (List<X509Certificate>) cf.generateCertificates(fis);
		CertPath path = cf.generateCertPath(c);
		CertPathValidator validator = CertPathValidator.getInstance("PKIX");
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());

		// load default Java trusted certificates
		try (FileInputStream fis2 = new FileInputStream(
				"C:\\Program Files\\Java\\jdk1.8.0\\jre\\lib\\security\\cacerts")) {
			ks.load(fis2, "changeit".toCharArray());
		}

		// and validate cert path
		PKIXParameters params = new PKIXParameters(ks);
		params.setRevocationEnabled(false);
		/*
		 * PKIXRevocationChecker rc = (PKIXRevocationChecker)
		 * validator.getRevocationChecker();
		 * rc.setOptions(EnumSet.of(Option.PREFER_CRLS, Option.SOFT_FAIL));
		 * params.setCertPathCheckers(Collections.<PKIXCertPathChecker>
		 * singletonList(rc));
		 */
		PKIXCertPathValidatorResult result = (PKIXCertPathValidatorResult) validator.validate(path, params);
		System.out.println(result);
		System.out.println(result.getTrustAnchor());
	}

}
