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
public class HttpRequest {

    private UniSession uniSession;
    private Object content;

    public HttpRequest() {
    }

    public HttpRequest(UniSession uniSession, Object content) {
        this.uniSession = uniSession;
        this.content = content;
    }

//    public void setUniSession(UniSession uniSession) {
//        this.uniSession = uniSession;
//    }
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public UniSession getSession(boolean create) {
        UniSession tempUni = new UniSession();
        if (uniSession.isActive()) {
            this.uniSession = tempUni;
            return uniSession;
        }
        if (create) {
            uniSession.createSession();
            this.uniSession = tempUni;
            return uniSession;
        }
        return null;
    }

}
