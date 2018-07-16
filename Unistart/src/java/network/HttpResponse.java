/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.List;

/**
 *
 * @author TNT
 */
public class HttpResponse {
    private Integer status;
    private String redirectTo;
    private String content;
    private List<String> cookies;

    public HttpResponse(Integer status, String redirectTo, String content, List<String> cookies) {
        this.status = status;
        this.redirectTo = redirectTo;
        this.content = content;
        this.cookies = cookies;
    }

    public HttpResponse() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }
    
    
    
}
