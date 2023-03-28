import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {Klasse, VereinDTO} from "../rest";
import {VereineService} from "../service/vereine.service";

@Component({
    selector: 'app-vereine',
    templateUrl: './vereine.component.html',
    styleUrls: ['./vereine.component.sass']
})
export class VereineComponent implements OnInit {
    data: VereinDTO[] = [];
    loading = false;

    constructor(private vereineService: VereineService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.loading = true;
        this.vereineService.getAll().subscribe({
            next: value => {
                this.data = value;
            },
            error: error => {
                this.messageService.add({severity: 'error', summary: 'Fehler', detail: error.statusText, life: 3000})
            },
            complete: () => {
                this.loading = false;
            }
        })
    }

    count(): number {
        return this.data.length;
    }

    formatBoolean(b: boolean): string {
        return b ? "X" : "";
    }

    formatKlasse(klasse?: Klasse): string {
        if (!klasse) {
            return ""
        }
        switch (klasse) {
            case Klasse.HOECHSTKLASSE:
                return "Höchstklasse"
            case Klasse.KLASSE_1:
                return "1. Klasse"
            case Klasse.KLASSE_2:
                return "2. Klasse"
            case Klasse.KLASSE_3:
                return "3. Klasse"
            case Klasse.KLASSE_4:
                return "4. Klasse"
            case Klasse.OBERSTUFE:
                return "Oberstufe"
            case Klasse.MITTELSTUFE:
                return "Mittelstufe"
            case Klasse.UNTERSTUFE:
                return "Unterstufe"
        }
    }
}
