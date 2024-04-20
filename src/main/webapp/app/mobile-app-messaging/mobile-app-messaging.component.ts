import {Component, signal} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {DynamicDialogRef} from "primeng/dynamicdialog";
import {MessageSendDTO, MessageType} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {MessagingService} from "../service/messaging.service";

@Component({
    selector: 'app-mobile-app-messaging',
    templateUrl: './mobile-app-messaging.component.html',
    styleUrl: './mobile-app-messaging.component.sass'
})
export class MobileAppMessagingComponent {

    dto: MessageSendDTO = {
        type: MessageType.GENERAL,
        title: '',
        body: '',
        route: '',
    };

    sending = signal(false);

    constructor(private readonly ref: DynamicDialogRef,
                private readonly service: MessagingService,
                private readonly messageService: MessageService,
                private readonly confirmationService: ConfirmationService,
                private authenticationService: AuthenticationService) {
    }

    send() {
        this.confirmationService.confirm({
            header: "Senden?",
            message: "Soll die Notification versandt werden?",
            acceptLabel: "Ja",
            rejectLabel: "Nein",
            accept: () => this.doSend(),
        });
    }

    private doSend() {
        this.sending.set(true);
        this.service.sendNotification(this.dto).subscribe({
            next: () => {
                this.sending.set(false);
                this.dto = {
                    type: MessageType.GENERAL,
                    title: '',
                    body: '',
                    route: '',
                };
                this.messageService.add({
                    severity: 'success',
                    summary: 'Gesendet',
                    detail: `Notification wurde versandt.`,
                    life: 5000,
                });
            },
            error: () => {
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: 'Ein Fehler ist aufgetreten',
                    life: 3000
                });
                this.sending.set(false);
            },
        });
    }

    close() {
        this.ref.close();
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }

    isValid() {
        return this.dto.title.length > 0
            && this.dto.body.length > 0
            && this.dto.route.length > 0;
    }

    get typeOptions(): MessageType[] {
        return [MessageType.GENERAL, MessageType.EMERGENCY];
    }
}
