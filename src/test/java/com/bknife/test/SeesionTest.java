package com.bknife.test;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.bknife.web.Sessions;

public class SeesionTest {
    
    @Test
    public void testNewSessionId() throws Exception
    {
        Set<String> values = new HashSet<String>();
        for (int i = 0; i < 5000; ++i) {
            String sessionId = Sessions.newSessionId();
            assertFalse(values.contains(sessionId));
            values.add(sessionId);
        }
    }
}
