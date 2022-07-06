package com.bknife.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.bknife.web.impl.HttpSession;

public class SessionManager {
    private Map<String, Session> sessions = new HashMap<String, Session>();
    private long inactiveInterval = 30 * 60 * 1000;
    private LinkedList<Session> checkSessions = new LinkedList<Session>();

    public long getInactiveInterval() {
        return inactiveInterval;
    }

    public SessionManager setInactiveInterval(long inactiveInterval) {
        this.inactiveInterval = inactiveInterval;
        return this;
    }

    public Session getSession(String sessionId)
    {
        long currentTimeMillis = System.currentTimeMillis();
        HttpSession session;
        synchronized(sessions)
        {
            session = (HttpSession)sessions.get(sessionId);
            if(session != null && currentTimeMillis >= session.getExpiredTime())
            {
                sessions.remove(session.getId());
                return null;
            }
        }
        session.setExpireTime(currentTimeMillis + inactiveInterval);
        return session;
    }

    public Session createSession() {
        HttpSession session = new HttpSession();
        session.setExpireTime(System.currentTimeMillis() + inactiveInterval);
        synchronized (sessions) {
            sessions.put(session.getId(), session);
        }
        return session;
    }

    public void checkInactive() {
        checkSessions.clear();
        synchronized (sessions) {
            checkSessions.addAll(sessions.values());
        }

        long currentTimeMillis = System.currentTimeMillis();

        Iterator<Session> iterator = checkSessions.iterator();
        while (iterator.hasNext()) {
            Session session = iterator.next();
            if (currentTimeMillis < session.getExpiredTime())
                iterator.remove();
        }

        synchronized (sessions) {
            for (Session session : checkSessions)
                sessions.remove(session.getId());
        }
    }
}
