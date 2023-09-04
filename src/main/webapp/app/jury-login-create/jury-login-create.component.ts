import {Component} from '@angular/core';
import {DynamicDialogRef} from "primeng/dynamicdialog";
import {JuryLoginCreateDTO} from "../rest";

@Component({
    selector: 'app-jury-login-create',
    templateUrl: './jury-login-create.component.html',
    styleUrls: ['./jury-login-create.component.sass']
})
export class JuryLoginCreateComponent {

    dto: JuryLoginCreateDTO = {
        email: '',
        name: '',
        password: ''
    };

    constructor(public ref: DynamicDialogRef) {
    }

    create() {
        this.ref.close(this.dto);
    }

    get valid(): boolean {
        return this.dto.email.length > 0 &&
            this.dto.name.length > 0 &&
            this.dto.password.length > 0;
    }
}
