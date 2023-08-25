import {Component} from '@angular/core';
import {DynamicDialogRef} from "primeng/dynamicdialog";
import {UserCreateDTO, UserRole} from "../rest";

@Component({
    selector: 'app-user-create',
    templateUrl: './user-create.component.html',
    styleUrls: ['./user-create.component.sass']
})
export class UserCreateComponent {

    roles = Object.values(UserRole);

    user: UserCreateDTO = {
        email: '',
        role: UserRole.ADMIN,
        password: ''
    };

    constructor(public ref: DynamicDialogRef) {
    }

    create() {
        this.ref.close(this.user);
    }
}
