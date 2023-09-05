import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {JuryLoginCreateComponent} from "../jury-login-create/jury-login-create.component";
import {JudgeDTO, JuryLoginCreateDTO} from "../rest";
import {JuryService} from "../service/jury.service";

@Component({
    selector: 'app-jury',
    templateUrl: './jury.component.html',
    styleUrls: ['./jury.component.sass']
})
export class JuryComponent implements OnInit {

    ref?: DynamicDialogRef;

    jury: JudgeDTO[] = [];

    constructor(private dialogService: DialogService,
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

}
