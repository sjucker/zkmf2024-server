import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from "./app.component";
import {HelferComponent} from "./helfer/helfer.component";
import {LoginComponent} from "./login/login.component";
import {NewsletterComponent} from "./newsletter/newsletter.component";
import {AuthenticationGuard} from "./service/authentication.guard";
import {UmfrageComponent} from "./umfrage/umfrage.component";

export const LOGIN_ROUTE = 'login'
export const UMFRAGE_ROUTE = 'umfrage'
export const NEWSLETTER_ROUTE = 'newsletter'
export const HELFER_ROUTE = 'helfer'

const routes: Routes = [
    {
        path: '', component: UmfrageComponent, canActivate: [AuthenticationGuard], // TODO add landing page after login
    },
    {
        path: UMFRAGE_ROUTE, component: UmfrageComponent, canActivate: [AuthenticationGuard],
    },
    {
        path: NEWSLETTER_ROUTE, component: NewsletterComponent, canActivate: [AuthenticationGuard],
    },
    {
        path: HELFER_ROUTE, component: HelferComponent, canActivate: [AuthenticationGuard],
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
