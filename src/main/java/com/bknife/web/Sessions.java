package com.bknife.web;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;

import com.bknife.base.util.Strings;

public class Sessions {
    private static final long serverTime = System.nanoTime();
    private static ThreadLocal<Session> session = new ThreadLocal<Session>();
    private static SecureRandom random = new SecureRandom(ByteBuffer.wrap(new byte[8]).putLong(serverTime).array());
    private static AtomicLong sequence = new AtomicLong(random.nextLong());
    private static final long startTime = random.nextLong();
    private static final long startThreadId = random.nextLong();

    public static String newSessionId() {
        String timeStr = Strings.padStart(Long.toHexString(System.nanoTime() - serverTime + startTime), 16, '0');
        String sequenceStr = Strings.padStart(Long.toHexString(sequence.incrementAndGet()), 16, '0');
        String threadIdStr = Strings.padStart(Long.toHexString(Thread.currentThread().getId() + startThreadId), 16, '0');
        String randStr = Strings.padStart(Long.toHexString(random.nextLong()), 16, '0');
        return sequenceStr + timeStr + threadIdStr + randStr;
    }

    public static Session getSession() {
        return session.get();
    }

    public static void main(String[] args) throws InterruptedException {
        for (;;) {
            System.out.println(Sessions.newSessionId());
            Thread.sleep(1);
        }
    }
}
