import {HttpClientModule} from "@angular/common/http";
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ConfirmationService, MessageService} from "primeng/api";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {MenubarModule} from "primeng/menubar";
import {RippleModule} from "primeng/ripple";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UmfrageComponent} from './umfrage/umfrage.component';

@NgModule({
    declarations: [
        AppComponent,
        UmfrageComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        HttpClientModule,
        MenubarModule,
        TableModule,
        ToastModule,
        ConfirmDialogModule,
        RippleModule,
    ],
    providers: [MessageService, ConfirmationService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
