import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;


public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		 StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		 encryptor.setPassword("superhaslo"); 
		 //Ale to jest kolejne has�o do pami�tania! 
		 //Here you may think: "wait... I can encrypt values in my configuration files, ok, but... 
		 //I still need a password (the encryption password) to decrypt them! Where can I store it safely?".
		 //That is correct, you still need one password, but this time it is the encryption one, under jasypt control,
		 //and thus, configurable in many other more secure ways (especially recommended would be environment variables or web PBE configuration...).
		 
		 //Troch� tego nie kupuj� - bez jasyptu te� mog� zapyta� o has�o z konsoli, pobra� je z parametru wywo�ania, VM, czy ze zmiennej �rodowiskowej.
		 //Z drugiej strony mo�na r�cznie szyfrowa� has�a w plikach, ale jasypt to u�atwia.
		 
		 //Dodatkowo, jasypt integruje si� z Hibernatem i Springiem.
		 
		 
		 
		 Properties props = new EncryptableProperties(encryptor);
		 props.load(new FileInputStream("configuration.properties"));

		 String datasourceUsername = props.getProperty("datasource.username"); 
		 String datasourcePassword = props.getProperty("datasource.password");
		 System.out.println(datasourceUsername);
		 System.out.println(datasourcePassword);
		 
	}

}
