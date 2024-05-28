import {Component, OnInit, signal} from '@angular/core';
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {EmergencyDialogComponent} from "../emergency-dialog/emergency-dialog.component";
import {EmergencyMessageDTO} from "../rest";
import {EmergencyService} from "../service/emergency.service";

@Component({
    selector: 'app-emergency',
    templateUrl: './emergency.component.html',
    styleUrl: './emergency.component.sass'
})
export class EmergencyComponent implements OnInit {

    messages = signal<EmergencyMessageDTO[]>([]);
    loading = signal(false);

    ref?: DynamicDialogRef;

    constructor(private emergencyService: EmergencyService,
                private dialogService: DialogService) {
    }

    ngOnInit(): void {
        this.load();
    }

    private load(): void {
        this.loading.set(true);
        this.emergencyService.getAll().subscribe({
            next: value => {
                this.messages.set(value);
            },
            error: err => {
                console.error(err);
                this.loading.set(false);
            },
            complete: () => {
                this.loading.set(false);
            }
        });

        this.emergencyService.getAll().subscribe(value => {
            this.messages.set(value);
        });
    }

    openCreateMessage(): void {
        this.ref = this.dialogService.open(EmergencyDialogComponent, {
            header: 'Notfall-Nachricht erstellen'
        });

        this.ref.onClose.subscribe(() => {
            this.load();
        });
    }

    openEditMessage(dto: EmergencyMessageDTO) {
        this.ref = this.dialogService.open(EmergencyDialogComponent, {
            header: 'Notfall-Nachricht bearbeiten',
            data: {...dto}
        });

        this.ref.onClose.subscribe(() => {
            this.load();
        });
    }
}
