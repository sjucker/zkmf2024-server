import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UmfrageComponent} from "./umfrage/umfrage.component";

const routes: Routes = [
    {path: 'umfrage', component: UmfrageComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule {

}
