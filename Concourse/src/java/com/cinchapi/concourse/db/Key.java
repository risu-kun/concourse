/*
 * This project is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This project is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this project. If not, see <http://www.gnu.org/licenses/>.
 */
package com.cinchapi.concourse.db;

import java.nio.ByteBuffer;

import javax.annotation.concurrent.Immutable;

import com.cinchapi.concourse.util.ByteBuffers;
import com.cinchapi.util.ObjectReuseCache;
import com.google.common.base.Objects;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedLongs;

/**
 * The primary identifier for a {@link Row}. Each key is an unsigned long, which
 * means that the total pool of identifiers is between 0 and 2^64 - 1 inclusive.
 * 
 * @author jnelson
 */
@Immutable
public final class Key implements Comparable<Key> {

	/**
	 * Return a row key that represents the same value as the inverse two's
	 * complement of {@code value}.
	 * 
	 * @param value
	 * @return a row key.
	 */
	public static Key create(long value) {
		Key key = cache.get(value);
		if(key == null) {
			key = new Key(value);
			cache.put(key, value);
		}
		return key;
	}

	private static final String append = "}";
	private static final String prepend = "{";
	private static final ObjectReuseCache<Key> cache = new ObjectReuseCache<Key>();
	private final long key;

	/**
	 * Construct a new instance.
	 * 
	 * @param key
	 */
	private Key(long key) {
		this.key = key;
	}

	/**
	 * Return a long that represents the two's complement.
	 * 
	 * @return the long value.
	 */
	public long asLong() {
		return key;
	}

	/**
	 * Compares keys such that they are sorted in descending order.
	 */
	@Override
	public int compareTo(Key o) {
		return -1 * UnsignedLongs.compare(key, o.key);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Key) {
			final Key other = (Key) obj;
			return Objects.equal(this.key, other.key);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(key);
	}

	@Override
	public String toString() {
		return prepend + UnsignedLongs.toString(key) + append;
	}

	/**
	 * Return a byte array that represents this key.
	 * 
	 * @return the byte array.
	 */
	public byte[] getBytes() {
		return Longs.toByteArray(key);
	}

	/**
	 * Return a byte buffer that represents this key.
	 * 
	 * @return the byte buffer.
	 */
	public ByteBuffer getByteBuffer() {
		return ByteBuffers.toByteBuffer(key);
	}

}
