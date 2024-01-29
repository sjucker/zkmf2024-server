import {Component} from '@angular/core';
import {MessageService} from "primeng/api";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {JudgeDTO, TimetableEntryDTO} from "../rest";
import {JuryService} from "../service/jury.service";

export interface TimetableAssignJudgesInput {
    dto: TimetableEntryDTO
}

@Component({
    selector: 'app-timetable-assign-judges',
    templateUrl: './timetable-assign-judges.component.html',
    styleUrls: ['./timetable-assign-judges.component.sass']
})
export class TimetableAssignJudgesComponent {

    dto!: TimetableEntryDTO;
    availableJudges: JudgeDTO[] = [];

    judge1Id = 0;
    judge2Id = 0;
    judge3Id = 0;

    saving = false;

    constructor(private service: JuryService,
                private messageService: MessageService,
                public config: DynamicDialogConfig<TimetableAssignJudgesInput>,
                public ref: DynamicDialogRef) {
        if (config.data) {
            this.dto = config.data.dto;
            this.service.getAll().subscribe({
                next: value => {
                    this.availableJudges = value;
                },
                error: err => {
                    console.error(err);
                },
            });
        }
    }

    get valid(): boolean {
        return this.judge1Id > 0 && this.judge2Id > 0 && this.judge3Id > 0 &&
            this.judge1Id !== this.judge2Id && this.judge1Id != this.judge3Id && this.judge2Id !== this.judge3Id;
    }

    save() {
        this.saving = true;
        this.service.createReports(this.dto.id, this.dto.modul, this.judge1Id, this.judge2Id, this.judge3Id).subscribe({
            next: () => {
                this.saving = false;
                this.ref.close();
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
                    detail: 'Es ist ein Fehler aufgetreten...',
                    life: 3000
                });
            },
        });
    }
}
