import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {TimetableEntryCreateDTO, TimetableEntryDTO, TimetableEntryType, VereinSelectionDTO} from "../rest";
import {TimetableService} from "../service/timetable.service";

@Component({
    selector: 'app-timetable',
    templateUrl: './timetable.component.html',
    styleUrls: ['./timetable.component.sass']
})
export class TimetableComponent implements OnInit {

    vereinId: number = 0;
    timetableEntries: TimetableEntryCreateDTO[] = [];
    availableVereine: VereinSelectionDTO[] = [];

    ref?: DynamicDialogRef;

    timetable: Map<string, TimetableEntryDTO[]> = new Map();
    locations: string[] = [];

    constructor(private service: TimetableService,
                public dialogService: DialogService,
                public messageService: MessageService) {
    }

    ngOnInit(): void {
        this.load();
        this.loadVereine();
    }

    private loadVereine() {
        this.service.vereine().subscribe({
            next: value => {
                this.availableVereine = value;
            }
        });
    }

    private load() {
        this.service.getAll().subscribe({
            next: value => {

                this.timetable = new Map<string, TimetableEntryDTO[]>();

                for (const dto of value) {
                    const entries = this.timetable.get(dto.location);
                    if (entries) {
                        entries.push(dto);
                    } else {
                        this.timetable.set(dto.location, [dto]);
                    }
                }

                this.locations = [...this.timetable.keys()];
            }
        });
    }

    getEntriesForLocation(location: string): TimetableEntryDTO[] {
        // TODO sort it correctly
        return this.timetable.get(location)?.sort((a, b) => a.date < b.date ? -1 : 1) ?? [];
    }

    create() {
        if (this.vereinId > 0) {
            this.service.create(this.vereinId, this.timetableEntries).subscribe({
                next: () => {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Erfolgreich erstellt',
                        life: 2000
                    });
                    this.load();
                    this.loadVereine();
                    this.vereinId = 0;
                },
                error: err => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Fehler',
                        detail: err?.error?.message ?? err.error,
                        life: 3000
                    });
                }
            });
        }
    }

    get valid(): boolean {
        return this.vereinId > 0 &&
            this.timetableEntries.every((entry: TimetableEntryCreateDTO) =>
                entry.entries.every(e => e.locationId > 0));
    }

    vereinSelectionChanged() {
        if (this.vereinId > 0) {
            this.service.vereinProgramme(this.vereinId).subscribe({
                next: value => {
                    this.timetableEntries = value;
                }
            });
        }
    }

    protected readonly Date = Date;

    getTranslation(type: TimetableEntryType): string {
        switch (type) {
            case TimetableEntryType.EINSPIEL:
                return "Einspiel";
            case TimetableEntryType.WETTSPIEL:
                return "Wettspiel";
            case TimetableEntryType.BESPRECHUNG:
                return "Besprechung";
            case TimetableEntryType.MARSCHMUSIK:
                return "Marschmusik";
            case TimetableEntryType.PLATZKONZERT:
                return "Platzkonzert";
        }

    }

    formatTime(time: string): string {
        return time.substring(0, 5);
    }
}
