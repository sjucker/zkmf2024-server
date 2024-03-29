import {Component} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MessageService} from 'primeng/api';
import {AuthenticationService} from '../service/authentication.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.sass']
})
export class LoginComponent {

    authenticationError = false;

    loginForm = this.formBuilder.group({
        email: [null, [Validators.required]],
        password: [null, [Validators.required]],
        rememberMe: [false],
    });

    constructor(private formBuilder: FormBuilder,
                private router: Router,
                private authenticationService: AuthenticationService,
                private messageService: MessageService) {
    }

    onSubmit(): void {
        if (this.loginForm.valid) {
            this.authenticationError = false;
            const val = this.loginForm.value;
            if (val.email && val.password) {
                this.authenticationService.login(val.email, val.password).subscribe({
                    next: response => {
                        this.authenticationService.setCredentials(response);
                        this.router.navigate(['/']).then();
                    },
                    error: () => {
                        this.messageService.add({
                            severity: 'error',
                            summary: 'Fehler',
                            detail: 'Login fehlgeschlagen',
                            life: 3000
                        })
                    }
                })
            }
        }
    }

}
