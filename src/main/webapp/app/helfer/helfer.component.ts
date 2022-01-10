import {Component, OnInit} from '@angular/core';
import {ConfirmationService, MessageService} from "primeng/api";
import {HelperRegistrationDTO} from "../rest";
import {HelperService} from "../service/helper.service";

@Component({
    selector: 'app-helfer',
    templateUrl: './helfer.component.html',
    styleUrls: ['./helfer.component.sass']
})
export class HelferComponent implements OnInit {

    data: HelperRegistrationDTO[] = [];
    loading = false;

    constructor(private helperService: HelperService,
                private messageService: MessageService,
                private confirmationService: ConfirmationService) {
    }

    ngOnInit(): void {
        this.loadData();
    }

    private loadData() {
        this.loading = true;
        this.helperService.getAll().subscribe(
            value => {
                this.data = value;
            },
            error => {
                this.messageService.add({severity: 'error', summary: 'Fehler', detail: error.statusText, life: 3000})
            },
            () => {
                this.loading = false;
            }
        )
    }

}
