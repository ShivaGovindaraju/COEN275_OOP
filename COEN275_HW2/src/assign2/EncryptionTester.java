/**
 * 
 */
package assign2;

import java.util.Random;

/**
 * Testing class for Encryptor and Encryptable
 * Shiva K Govindaraju / COEN 275 / Assignment 2 / Submitted 11 Feb 2020
 * @author keshavgovindaraju
 *
 */
public class EncryptionTester {

	/**
	 * Default Constructor.
	 */
	public EncryptionTester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Main method used in testing Encryptor class, calls both Test Sets to test the class.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("----- Assignment 1 Questions 1 and 2 Tester -----\n");
		testSet1();
		System.out.println("");
		testSet2();
		System.out.println("\n---- Assign1 Questions 1 & 2 Tester Complete -----");
		return;
	}
	
	/**
	 * Method for First Testing Set of the Encryptor class.
	 * See assignment document for further details
	 */
	private static void testSet1() {
		String [] testStrings = {"abcdefghijklmnopqrstuvwxyz",
		                         "Shiva K. Govindaraju",
		                         "Xray defraction",
		                         "Zebra-tailed lizard",
		                         "Walkie-talkie",
		                         "COEN 275 OO Analysis, Design and Programming"};
		int [] testKeys = {0, 3, 11, 25, 26};
		
		Encryptor caesar = new Encryptor();
		String codetext;
		
		System.out.println("[EncryptionTester] --- Testing TestSet1 ---");
		
		for (int i = 0; i < testStrings.length; i++) {
			for (int j = 0; j < testKeys.length; j++) {
				//set the offset using known values
				caesar.setOffset(testKeys[j]);

				System.out.println("\n[EncryptionTester] Testing String: " + testStrings[i] + "  Offset: " + caesar.getOffset());
				codetext =  caesar.encrypt(testStrings[i]);
				System.out.println("Encrypted Text: " + codetext);
				System.out.println("Decrypted Test: " + caesar.decrypt(codetext));
			}
		}
		
		System.out.println("\n[EncryptionTester] --- TestSet1 Complete ---");
		
		return;
	}

	/**
	 * Method for Second Testing Set of the Encryptor class.
	 * See assignment document for further details
	 */
	private static void testSet2() {
		String [] testStrings = {"abcdefghijklmnopqrstuvwxyz",
									"Shiva K. Govindaraju",
									"Xray defraction",
									"Zebra-tailed lizard",
									"Walkie-talkie",
									"COEN 275 OO Analysis, Design and Programming"};

		Random rand = new Random(); //we're going to use this Random object to generate random offsets for testing purposes
		int testKey;
		
		Encryptor caesar = new Encryptor();
		String codetext;

		System.out.println("[EncryptionTester] --- Testing TestSet2 ---");

		for (int i = 0; i < testStrings.length; i++) {
				for (int j = 0; j < 4; j++) {
					//set the offset using random values
					testKey = rand.nextInt(25) + 1;
					caesar.setOffset(testKey);
					
					System.out.println("\n[EncryptionTester] Testing String: " + testStrings[i] + "  Offset: " + caesar.getOffset());
					codetext =  caesar.encrypt(testStrings[i]);
					System.out.println("Encrypted Text: " + codetext);
					System.out.println("Decrypted Test: " + caesar.decrypt(codetext));
				}
		}
		
		System.out.println("\n[EncryptionTester] --- TestSet2 Complete ---");
		
		return;
	}

}
