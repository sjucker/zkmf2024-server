import {Component} from '@angular/core';
import {MessageService} from "primeng/api";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {AuthenticationService} from "../service/authentication.service";
import {VereineService} from "../service/vereine.service";

export interface BroadcastInput {
    ids: number[]
}

@Component({
    selector: 'app-broadcast',
    templateUrl: './broadcast.component.html',
    styleUrls: ['./broadcast.component.sass']
})
export class BroadcastComponent {

    message = '';
    saving = false;

    constructor(private readonly config: DynamicDialogConfig<BroadcastInput>,
                private readonly ref: DynamicDialogRef,
                private authenticationService: AuthenticationService,
                private readonly vereineService: VereineService,
                private readonly messageService: MessageService) {
    }

    broadcast() {
        if (this.config.data) {
            this.saving = true;
            this.vereineService.broadcast(this.config.data.ids, this.message).subscribe({
                next: value => {
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Gesendet',
                        detail: `Broadcast erfolgreich an ${value.length} Vereine gesendet!`,
                        life: 5000,
                    });
                    this.saving = false;
                },
                error: () => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Fehler',
                        detail: 'Broadcast konnte nicht gesendet werden.',
                        life: 3000,
                    });
                    this.saving = false;
                },
            });
        }
    }

    close() {
        this.ref.close();
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }
}
