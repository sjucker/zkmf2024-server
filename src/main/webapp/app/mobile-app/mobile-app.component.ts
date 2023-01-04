import {Component, OnInit, signal} from '@angular/core';
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {MobileAppCreateComponent} from "../mobile-app-create/mobile-app-create.component";
import {MobileAppEditComponent} from "../mobile-app-edit/mobile-app-edit.component";
import {AppPageDTO} from "../rest";
import {MobileAppService} from "../service/app.service";

@Component({
    selector: 'app-mobile-app',
    templateUrl: './mobile-app.component.html',
    styleUrl: './mobile-app.component.sass'
})
export class MobileAppComponent implements OnInit {

    pages: AppPageDTO[] = [];

    loading = signal(false);

    ref?: DynamicDialogRef;

    constructor(private service: MobileAppService,
                private dialogService: DialogService) {
    }

    ngOnInit(): void {
        this.load();
    }

    private load(): void {
        this.loading.set(true);
        this.service.getAll().subscribe({
            next: value => {
                this.pages = value;
            },
            error: err => {
                console.error(err);
                this.loading.set(false);
            },
            complete: () => {
                this.loading.set(false);
            }
        });
    }

    openCreatePage() {
        this.ref = this.dialogService.open(MobileAppCreateComponent, {
            header: 'App Page erstellen'
        });

        this.ref.onClose.subscribe(() => {
            this.load();
        });
    }

    openEditPage(dto: AppPageDTO) {
        this.ref = this.dialogService.open(MobileAppEditComponent, {
            header: 'App Page bearbeiten',
            data: {...dto}
        });

        this.ref.onClose.subscribe(() => {
            this.load();
        });
    }
}
