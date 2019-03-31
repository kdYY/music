package com.ebook.util.impl.id;

import com.google.common.base.Objects;

import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 4byte时间戳 + 3byte机器标识 + 2byte PID + 3byte自增id
 * @author gary
 *
 */
public class ObjectId implements Comparable<ObjectId>, java.io.Serializable {
 
    private final int time;
    private final int machine;
    private final int inc;
    private boolean snew;
    private static final int GENMACHINE;
 
    private static AtomicInteger nextInc = new AtomicInteger((new Random()).nextInt());
 
    private static final long serialVersionUID = -4415279469780082174L;
 
    private static final Logger LOGGER = Logger.getLogger("org.bson.ObjectId");
 
    /**
     * Create a new object id.
     */
    public ObjectId() {
        time = (int) (System.currentTimeMillis() / 1000);
        machine = GENMACHINE;
        inc = nextInc.getAndIncrement();
        snew = true;
    }
 
    /**
     * Gets a new object id.
     *
     * @return the new id
     */
    public static ObjectId get() {
        return new ObjectId();
    }
 
    /**
     * Checks if a string could be an {@code ObjectId}.
     *
     * @param s a potential ObjectId as a String.
     * @return whether the string could be an object id
     * @throws IllegalArgumentException if hexString is null
     */
    public static boolean isValid(String s) {
        if (s == null) {
			return false;
		}
 
        final int len = s.length();
        int validlen = 24;
		if (len != validlen) {
			return false;
		}
 
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
				continue;
			}
            if (c >= 'a' && c <= 'f') {
				continue;
			}
            if (c >= 'A' && c <= 'F') {
				continue;
			}
 
            return false;
        }
 
        return true;
    }
 
 
    /**
     * Converts this instance into a 24-byte hexadecimal string representation.
     *
     * @return a string representation of the ObjectId in hexadecimal format
     */
    public String toHexString() {
        final StringBuilder buf = new StringBuilder(24);
        for (final byte b : toByteArray()) {
            buf.append(String.format("%02x", b & 0xff));
        }
        return buf.toString();
    }
 
    /**
     * Convert to a byte array.  Note that the numbers are stored in big-endian order.
     *
     * @return the byte array
     */
    public byte[] toByteArray() {
        byte b[] = new byte[12];
        ByteBuffer bb = ByteBuffer.wrap(b);
        // by default BB is big endian like we need
        bb.putInt(time);
        bb.putInt(machine);
        bb.putInt(inc);
        return b;
    }
 
    private int compareUnsigned(final int i, final int j) {
        long li = 0xFFFFFFFFL;
        li = i & li;
        long lj = 0xFFFFFFFFL;
        lj = j & lj;
        long diff = li - lj;
        if (diff < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}
        if (diff > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
        return (int) diff;
    }
 
    @Override
    public int compareTo(ObjectId id) {
        if (id == null) {
			return -1;
		}
 
        int x = compareUnsigned(time, id.time);
        if (x != 0) {
			return x;
		}
 
        x = compareUnsigned(machine, id.machine);
        if (x != 0) {
			return x;
		}
 
        return compareUnsigned(inc, id.inc);
    }
 
    /**
     * Gets the timestamp (number of seconds since the Unix epoch).
     *
     * @return the timestamp
     */
    public int getTimestamp() {
        return time;
    }
 
    /**
     * Gets the timestamp as a {@code Date} instance.
     *
     * @return the Date
     */
    public Date getDate() {
        return new Date(time * 1000L);
    }
 
 
    /**
     * Gets the current value of the auto-incrementing counter.
     *
     * @return the current counter value.
     */
    public static int getCurrentCounter() {
        return nextInc.get();
    }
 
 
    static {
 
        try {
            // build a 2-byte machine piece based on NICs info
            int machinePiece;
            {
                try {
                    StringBuilder sb = new StringBuilder();
                    Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
                    while (e.hasMoreElements()) {
                        NetworkInterface ni = e.nextElement();
                        sb.append(ni.toString());
                    }
                    machinePiece = sb.toString().hashCode() << 16;
                } catch (Throwable e) {
                    // exception sometimes happens with IBM JVM, use random
                    LOGGER.log(Level.WARNING, e.getMessage(), e);
                    machinePiece = (new Random().nextInt()) << 16;
                }
                LOGGER.fine("machine piece post: " + Integer.toHexString(machinePiece));
            }
 
            // add a 2 byte process piece. It must represent not only the JVM but the class loader.
            // Since static var belong to class loader there could be collisions otherwise
            final int processPiece;
            {
                int processId = new Random().nextInt();
                try {
                    processId = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().hashCode();
                } catch (Throwable t) {
                }
 
                ClassLoader loader = ObjectId.class.getClassLoader();
                int loaderId = loader != null ? System.identityHashCode(loader) : 0;
 
                StringBuilder sb = new StringBuilder();
                sb.append(Integer.toHexString(processId));
                sb.append(Integer.toHexString(loaderId));
                processPiece = sb.toString().hashCode() & 0xFFFF;
                LOGGER.fine("process piece: " + Integer.toHexString(processPiece));
            }
 
            GENMACHINE = machinePiece | processPiece;
            LOGGER.fine("machine : " + Integer.toHexString(GENMACHINE));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
 
    }
 
    @SuppressWarnings("static-access")
	@Override
    public boolean equals(Object o) {
        if (this == o) {
			return true;
		}
        if (o == null || getClass() != o.getClass()) {
			return false;
		}
 
        ObjectId that = (ObjectId) o;
 
        long serialversionuid2 = ObjectId.serialVersionUID;
		Logger logger2 = ObjectId.LOGGER;
		AtomicInteger nextInc2 = ObjectId.nextInc;
		int genmachine2 = ObjectId.GENMACHINE;
		return Objects.equal(serialVersionUID, serialversionuid2) &&
                Objects.equal(LOGGER, logger2) &&
                Objects.equal(this.time, that.time) &&
                Objects.equal(this.machine, that.machine) &&
                Objects.equal(this.inc, that.inc) &&
                Objects.equal(this.snew, that.snew) &&
                Objects.equal(nextInc, nextInc2) &&
                Objects.equal(GENMACHINE, genmachine2);
    }
 
    @Override
    public int hashCode() {
        return Objects.hashCode(serialVersionUID, LOGGER, time, machine, inc, snew,
                nextInc, GENMACHINE);
    }
 
    public static void main(String[] args) {
        System.out.println(new ObjectId().toHexString());
        System.out.println(new ObjectId().toHexString());
        System.out.println(new ObjectId().toHexString());
    }
}