import {Component} from '@angular/core';
import {saveAs} from "file-saver";
import {MessageService} from "primeng/api";
import {HelperService} from "../service/helper.service";

@Component({
    selector: 'app-helfer',
    templateUrl: './helfer.component.html',
    styleUrls: ['./helfer.component.sass']
})
export class HelferComponent {

    exporting = false;
    exportingHelfereinsatz = false;

    constructor(private helperService: HelperService,
                private messageService: MessageService) {
    }

    export() {
        this.exporting = true;
        this.helperService.export().subscribe({
            next: response => {
                saveAs(response, "helfer-export.xlsx");
            },
            error: error => {
                this.exporting = false;
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: error.statusText,
                    life: 3000
                });
            },
            complete: () => {
                this.exporting = false;
            }
        })
    }

    exportForImport() {
        this.exportingHelfereinsatz = true;
        this.helperService.exportForImport().subscribe({
            next: response => {
                saveAs(response, "helfer-import.xlsx");
            },
            error: error => {
                this.exportingHelfereinsatz = false;
                this.messageService.add({
                    severity: 'error',
                    summary: 'Fehler',
                    detail: error.statusText,
                    life: 3000
                });
            },
            complete: () => {
                this.exportingHelfereinsatz = false;
            }
        })
    }
}
