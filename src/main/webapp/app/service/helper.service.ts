import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {HelperRegistrationDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class HelperService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<HelperRegistrationDTO[]> {
        return this.httpClient.get<HelperRegistrationDTO[]>(`${this.baseUrl}/secured/admin/helfer`)
    }

    export(): Observable<Blob> {
        return this.httpClient.get(`${this.baseUrl}/secured/admin/download/helfer`, {
            responseType: 'blob'
        });
    }

    exportForImport(): Observable<Blob> {
        return this.httpClient.get(`${this.baseUrl}/secured/admin/download/helfer-import`, {
            responseType: 'blob'
        });
    }
}
