import {Component, OnInit} from '@angular/core';
import {MenuItem, PrimeNGConfig} from "primeng/api";
import {HELFER_ROUTE, NEWSLETTER_ROUTE, UMFRAGE_ROUTE} from "./app-routing.module";

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
            {label: 'Helfer', routerLink: '/' + HELFER_ROUTE},
            {label: 'Newsletter', routerLink: '/' + NEWSLETTER_ROUTE},
            {label: 'Konsulativumfrage', routerLink: '/' + UMFRAGE_ROUTE}
        ]

    }

}
