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
public class HttpResponse {

    private Integer status;
    private String redirectTo;
    private Object content;
    private HashMap<String, Object> metas;

    public HttpResponse() {
    }

    public HttpResponse(Integer status, String redirectTo, Object content, HashMap<String, Object> metas) {
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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public HashMap<String, Object> getMetas() {
        return metas;
    }

    public void setMetas(HashMap<String, Object> metas) {
        this.metas = metas;
    }

}
