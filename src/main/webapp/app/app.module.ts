import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ConfirmationService, MessageService} from "primeng/api";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {DialogModule} from "primeng/dialog";
import {DialogService, DynamicDialogModule} from "primeng/dynamicdialog";
import {InputTextModule} from "primeng/inputtext";
import {MenubarModule} from "primeng/menubar";
import {PanelModule} from "primeng/panel";
import {ProgressBarModule} from "primeng/progressbar";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {RippleModule} from "primeng/ripple";
import {TableModule} from "primeng/table";
import {TabViewModule} from "primeng/tabview";
import {ToastModule} from "primeng/toast";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HelferComponent} from './helfer/helfer.component';
import {AuthenticationInterceptor} from "./interceptor/authentication-interceptor.service";
import {LoginComponent} from './login/login.component';
import {NewsletterComponent} from './newsletter/newsletter.component';
import {VereinDetailComponent} from './verein-detail/verein-detail.component';
import {VereineComponent} from './vereine/vereine.component';

@NgModule({
    declarations: [
        AppComponent,
        NewsletterComponent,
        HelferComponent,
        LoginComponent,
        VereineComponent,
        VereinDetailComponent
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
        ProgressSpinnerModule,
        DialogModule,
        DynamicDialogModule,
        TabViewModule,
        ProgressBarModule
    ],
    providers: [
        MessageService,
        ConfirmationService,
        DialogService,
        {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true},
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
