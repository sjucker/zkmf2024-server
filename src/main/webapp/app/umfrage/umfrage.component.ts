import {Component, OnInit} from '@angular/core';
import {saveAs} from "file-saver";
import {ConfirmationService, MessageService} from "primeng/api";
import {SurveyAnswerDTO} from "../rest";
import {UmfrageService} from "../service/umfrage.service";

@Component({
    selector: 'app-umfrage',
    templateUrl: './umfrage.component.html',
    styleUrls: ['./umfrage.component.sass']
})
export class UmfrageComponent implements OnInit {

    data: SurveyAnswerDTO[] = [];
    loading = false;
    exporting = false;

    constructor(private umfrageService: UmfrageService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.loading = true;
        this.umfrageService.getAll().subscribe({
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

    delete(dto: SurveyAnswerDTO) {
        this.confirmationService.confirm({
            header: 'Löschen?',
            message: 'Umfrage-Antwort wirklich löschen?',
            rejectLabel: 'Nein',
            acceptLabel: 'Ja',
            accept: () => {
                this.umfrageService.delete(dto.id!).subscribe({
                    next: _ => {
                        this.data = this.data.filter(val => val.id !== dto.id);
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Gelöscht',
                            detail: 'Umfrageantwort wurde gelöscht',
                            life: 2000
                        });
                    },
                    error: error => {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Fehler',
                            detail: error.statusText,
                            life: 3000
                        });
                    }
                })
            }
        });
    }

    export() {
        this.exporting = true;
        this.umfrageService.export().subscribe({
            next: response => {
                saveAs(response, "umfrage-export.xlsx");
            },
            error: error => {
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: error.statusText,
                    life: 3000
                });
            },
            complete: () => {
                this.exporting = false;
            }
        })
    }
}
