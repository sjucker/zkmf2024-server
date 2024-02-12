import {Component} from '@angular/core';
import {MessageService} from "primeng/api";
import {DynamicDialogConfig} from "primeng/dynamicdialog";
import {VereinMessageDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {VereineService} from "../service/vereine.service";

export interface VereinMessagesInput {
    id: number
}

@Component({
    selector: 'app-verein-messages',
    templateUrl: './verein-messages.component.html',
    styleUrls: ['./verein-messages.component.sass']
})
export class VereinMessagesComponent {
    messages: VereinMessageDTO[] = [];
    loading = true;
    saving = false;

    message = '';

    constructor(private readonly config: DynamicDialogConfig<VereinMessagesInput>,
                private authenticationService: AuthenticationService,
                private vereineService: VereineService,
                private messageService: MessageService) {
        if (config.data) {
            this.loading = true;
            this.vereineService.getMessages(config.data.id).subscribe({
                next: value => {
                    this.messages = value;
                    this.loading = false
                },
                error: () => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Fehler',
                        detail: 'Nachrichten konnten nicht geladen werden',
                        life: 3000
                    });
                    this.loading = false
                }
            });
        }
    }

    saveMessage() {
        if (this.config.data) {
            this.saving = true;
            this.vereineService.saveMessag(this.config.data.id, this.message).subscribe({
                next: value => {
                    this.messages = [...this.messages, value];
                    this.message = '';
                    this.saving = false;
                },
                error: err => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Fehler',
                        detail: 'Nachricht konnte nicht gesendet werden',
                        life: 3000,
                    });
                    console.error(err);
                    this.saving = false;
                }
            });
        }
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }
}
