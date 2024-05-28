import {registerLocaleData} from "@angular/common";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import localeDe from '@angular/common/locales/de';
import localeDeExtra from '@angular/common/locales/extra/de';
import {LOCALE_ID, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ConfirmationService, MessageService} from "primeng/api";
import {CalendarModule} from "primeng/calendar";
import {CheckboxModule} from "primeng/checkbox";
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {DialogModule} from "primeng/dialog";
import {DropdownModule} from "primeng/dropdown";
import {DialogService, DynamicDialogModule} from "primeng/dynamicdialog";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {ListboxModule} from "primeng/listbox";
import {MenuModule} from "primeng/menu";
import {MenubarModule} from "primeng/menubar";
import {OrderListModule} from "primeng/orderlist";
import {PanelModule} from "primeng/panel";
import {PasswordModule} from "primeng/password";
import {ProgressBarModule} from "primeng/progressbar";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {RippleModule} from "primeng/ripple";
import {TableModule} from "primeng/table";
import {TabViewModule} from "primeng/tabview";
import {ToastModule} from "primeng/toast";
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BroadcastComponent} from './broadcast/broadcast.component';
import {EmergencyDialogComponent} from "./emergency-dialog/emergency-dialog.component";
import {EmergencyComponent} from "./emergency/emergency.component";
import {ErrataComponent} from './errata/errata.component';
import {HelferComponent} from './helfer/helfer.component';
import {AuthenticationInterceptor} from "./interceptor/authentication-interceptor.service";
import {JuryLoginCreateComponent} from './jury-login-create/jury-login-create.component';
import {JuryComponent} from './jury/jury.component';
import {LoginComponent} from './login/login.component';
import {MobileAppCreateComponent} from "./mobile-app-create/mobile-app-create.component";
import {MobileAppEditComponent} from "./mobile-app-edit/mobile-app-edit.component";
import {MobileAppMessagingComponent} from "./mobile-app-messaging/mobile-app-messaging.component";
import {MobileAppComponent} from "./mobile-app/mobile-app.component";
import {NewsletterComponent} from './newsletter/newsletter.component';
import {StageSetupComponent} from "./stage-setup/stage-setup.component";
import {TimetableAssignJudgesComponent} from './timetable-assign-judges/timetable-assign-judges.component';
import {TimetableEntryEditComponent} from './timetable-entry-edit/timetable-entry-edit.component';
import {TimetableComponent} from './timetable/timetable.component';
import {UserCreateComponent} from './user-create/user-create.component';
import {UsersComponent} from './users/users.component';
import {VereinCommentsComponent} from './verein-comments/verein-comments.component';
import {VereinDetailComponent} from './verein-detail/verein-detail.component';
import {VereinMessagesComponent} from './verein-messages/verein-messages.component';
import {VereineComponent} from './vereine/vereine.component';

@NgModule({
    declarations: [
        AppComponent,
        NewsletterComponent,
        HelferComponent,
        LoginComponent,
        VereineComponent,
        VereinDetailComponent,
        UsersComponent,
        UserCreateComponent,
        VereinCommentsComponent,
        JuryComponent,
        JuryLoginCreateComponent,
        TimetableComponent,
        VereinMessagesComponent,
        BroadcastComponent,
        TimetableEntryEditComponent,
        ErrataComponent,
        TimetableAssignJudgesComponent,
        StageSetupComponent,
        MobileAppComponent,
        MobileAppCreateComponent,
        MobileAppEditComponent,
        MobileAppMessagingComponent,
        EmergencyComponent,
        EmergencyDialogComponent,
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
        ProgressBarModule,
        ListboxModule,
        InputTextareaModule,
        PasswordModule,
        OrderListModule,
        CalendarModule,
        DropdownModule,
        MenuModule,
        CheckboxModule
    ],
    providers: [
        MessageService,
        ConfirmationService,
        DialogService,
        {provide: HTTP_INTERCEPTORS, useClass: AuthenticationInterceptor, multi: true},
        {provide: LOCALE_ID, useValue: 'de-DE'}
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
    constructor() {
        registerLocaleData(localeDe, 'de-DE', localeDeExtra);
    }
}
