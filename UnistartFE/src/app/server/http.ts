import { CookieManager } from './cookie-manager';

export class HttpRequest {

    private uniSession: Session;
    content: Object;

    constructor() {
    }

    getSession(create: boolean): Session {
        var uniSession = new Session();
        if (uniSession.isActive()) {
            this.uniSession = uniSession
            return uniSession;
        }
        if (create) {
            uniSession.createSession();
            this.uniSession = uniSession
            return uniSession;
        }
        return null;
    }

}

export class Session {
    private uniSession: Object;
    isChanged: boolean = false;

    constructor() {
        var uniCookie = CookieManager.getCookie('UNISESSION');
        if (uniCookie)
            this.uniSession = JSON.parse(uniCookie)['uniSession'];
    }

    createSession(): void {
        this.uniSession = new Object();
        this.pushToCookie();
    }

    get(key: string) {
        return this.uniSession[key];
    }

    set(key: string, value: Object) {
        this.uniSession[key] = value;
        this.pushToCookie();
    }

    remove(key: string) {
        delete this.uniSession[key];
        this.pushToCookie();
    }

    pushToCookie() {
        CookieManager.setCookie('UNISESSION=' + JSON.stringify(this));
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
    content: Object;
    metas: Map<String, Object>;
    constructor() { }
}