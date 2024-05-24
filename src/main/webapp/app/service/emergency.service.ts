import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {EmergencyMessageDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class EmergencyService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<EmergencyMessageDTO[]> {
        return this.httpClient.get<EmergencyMessageDTO[]>(`${this.baseUrl}/secured/admin/emergency`)
    }

    createOrUpdate(dto: EmergencyMessageDTO): Observable<void> {
        return this.httpClient.post<void>(`${this.baseUrl}/secured/admin/emergency`, dto);
    }
}
