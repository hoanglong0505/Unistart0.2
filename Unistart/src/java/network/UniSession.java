/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.HashMap;

/**
 *
 * @author TNT
 */
public class UniSession {

    private HashMap<String, Object> uniSession;
    private boolean isChanged = false;

    public UniSession() {
    }

    public UniSession(HashMap<String, Object> uniSession) {
        this.uniSession = uniSession;
    }

//    public HashMap<String, Object> getUniSession() {
//        return uniSession;
//    }
    public void setUniSession(HashMap<String, Object> uniSession) {
        this.uniSession = uniSession;
    }

    public Object get(String key) {
        return uniSession.get(key);
    }

    public void set(String key, Object value) {
        uniSession.put(key, value);
        isChanged = true;
    }

    public void remove(String key) {
        uniSession.remove(key);
        isChanged = true;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public boolean isActive() {
        if (uniSession != null) {
            return true;
        }
        return false;
    }

    public void createSession() {
        this.uniSession = new HashMap<>();
        isChanged = true;
    }

}
