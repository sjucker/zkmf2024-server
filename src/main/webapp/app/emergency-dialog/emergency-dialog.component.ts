import {Component, OnInit, signal} from '@angular/core';
import {MessageService} from "primeng/api";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {EmergencyMessageDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {EmergencyService} from "../service/emergency.service";

@Component({
    selector: 'app-emergency-dialog',
    templateUrl: './emergency-dialog.component.html',
    styleUrl: './emergency-dialog.component.sass'
})
export class EmergencyDialogComponent implements OnInit {

    dto: EmergencyMessageDTO = {
        active: false, header: "", message: ""
    };

    saving = signal(false);

    constructor(private config: DynamicDialogConfig<EmergencyMessageDTO>,
                private ref: DynamicDialogRef,
                private authenticationService: AuthenticationService,
                private service: EmergencyService,
                private messageService: MessageService) {
    }

    ngOnInit(): void {
        if (this.config.data) {
            this.dto = this.config.data;
        }
    }

    save() {
        this.saving.set(true);
        this.service.createOrUpdate(this.dto).subscribe({
            next: () => {
                this.saving.set(false);
                this.close();
            },
            error: () => {
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: 'Ein Fehler ist aufgetreten',
                    life: 3000,
                });
                this.saving.set(false);
            },
        });
    }

    close() {
        this.ref.close();
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }
}
