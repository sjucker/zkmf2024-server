import {Component, OnInit, signal} from '@angular/core';
import {saveAs} from "file-saver";
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {JuryLoginCreateComponent} from "../jury-login-create/jury-login-create.component";
import {JudgeDTO, JuryLoginCreateDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {JuryService} from "../service/jury.service";

@Component({
    selector: 'app-jury',
    templateUrl: './jury.component.html',
    styleUrls: ['./jury.component.sass']
})
export class JuryComponent implements OnInit {

    ref?: DynamicDialogRef;

    jury: JudgeDTO[] = [];

    exporting = signal(false);
    exportingRankings = signal(false);
    exportingParademusik = signal(false);
    exportingDiplomas = signal(false);

    constructor(private dialogService: DialogService,
                private authenticationService: AuthenticationService,
                private service: JuryService,
                private messageService: MessageService) {
    }

    ngOnInit(): void {
        this.load();
    }

    private load() {
        this.service.getAll().subscribe({
            next: value => {
                this.jury = value;
            }
        });
    }

    openCreateJuryUser() {
        this.ref = this.dialogService.open(JuryLoginCreateComponent, {
            header: 'Jury Login erstellen',
            width: '300px',
            height: '400px',
            maximizable: false,
            modal: true
        });

        this.ref.onClose.subscribe((value: JuryLoginCreateDTO) => {
            if (value) {
                this.service.create(value).subscribe({
                    next: () => {
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Login erstellt',
                            life: 2000
                        });
                        this.load();
                    },
                    error: err => {
                        console.error(err);
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Fehler',
                            detail: err.error,
                            life: 3000
                        });
                    }
                });
            }
        });
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }

    export() {
        this.exporting.set(true);
        this.service.export().subscribe({
            next: response => {
                saveAs(response, "jury.pdf");
            },
            error: error => {
                this.exporting.set(false);
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: error.statusText,
                    life: 3000
                });
            },
            complete: () => {
                this.exporting.set(false);
            }
        });
    }

    exportRankings() {
        this.exportingRankings.set(true);
        this.service.exportRankings().subscribe({
            next: response => {
                saveAs(response, "ranglisten.xlsx");
            },
            error: error => {
                this.exportingRankings.set(false);
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: error.statusText,
                    life: 3000
                });
            },
            complete: () => {
                this.exportingRankings.set(false);
            }
        });
    }

    exportParademusik() {
        this.exportingParademusik.set(true);
        this.service.exportParademusik().subscribe({
            next: response => {
                saveAs(response, "parademusik.xlsx");
            },
            error: error => {
                this.exportingParademusik.set(false);
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: error.statusText,
                    life: 3000
                });
            },
            complete: () => {
                this.exportingParademusik.set(false);
            }
        });
    }

    exportDiplomas() {
        this.exportingDiplomas.set(true);
        this.service.exportDiplomas().subscribe({
            next: response => {
                saveAs(response, "diplome.pdf");
            },
            error: error => {
                this.exportingDiplomas.set(false);
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: error.statusText,
                    life: 3000
                });
            },
            complete: () => {
                this.exportingDiplomas.set(false);
            }
        });
    }

}
