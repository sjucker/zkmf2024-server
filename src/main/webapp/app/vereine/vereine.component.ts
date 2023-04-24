import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {Klasse, PhaseStatus, VereinOverviewDTO} from "../rest";
import {VereineService} from "../service/vereine.service";
import {VereinDetailComponent, VereinDetailInput} from "../verein-detail/verein-detail.component";

@Component({
    selector: 'app-vereine',
    templateUrl: './vereine.component.html',
    styleUrls: ['./vereine.component.sass']
})
export class VereineComponent implements OnInit {
    data: VereinOverviewDTO[] = [];
    loading = false;

    ref?: DynamicDialogRef;

    constructor(private vereineService: VereineService,
                private messageService: MessageService,
                private dialogService: DialogService) {
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

    getPhaseStatusIcon(status: PhaseStatus): string {
        switch (status) {
            case PhaseStatus.NEW:
                return "pi pi-circle"
            case PhaseStatus.IN_PROGRESS:
                return "pi pi-exclamation-circle"
            case PhaseStatus.DONE:
                return "pi pi-check-circle"
        }
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

    openVereinDetail(dto: VereinOverviewDTO) {
        const input: VereinDetailInput = {
            id: dto.id,
        }
        this.ref = this.dialogService.open(VereinDetailComponent, {
            header: dto.vereinsname,
            width: '90%',
            height: '90%',
            maximizable: true,
            data: input
        });
    }
}
