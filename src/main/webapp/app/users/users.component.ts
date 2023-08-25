import {Component} from '@angular/core';
import {DialogService, DynamicDialogRef} from "primeng/dynamicdialog";
import {UserCreateDTO} from "../rest";
import {UserCreateComponent} from "../user-create/user-create.component";

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.sass']
})
export class UsersComponent {

    ref?: DynamicDialogRef;

    constructor(private dialogService: DialogService) {
    }

    openDialog() {
        this.ref = this.dialogService.open(UserCreateComponent, {
            header: "User erstellen",
            width: '400px',
            height: '300px',
            maximizable: false,
            modal: true
        });

        this.ref.onClose.subscribe((user: UserCreateDTO) => {
            if (user) {
                console.log(user);
            }
        })
    }
}
