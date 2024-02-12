import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {ErrataDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {ErrataService} from "../service/errata.service";

@Component({
    selector: 'app-errata',
    templateUrl: './errata.component.html',
    styleUrls: ['./errata.component.sass']
})
export class ErrataComponent implements OnInit {

    errata: ErrataDTO[] = [];

    saving = false;
    sending = false;

    constructor(private authenticationService: AuthenticationService,
                private service: ErrataService,
                private readonly messageService: MessageService,
                private readonly confirmationService: ConfirmationService) {
    }

    ngOnInit() {
        this.service.getAll().subscribe({
            next: value => {
                this.errata = value;
            },
        });
    }

    save() {
        this.saving = true;
        this.service.save(this.errata).subscribe({
            next: () => {
                this.saving = false;
                this.messageService.add({
                    severity: 'success',
                    summary: 'Gespeichert',
                    life: 3000
                });
            },
            error: () => {
                this.saving = false;
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: 'Speichern war nicht erfolgreich...',
                    life: 3000
                });
            },
        });
    }

    triggerSend(errata: ErrataDTO) {
        this.confirmationService.confirm({
            header: 'Senden?',
            message: 'Es wird ein Mail an alle relevanten Vereine gesendet.',
            rejectLabel: 'Nein',
            acceptLabel: 'Ja',
            accept: () => {
                this.service.send(errata.modul, errata.klasse, errata.besetzung).subscribe({
                    next: () => {
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Erfolgreich gesendet',
                            life: 2000
                        });
                    },
                    error: () => {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Fehler',
                            detail: 'Es ist ein Fehler aufgetreten...',
                            life: 3000
                        });
                    }
                })
            }
        })
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }
}
