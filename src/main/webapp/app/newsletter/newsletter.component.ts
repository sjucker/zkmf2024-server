import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {NewsletterRecipientDTO} from "../rest";
import {NewsletterService} from "../service/newsletter.service";

@Component({
    selector: 'app-newsletter',
    templateUrl: './newsletter.component.html',
    styleUrls: ['./newsletter.component.sass']
})
export class NewsletterComponent implements OnInit {

    data: NewsletterRecipientDTO[] = [];
    loading = false;

    constructor(private newsletterService: NewsletterService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.loading = true;
        this.newsletterService.getAll().subscribe(
            value => {
                this.data = value;
            },
            error => {
                this.messageService.add({severity: 'error', summary: 'Fehler', detail: error.statusText, life: 3000})
            },
            () => {
                this.loading = false;
            }
        )
    }

    delete(dto: NewsletterRecipientDTO) {
        this.confirmationService.confirm({
            header: 'Löschen?',
            message: 'Wirklich löschen?',
            rejectLabel: 'Nein',
            acceptLabel: 'Ja',
            accept: () => {
                this.newsletterService.delete(dto.email).subscribe(
                    _ => {
                        this.data = this.data.filter(val => val.email !== dto.email);
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Gelöscht',
                            detail: 'Erfolgreich gelöscht',
                            life: 2000
                        });
                    },
                    error => {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Fehler',
                            detail: error.statusText,
                            life: 3000
                        });
                    }
                )
            }
        });
    }

    unsubscribe(dto: NewsletterRecipientDTO) {
        this.confirmationService.confirm({
            header: 'Abmelden?',
            message: 'Soll diese Email ausgetragen werden?',
            rejectLabel: 'Nein',
            acceptLabel: 'Ja',
            accept: () => {
                this.newsletterService.unsubscribe(dto.email).subscribe(
                    _ => {
                        this.loadData();
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Abgemeldet',
                            detail: 'Erfolgreich abgemeldet',
                            life: 2000
                        });
                    },
                    error => {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Fehler',
                            detail: error.statusText,
                            life: 3000
                        });
                    }
                )
            }
        })
    }

    subscribeCount(): number {
        return this.data.filter(dto => !dto.unsubscribedAt).length;
    }

}
