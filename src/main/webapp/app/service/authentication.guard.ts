import {Injectable} from '@angular/core';
import {Router, UrlTree} from '@angular/router';
import {Observable} from 'rxjs';
import {LOGIN_ROUTE} from "../app-routing.module";
import {AuthenticationService} from "./authentication.service";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationGuard {

    constructor(private authenticationService: AuthenticationService,
                private router: Router) {
    }

    canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (this.authenticationService.isAdmin()) {
            return true;
        } else {
            return this.router.parseUrl(LOGIN_ROUTE);
        }
    }

}
