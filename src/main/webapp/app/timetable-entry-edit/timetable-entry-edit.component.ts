import {Component} from '@angular/core';
import {MessageService} from "primeng/api";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {LocationSelectionDTO, TimetableEntryDTO} from "../rest";
import {TimetableService} from "../service/timetable.service";

export interface TimetableEntryEditInput {
    dto: TimetableEntryDTO
}

@Component({
    selector: 'app-timetable-entry-edit',
    templateUrl: './timetable-entry-edit.component.html',
    styleUrls: ['./timetable-entry-edit.component.sass']
})
export class TimetableEntryEditComponent {

    entry?: TimetableEntryDTO;
    availableLocations: LocationSelectionDTO[] = [];

    saving = false;

    constructor(private service: TimetableService,
                private messageService: MessageService,
                public config: DynamicDialogConfig<TimetableEntryEditInput>,
                public ref: DynamicDialogRef) {
        if (config.data) {
            this.entry = config.data.dto;
            this.service.locations(this.entry.type).subscribe({
                next: value => {
                    this.availableLocations = value;
                },
                error: err => {
                    console.error(err);
                },
            })
        }
    }

    save() {
        if (this.entry) {
            this.saving = true;
            this.service.update(this.entry).subscribe({
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
}
