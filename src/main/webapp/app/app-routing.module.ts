import {inject, NgModule} from '@angular/core';
import {CanActivateFn, RouterModule, Routes} from '@angular/router';
import {HelferComponent} from "./helfer/helfer.component";
import {LoginComponent} from "./login/login.component";
import {NewsletterComponent} from "./newsletter/newsletter.component";
import {AuthenticationGuard} from "./service/authentication.guard";
import {VereineComponent} from "./vereine/vereine.component";

export const LOGIN_ROUTE = 'login'
export const VEREINE_ROUTE = 'vereine'
export const NEWSLETTER_ROUTE = 'newsletter'
export const HELFER_ROUTE = 'helfer'

const canActiveFn: CanActivateFn = () => inject(AuthenticationGuard).canActivate();

const routes: Routes = [
    {
        path: '',
        component: VereineComponent,
        canActivate: [canActiveFn],
    },
    {
        path: VEREINE_ROUTE,
        component: VereineComponent,
        canActivate: [canActiveFn],
    },
    {
        path: NEWSLETTER_ROUTE,
        component: NewsletterComponent,
        canActivate: [canActiveFn],
    },
    {
        path: HELFER_ROUTE, component: HelferComponent, canActivate: [canActiveFn],
    },
    {
        path: LOGIN_ROUTE, component: LoginComponent,
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
