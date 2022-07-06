import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {AuthenticationService} from "../service/authentication.service";

@Injectable()
export class AuthenticationInterceptor implements HttpInterceptor {

    constructor(private authenticationService: AuthenticationService) {
    }

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
        const token = this.authenticationService.getAuthorizationToken();
        if (token) {
            return next.handle(request.clone({
                setHeaders: {Authorization: 'Bearer ' + token}
            }));
        }

        return next.handle(request);
    }

}
