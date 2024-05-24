import {Component, OnInit, signal} from '@angular/core';
import {MessageService} from "primeng/api";
import {EmergencyMessageDTO} from "../rest";
import {AuthenticationService} from "../service/authentication.service";
import {EmergencyService} from "../service/emergency.service";

@Component({
    selector: 'app-emergency',
    templateUrl: './emergency.component.html',
    styleUrl: './emergency.component.sass'
})
export class EmergencyComponent implements OnInit {

    messages = signal<EmergencyMessageDTO[]>([]);
    loading = signal(false);

    constructor(private authenticationService: AuthenticationService,
                private readonly emergencyService: EmergencyService,
                private readonly messageService: MessageService) {
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
// TODO
    }

    openEditMessage(dto: EmergencyMessageDTO) {
// TODO
    }
}
