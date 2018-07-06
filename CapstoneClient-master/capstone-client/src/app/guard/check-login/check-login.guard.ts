import {Inject, Injectable} from '@angular/core';
import {CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {LoginService} from '../../service/login/login.service';
import {BaseService} from '../../service/base-service/base.service';
import { DOCUMENT } from '@angular/common';

@Injectable()
export class CheckLoginGuard implements CanActivate {
  constructor(private loginService: LoginService, private baseService: BaseService,private router: Router,
              @Inject(DOCUMENT) private document: Document) {}
  canActivate() {
    const status = this.loginService.checkLogged();
    if (!status) {
       this.baseService.showLoginForm();
       this.router.navigate([this.router.url]);
       this.loginService.setUrl(this.document.location.pathname);
      console.log(this.router.url);
      console.log(this.router.routerState.snapshot.url);
      console.log(this.document.location.pathname);
    }
    return status;
  }
}
