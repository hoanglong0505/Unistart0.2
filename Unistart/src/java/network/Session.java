/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import com.google.gson.JsonElement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TNT
 */
public class Session {

    private Map<String, JsonElement> uniSession;
    private boolean isChanged = false;

    public Session() {
        this.uniSession = new HashMap();
        isChanged = true;
    }

    public Session(Map<String, JsonElement> uniSession) {
        this.uniSession = uniSession;
    }

    public Map<String, JsonElement> getSession() {
        return uniSession;
    }

    public void setSession(Map<String, JsonElement> uniSession) {
        this.uniSession = uniSession;
    }

    public JsonElement get(String key) {
        return uniSession.get(key);
    }

    public void set(String key, JsonElement value) {
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
        return uniSession != null;
    }

    public void createSession() {
        this.uniSession = new HashMap();
        isChanged = true;
    }

}
