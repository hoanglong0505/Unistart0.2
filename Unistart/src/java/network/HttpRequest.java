/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

/**
 *
 * @author TNT
 */
public class HttpRequest<E> {

    private Session uniSession;
    private E content;

    public HttpRequest() {
    }

    public HttpRequest(Session uniSession, E content) {
        this.uniSession = uniSession;
        this.content = content;
    }

    public void setUniSession(Session uniSession) {
        this.uniSession = uniSession;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public Session getSession(boolean create) {
        if (uniSession.isActive()) {
            return uniSession;
        }
        if (create) {
            uniSession.createSession();
            return uniSession;
        }
        return null;
    }

}
