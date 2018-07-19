import { CookieManager } from './cookie-manager';

export class HttpRequest {
    getSession(create: boolean): Session {
        var uniSession = new Session();
        if (uniSession.isActive())
            return uniSession;
        if (create) {
            uniSession.createSession();
            return uniSession;
        }
        return null;
    }

}

export class Session {
    private uniSession: Object;

    constructor() {
        var uniCookie = CookieManager.getCookie('UNISESSION');
        if (uniCookie)
            this.uniSession = JSON.parse(uniCookie);
    }

    createSession(): void {
        this.uniSession = new Object();
        CookieManager.setCookie('UNISESSION=' + JSON.stringify(this.uniSession));
    }

    get(key: string) {
        return this.uniSession[key];
    }

    set(key: string, value: Object) {
        this.uniSession[key] = value;
        this.pushToCookie();
    }

    remove(key: string) {
        this.uniSession[key] = null;
        this.pushToCookie();
    }

    private pushToCookie() {
        CookieManager.setCookie('UNISESSION=' + JSON.stringify(this.uniSession));
    }

    isActive(): boolean {
        if (this.uniSession)
            return true;
        return false;
    }

}

export class HttpResponse {
    status: number;
    redirectTo: string;
    content: string;
    cookies: string[];
    constructor() { }
}