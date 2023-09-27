import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {
    JudgeDTO,
    LocationSelectionDTO,
    TimetableEntryCreateDTO,
    TimetableEntryDTO,
    VereinProgrammSelectionDTO,
    VereinSelectionDTO
} from "../rest";
import {TimetableService} from "../service/timetable.service";

@Component({
    selector: 'app-timetable',
    templateUrl: './timetable.component.html',
    styleUrls: ['./timetable.component.sass']
})
export class TimetableComponent implements OnInit {

    newEntry: TimetableEntryCreateDTO = {
        date: '2024-06-22',
        start: '12:00:00',
        end: '13:00:00',
        locationId: 0,
        vereinId: 0,
        vereinProgrammId: 0
    };

    availableLocations: LocationSelectionDTO[] = [];
    availableVereine: VereinSelectionDTO[] = [];
    availableProgramme: VereinProgrammSelectionDTO[] = [];
    availableJudges: JudgeDTO[] = [];

    ref?: DynamicDialogRef;

    timetable: Map<string, TimetableEntryDTO[]> = new Map();
    locations: string[] = [];

    constructor(private service: TimetableService,
                public dialogService: DialogService,
                public messageService: MessageService) {
    }

    ngOnInit(): void {
        this.load();

        this.service.locations().subscribe({
            next: value => {
                this.availableLocations = value;
            }
        });

        this.service.vereine().subscribe({
            next: value => {
                this.availableVereine = value;
            }
        });

        this.service.judges().subscribe({
            next: value => {
                this.availableJudges = value;
            }
        })
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
        this.service.create(this.newEntry).subscribe({
            next: _ => {
                this.messageService.add({
                    severity: 'success',
                    summary: 'Erfolgreich erstellt',
                    life: 2000
                });
                this.load();
                this.vereinSelectionChanged();
            },
            error: err => {
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: err.error,
                    life: 3000
                });
            }
        })
    }

    get valid(): boolean {
        return this.newEntry.vereinId > 0 &&
            this.newEntry.locationId > 0 &&
            // TODO validate date formats
            this.newEntry.end.length > 0 &&
            this.newEntry.start.length > 0 &&
            this.newEntry.date.length > 0;
        // this.newEntry.vereinProgrammId > 0 && TODO

    }

    vereinSelectionChanged() {
        this.newEntry.vereinProgrammId = 0;
        if (this.newEntry.vereinId) {
            this.service.vereinProgramme(this.newEntry.vereinId).subscribe({
                next: value => {
                    this.availableProgramme = value;
                },
            });
        }
    }
}
