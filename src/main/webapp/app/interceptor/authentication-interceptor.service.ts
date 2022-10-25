import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {catchError, Observable, of, throwError} from "rxjs";
import {LOGIN_ROUTE} from "../app-routing.module";
import {AuthenticationService} from "../service/authentication.service";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

    constructor(private authenticationService: AuthenticationService,
                private router: Router) {
    }

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        return next.handle(this.addAuthToken(request)).pipe(
            catchError((error: HttpErrorResponse) => this.handleAuthError(error))
        );
    }

    addAuthToken(request: HttpRequest<any>): HttpRequest<any> {
        const token = this.authenticationService.getAuthorizationToken();
        if (!token) {
            return request;
        }
        return request.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`,
            },
        });
    }

    private handleAuthError(err: HttpErrorResponse): Observable<any> {
        if (err.status === 401 || err.status === 403) {
            this.authenticationService.logout();
            this.router.navigate([LOGIN_ROUTE]);
            return of(err.message);
        }
        return throwError(() => err);
    }

}
