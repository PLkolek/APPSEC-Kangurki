package kangurki;

public class Main {

	public static void main(String[] args) throws Exception {
		CertPathValidation.testCertPath();
		CRLValidation.testCRLs();
		CertificateExample.testCertificate();
	}
}
