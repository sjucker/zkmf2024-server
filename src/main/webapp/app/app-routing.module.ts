import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HelferComponent} from "./helfer/helfer.component";
import {NewsletterComponent} from "./newsletter/newsletter.component";
import {UmfrageComponent} from "./umfrage/umfrage.component";

export const UMFRAGE_ROUTE = 'umfrage'
export const NEWSLETTER_ROUTE = 'newsletter'
export const HELFER_ROUTE = 'helfer'

const routes: Routes = [
    {path: UMFRAGE_ROUTE, component: UmfrageComponent},
    {path: NEWSLETTER_ROUTE, component: NewsletterComponent},
    {path: HELFER_ROUTE, component: HelferComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
