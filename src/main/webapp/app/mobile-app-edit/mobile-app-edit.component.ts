import {Component, OnInit, signal} from '@angular/core';
import {MessageService} from "primeng/api";
import {DynamicDialogConfig, DynamicDialogRef} from "primeng/dynamicdialog";
import {AppPageDTO} from "../rest";
import {MobileAppService} from "../service/app.service";
import {AuthenticationService} from "../service/authentication.service";

@Component({
    selector: 'app-mobile-app-edit',
    templateUrl: './mobile-app-edit.component.html',
    styleUrl: './mobile-app-edit.component.sass'
})
export class MobileAppEditComponent implements OnInit {

    dto: AppPageDTO = {
        id: 0,
        markdown: '',
        title: '',
        news: false
    };

    saving = signal(false);

    constructor(private readonly config: DynamicDialogConfig<AppPageDTO>,
                private readonly ref: DynamicDialogRef,
                private authenticationService: AuthenticationService,
                private readonly service: MobileAppService,
                private readonly messageService: MessageService) {
    }

    ngOnInit(): void {
        if (this.config.data) {
            this.dto = this.config.data;
        }
    }

    save() {
        this.saving.set(true);
        this.service.update(this.dto).subscribe({
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
