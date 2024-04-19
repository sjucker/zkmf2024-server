import {Component, signal} from '@angular/core';
import {MessageService} from "primeng/api";
import {DynamicDialogRef} from "primeng/dynamicdialog";
import {AppPageCreateDTO} from "../rest";
import {MobileAppService} from "../service/app.service";
import {AuthenticationService} from "../service/authentication.service";

@Component({
    selector: 'app-mobile-app-create',
    templateUrl: './mobile-app-create.component.html',
    styleUrl: './mobile-app-create.component.sass'
})
export class MobileAppCreateComponent {

    dto: AppPageCreateDTO = {
        markdown: '',
        title: '',
        news: false
    };

    saving = signal(false);

    constructor(private readonly ref: DynamicDialogRef,
                private authenticationService: AuthenticationService,
                private readonly service: MobileAppService,
                private readonly messageService: MessageService) {
    }

    save() {
        if (this.dto.markdown) {
            this.saving.set(true);
            this.service.create(this.dto).subscribe({
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
    }

    close() {
        this.ref.close();
    }

    isReadOnly(): boolean {
        return this.authenticationService.isReadOnly();
    }
}
