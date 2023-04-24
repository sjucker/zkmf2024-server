import {Component} from '@angular/core';
import {DynamicDialogConfig} from "primeng/dynamicdialog";
import {VereinDTO} from "../rest";
import {VereineService} from "../service/vereine.service";

export interface VereinDetailInput {
    id: number
}

@Component({
    selector: 'app-verein-detail',
    templateUrl: './verein-detail.component.html',
    styleUrls: ['./verein-detail.component.sass']
})
export class VereinDetailComponent {

    verein?: VereinDTO
    loading = true;

    constructor(private readonly config: DynamicDialogConfig<VereinDetailInput>,
                private vereineService: VereineService) {
        if (config.data) {
            this.loading = true;
            this.vereineService.get(config.data.id).subscribe({
                next: value => {
                    this.verein = value;
                    this.loading = false
                },
                error: _ => {
                    this.loading = false
                }
            });
        }
    }
}
