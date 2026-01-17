package me.pinitnotification.infrastructure.persistence;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class UuidV7Generator {
    private UuidV7Generator() {
    }

    public static UUID generate() {
        byte[] bytes = new byte[16];
        long millis = System.currentTimeMillis();

        bytes[0] = (byte) (millis >>> 40);
        bytes[1] = (byte) (millis >>> 32);
        bytes[2] = (byte) (millis >>> 24);
        bytes[3] = (byte) (millis >>> 16);
        bytes[4] = (byte) (millis >>> 8);
        bytes[5] = (byte) millis;

        int randA = ThreadLocalRandom.current().nextInt(1 << 12);
        bytes[6] = (byte) (0x70 | ((randA >>> 8) & 0x0F));
        bytes[7] = (byte) randA;

        long randB = ThreadLocalRandom.current().nextLong();
        bytes[8] = (byte) (randB >>> 56);
        bytes[9] = (byte) (randB >>> 48);
        bytes[10] = (byte) (randB >>> 40);
        bytes[11] = (byte) (randB >>> 32);
        bytes[12] = (byte) (randB >>> 24);
        bytes[13] = (byte) (randB >>> 16);
        bytes[14] = (byte) (randB >>> 8);
        bytes[15] = (byte) randB;

        bytes[8] = (byte) ((bytes[8] & 0x3F) | 0x80);

        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | (bytes[i] & 0xFF);
        }
        for (int i = 8; i < 16; i++) {
            lsb = (lsb << 8) | (bytes[i] & 0xFF);
        }
        return new UUID(msb, lsb);
    }
}
