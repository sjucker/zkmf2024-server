import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {ErrataDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class ErrataService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<ErrataDTO[]> {
        return this.httpClient.get<ErrataDTO[]>(`${this.baseUrl}/secured/admin/errata`)
    }

    save(dtos: ErrataDTO[]): Observable<void> {
        return this.httpClient.post<void>(`${this.baseUrl}/secured/admin/errata`, dtos)
    }
}
