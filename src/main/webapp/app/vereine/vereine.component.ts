import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {VereinDTO} from "../rest";
import {VereineService} from "../service/vereine.service";

@Component({
    selector: 'app-vereine',
    templateUrl: './vereine.component.html',
    styleUrls: ['./vereine.component.sass']
})
export class VereineComponent implements OnInit {
    data: VereinDTO[] = [];
    loading = false;

    constructor(private vereineService: VereineService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.loading = true;
        this.vereineService.getAll().subscribe({
            next: value => {
                this.data = value;
            },
            error: error => {
                this.messageService.add({severity: 'error', summary: 'Fehler', detail: error.statusText, life: 3000})
            },
            complete: () => {
                this.loading = false;
            }
        })
    }

    count(): number {
        return this.data.length;
    }
}
