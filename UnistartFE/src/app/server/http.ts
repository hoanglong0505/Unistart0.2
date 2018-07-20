import { CookieManager } from './cookie-manager';

export class HttpRequest {

    uniSession: Session;
    content: any;

    constructor() {
    }

    getSession(create: boolean): Session {
        var uniSession = new Session();
        if (uniSession.isActive()) {
            this.uniSession = uniSession;
            return uniSession;
        }
        if (create) {
            uniSession.createSession();
            this.uniSession = uniSession;
            return uniSession;
        }
        return null;
    }

}

export class Session {
    uniSession: Array<any>;
    isChanged: boolean = false;

    constructor() {
        var uniCookie = CookieManager.getCookie('UNISESSION');
        if (uniCookie)
            this.uniSession = JSON.parse(uniCookie)['uniSession'];
    }

    createSession(): void {
        this.uniSession = new Array();
        this.pushToCookie();
    }

    getItem(key: string) {
        return this.uniSession[key];
    }

    setItem(key: string, value: any) {
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
    content: any;
    metas: Array<any>;
    constructor() { }
}