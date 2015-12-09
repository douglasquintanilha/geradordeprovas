package br.com.caelum.geradordeprovas.configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class TempoSessao implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(72*3600);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
    }
}

