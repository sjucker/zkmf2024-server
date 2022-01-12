import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ConfirmationService, MessageService} from "primeng/api";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {InputTextModule} from "primeng/inputtext";
import {MenubarModule} from "primeng/menubar";
import {PanelModule} from "primeng/panel";
import {RippleModule} from "primeng/ripple";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HelferComponent} from './helfer/helfer.component';
import {AuthenticationInterceptor} from "./interceptor/authentication-interceptor.service";
import {LoginComponent} from './login/login.component';
import {NewsletterComponent} from './newsletter/newsletter.component';
import {UmfrageComponent} from './umfrage/umfrage.component';

@NgModule({
    declarations: [
        AppComponent,
        UmfrageComponent,
        NewsletterComponent,
        HelferComponent,
        LoginComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        MenubarModule,
        TableModule,
        ToastModule,
        ConfirmDialogModule,
        RippleModule,
        ReactiveFormsModule,
        PanelModule,
        InputTextModule,
    ],
    providers: [
        MessageService,
        ConfirmationService,
        {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true},
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
