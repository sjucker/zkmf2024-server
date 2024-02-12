import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {TimetableEntryCreateDTO, TimetableEntryDTO, TimetableEntryType, VereinSelectionDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {TimetableService} from "../service/timetable.service";
import {TimetableAssignJudgesComponent, TimetableAssignJudgesInput} from "../timetable-assign-judges/timetable-assign-judges.component";
import {TimetableEntryEditComponent, TimetableEntryEditInput} from "../timetable-entry-edit/timetable-entry-edit.component";

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
                private authenticationService: AuthenticationService,
                public dialogService: DialogService,
                public messageService: MessageService,
                public confirmationService: ConfirmationService) {
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

                this.locations = [...this.timetable.keys()].sort();
            },
        });
    }

    getEntriesForLocation(location: string): TimetableEntryDTO[] {
        return this.timetable.get(location)?.sort((a, b) => {
            if (a.date === b.date) {
                return a.start < b.start ? -1 : 1;
            }
            return a.date < b.date ? -1 : 1
        }) ?? [];
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

    edit(dto: TimetableEntryDTO) {
        this.dialogService.open(TimetableEntryEditComponent, {
            header: dto.verein,
            data: {
                dto: {...dto},
            } as TimetableEntryEditInput,
            width: "1000px",
            height: "400px",
        }).onClose.subscribe({
            next: () => {
                this.load();
            }
        });
    }

    delete(dto: TimetableEntryDTO) {
        this.confirmationService.confirm({
            header: 'Löschen',
            icon: 'pi pi-exclamation-triangle',
            message: "Soll dieser Eintrage wirklich gelöscht werden?",
            acceptLabel: 'Ja',
            rejectLabel: 'Abbrechen',
            accept: () => {
                this.service.deleteTimetableEntry(dto.id).subscribe({
                    next: () => {
                        this.load();
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Gelöscht',
                            life: 3000
                        });
                    },
                    error: () => {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Fehler',
                            detail: 'Ein Fehler ist aufgetreten',
                            life: 3000
                        });
                    },
                });
            },
        });
    }

    assignJudges(dto: TimetableEntryDTO) {
        this.dialogService.open(TimetableAssignJudgesComponent, {
            header: dto.verein,
            data: {
                dto: {...dto},
            } as TimetableAssignJudgesInput,
            width: "1000px",
            height: "400px",
        }).onClose.subscribe({
            next: () => {
                this.load();
            }
        })
    }

    canAssignJudges(dto: TimetableEntryDTO) {
        return (dto.type === TimetableEntryType.WETTSPIEL || dto.type === TimetableEntryType.MARSCHMUSIK) && !this.hasJudgesAssigned(dto);
    }

    hasJudgesAssigned(dto: TimetableEntryDTO): boolean {
        return !!dto.judge1 && !!dto.judge2 && !!dto.judge3;
    }

    judges(dto: TimetableEntryDTO): string {
        let judges = `${dto.judge1}, ${dto.judge2}, ${dto.judge3}`;
        if (dto.judge4) {
            judges += `, ${dto.judge4}`;
        }
        return judges;
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }
}
