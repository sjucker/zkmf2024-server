import {Component, OnInit} from '@angular/core';
import {MessageService} from "primeng/api";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {
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
        date: '',
        start: '',
        end: '',
        locationId: 0,
        vereinId: 0,
        vereinProgrammId: 0,
    };

    availableLocations: LocationSelectionDTO[] = [];
    availableVereine: VereinSelectionDTO[] = [];
    availableProgramme: VereinProgrammSelectionDTO[] = [];

    ref?: DynamicDialogRef;

    timetable: Map<string, TimetableEntryDTO[]> = new Map();

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
            }
        });
    }

    get locations(): IterableIterator<string> {
        return this.timetable.keys();
    }

    getEntriesForLocation(location: string): TimetableEntryDTO[] {
        // TODO sort it correctly
        return this.timetable.get(location)?.sort((a, b) => a.date < b.date ? -1 : 1) ?? [];
    }

    create() {
        // TODO
        console.log(this.newEntry);
    }

    get valid(): boolean {
        return true;
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
