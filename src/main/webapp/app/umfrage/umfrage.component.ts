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

    constructor(private umfrageService: UmfrageService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.loading = true;
        this.umfrageService.getAll().subscribe(
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

    delete(dto: SurveyAnswerDTO) {
        this.confirmationService.confirm({
            header: 'Löschen?',
            message: 'Umfrage-Antwort wirklich löschen?',
            rejectLabel: 'Nein',
            acceptLabel: 'Ja',
            accept: () => {
                this.umfrageService.delete(dto.id!).subscribe(
                    _ => {
                        this.data = this.data.filter(val => val.id !== dto.id);
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Gelöscht',
                            detail: 'Umfrageantwort wurde gelöscht',
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

    export() {
        this.umfrageService.export().subscribe(
            response => {
                saveAs(response, "umfrage-export.xlsx");
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
}
