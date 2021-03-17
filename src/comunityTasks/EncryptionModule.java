package comunityTasks;

public class EncryptionModule {
	
	RSAEncryptionSubModule RSAE = new RSAEncryptionSubModule();
	PreEncriptionSubModule PREE = new PreEncriptionSubModule();
	
	EncryptionModule() {
		
	}
	
	public String encrypt(String messege) {
		String temp = RSAE.encrypt(PREE.encrypt(messege));
		System.out.println(temp);
		return temp;
	}
	
	public String desypher(String messege) {
		String temp = PREE.dechipher(RSAE.decypher(messege));
		System.out.println(temp);
		return temp;
	}
}
