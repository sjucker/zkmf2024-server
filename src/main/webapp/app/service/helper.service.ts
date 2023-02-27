import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class HelperService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
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
