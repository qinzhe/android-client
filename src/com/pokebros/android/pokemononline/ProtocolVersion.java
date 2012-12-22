package com.pokebros.android.pokemononline;

/**
 * Describes the protocol version of the client or server. 
 * 
 * Protocol versions have a major version(version) and a minor
 * version (minorVersion), that both range from 0 to 65536.
 * 
 * Use compareTo to compare two protocol versions.
 * 
 * @author coyotte508
 *
 */
public class ProtocolVersion extends SerializeBytes {
	public int version;
	public int minorVersion;
	
	/**
	 * Initiates a protocol version object to the latest version
	 */
	public ProtocolVersion() {
		version = 1;
		minorVersion = 2;
	}
	
	/**
	 * Initiates a protocol version object with the given version/minorVersion
	 * @param version
	 * @param minorVersion
	 */
	public ProtocolVersion(int version, int minorVersion) {
		this.version = version;
		this.minorVersion = minorVersion;
	}
	
	public ProtocolVersion(Bais input) {
		this.version = input.readShort();
		this.minorVersion = input.readShort();
	}
	
	/**
	 * Compares which version is newer
	 * @param other The other version
	 * @return A positive number if this version is newer, a negative number if
	 * the other version is newer. 0 if versions are the same.
	 */
	public int compareTo(ProtocolVersion other) {
		return this.toInt() - other.toInt();
	}
	
	/**
	 * Converts the structure into a single int (32 bits)
	 * @return version * 65536 + minorVersion 
	 */
	public int toInt() {
		return version << 16 + minorVersion;
	}

	@Override
	public Baos serializeBytes() {
		Baos b = new Baos();
		b.putShort((short)version);
		b.putShort((short)minorVersion);
		return b;
	} 
}