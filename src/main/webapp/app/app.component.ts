import {Component, OnInit} from '@angular/core';
import {MenuItem, PrimeNGConfig} from "primeng/api";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {

    items: MenuItem[] = [];

    constructor(private primengConfig: PrimeNGConfig) {
    }

    ngOnInit() {
        this.primengConfig.ripple = true;

        this.items = [
            {label: 'Helfer', routerLink: '/helfer'},
            {label: 'Newsletter', routerLink: '/newsletter'},
            {label: 'Konsulativumfrage', routerLink: '/umfrage'}
        ]

    }

}
