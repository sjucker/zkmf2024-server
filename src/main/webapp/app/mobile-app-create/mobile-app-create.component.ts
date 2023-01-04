import {Component, signal} from '@angular/core';
import {MessageService} from "primeng/api";
import {DynamicDialogRef} from "primeng/dynamicdialog";
import {MobileAppService} from "../service/app.service";
import {AuthenticationService} from "../service/authentication.service";

@Component({
    selector: 'app-mobile-app-create',
    templateUrl: './mobile-app-create.component.html',
    styleUrl: './mobile-app-create.component.sass'
})
export class MobileAppCreateComponent {

    markdown = '';
    cloudflareId = '';

    saving = signal(false);

    constructor(private readonly ref: DynamicDialogRef,
                private authenticationService: AuthenticationService,
                private readonly service: MobileAppService,
                private readonly messageService: MessageService) {
    }

    save() {
        if (this.markdown) {
            this.saving.set(true);
            this.service.create({markdown: this.markdown, cloudflareId: this.cloudflareId}).subscribe({
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
