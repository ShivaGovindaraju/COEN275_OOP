/**
 * 
 */
package assign2;

/**
 * Implements Encryptable interface using a Caesar-Shift Cipher.
 * Shiva K Govindaraju / COEN 275 / Assignment 2 / Submitted 11 Feb 2020
 * @author keshavgovindaraju
 *
 */
public class Encryptor implements Encryptable {

	//private variable for use in Caesar Shifting
	private int offset;
	
	
	/**
	 * Default Constructor
	 */
	public Encryptor() {
		this.setOffset(3);
		//as a default, we'll use the standard shift of 3
	}
	
	/**
	 * Class Constructor specifying an offset
	 * 
	 * @param offset an integer between 1 and 25 to set the offset with
	 */
	//Alternate Constructor with an offset parameter
	public Encryptor(int offset) {
		this.setOffset(offset);
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the offset variable and ensures that the offset is between 1 and 25, inclusive
	 * 
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		if (offset < 1) {
			System.out.println("Error [Encryptor]: the Offset cannot be < 01. Offset is being set to 1.");
			this.offset = 1;
		} else if (offset > 25) {
			System.out.println("Error [Encryptor]: the Offset cannot be > 25. Offset is being set to 25.");
			this.offset = 25;
		} else {
			this.offset = offset;
		}
	}

	/**
	 * Implements method declared in Encryptable Interface.
	 * Encrypts an input String using Shift-Ciphering. The offset used for shifting is retained in the private variable offset.
	 * 
	 * @param text	the input String to be encrypted
	 * @return		the encrypted String using a Shift-Cipher of known offset.
	 */
	@Override
	public String encrypt(String text) {
		char [] plaintext = text.toUpperCase().toCharArray();
		for (int i = 0; i < plaintext.length; i++) {
			//Note: we should only encrypt letters using the Shift-Cipher.
			if(Character.isLetter(plaintext[i])) {
				//took the algorithm from the assignment document
				plaintext[i] = (char)(((plaintext[i] - (int)'A' + this.getOffset()) % 26) + (int)'A');
			}
		}
		return new String(plaintext);
	}

	/**
	 * Implements method declared in Encryptable Interface
	 * Decrypts an input String using Shift-Ciphering. The offset sued for shifting is retained in the private variable offset.
	 * 
	 * @param text	the input String to be decrypted
	 * @return		the decrypted String using a Shift-Cipher of known offset.
	 */
	@Override
	public String decrypt(String text) {
		char [] codetext = text.toUpperCase().toCharArray();
		for (int i = 0; i < codetext.length; i++) {
			//Note: we should only encrypt letters using the Shift-Cipher.
			if(Character.isLetter(codetext[i])) {
				//took the algorithm from the assignment document
				codetext[i] = (char)(((codetext[i] - (int)'A' + (26 - this.getOffset())) % 26) + (int)'A');
			}
		}
		return new String(codetext);
	}

}
