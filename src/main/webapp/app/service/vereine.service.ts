import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {VereinDTO, VereinOverviewDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class VereineService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<VereinOverviewDTO[]> {
        return this.httpClient.get<VereinOverviewDTO[]>(`${this.baseUrl}/secured/admin/vereine`)
    }

    get(id: number): Observable<VereinDTO> {
        return this.httpClient.get<VereinDTO>(`${this.baseUrl}/secured/admin/vereine/${id}`)
    }

    export() {
        return this.httpClient.get(`${this.baseUrl}/secured/admin/download/vereine`, {
            responseType: 'blob'
        });
    }
}
