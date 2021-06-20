/**
 * 
 */
package assign2;

/**
 * Interface for Objects which encrypt and decrypt Strings
 * Shiva K Govindaraju / COEN 275 / Assignment 2 / Submitted 11 Feb 2020
 * @author keshavgovindaraju
 *
 */
public interface Encryptable {
	public String encrypt(String text);
	public String decrypt(String text);
}
