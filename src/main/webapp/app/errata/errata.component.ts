import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";
import {ErrataDTO} from "../rest";
import {ErrataService} from "../service/errata.service";

@Component({
    selector: 'app-errata',
    templateUrl: './errata.component.html',
    styleUrls: ['./errata.component.sass']
})
export class ErrataComponent implements OnInit {

    errata: ErrataDTO[] = [];

    saving = false;

    constructor(private service: ErrataService,
                private readonly messageService: MessageService) {
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
}
