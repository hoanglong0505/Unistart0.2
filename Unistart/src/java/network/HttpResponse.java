/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import com.google.gson.JsonElement;
import java.util.HashMap;

/**
 *
 * @author TNT
 */
public class HttpResponse<E> {

    private Integer status;
    private String redirectTo;
    private E content;
    private HashMap<String, JsonElement> metas;

    public HttpResponse() {
    }

    public HttpResponse(Integer status, String redirectTo, E content, HashMap<String, JsonElement> metas) {
        this.status = status;
        this.redirectTo = redirectTo;
        this.content = content;
        this.metas = metas;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRedirectTo() {
        return redirectTo;
    }

    public void setRedirectTo(String redirectTo) {
        this.redirectTo = redirectTo;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public HashMap<String, JsonElement> getMetas() {
        return metas;
    }

    public void setMetas(HashMap<String, JsonElement> metas) {
        this.metas = metas;
    }

}
