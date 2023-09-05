import {inject, NgModule} from '@angular/core';
import {CanActivateFn, RouterModule, Routes} from '@angular/router';
import {HelferComponent} from "./helfer/helfer.component";
import {JuryComponent} from "./jury/jury.component";
import {LoginComponent} from "./login/login.component";
import {NewsletterComponent} from "./newsletter/newsletter.component";
import {AuthenticationGuard} from "./service/authentication.guard";
import {TimetableComponent} from "./timetable/timetable.component";
import {UsersComponent} from "./users/users.component";
import {VereineComponent} from "./vereine/vereine.component";

export const LOGIN_ROUTE = 'login';
export const VEREINE_ROUTE = 'vereine';
export const NEWSLETTER_ROUTE = 'newsletter';
export const HELFER_ROUTE = 'helfer';
export const USERS_ROUTE = 'users';
export const JURY_ROUTE = 'jury';
export const TIMETABLE_ROUTE = 'zeitplan';

const canActivateFn: CanActivateFn = () => inject(AuthenticationGuard).canActivate();

const routes: Routes = [
    {
        path: '',
        component: VereineComponent,
        canActivate: [canActivateFn],
    },
    {
        path: VEREINE_ROUTE,
        component: VereineComponent,
        canActivate: [canActivateFn],
    },
    {
        path: NEWSLETTER_ROUTE,
        component: NewsletterComponent,
        canActivate: [canActivateFn],
    },
    {
        path: HELFER_ROUTE,
        component: HelferComponent,
        canActivate: [canActivateFn],
    },
    {
        path: USERS_ROUTE,
        component: UsersComponent,
        canActivate: [canActivateFn],
    },
    {
        path: JURY_ROUTE,
        component: JuryComponent,
        canActivate: [canActivateFn],
    },
    {
        path: TIMETABLE_ROUTE,
        component: TimetableComponent,
        canActivate: [canActivateFn],
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
