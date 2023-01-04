import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {AppPageCreateDTO, AppPageDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class MobileAppService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<AppPageDTO[]> {
        return this.httpClient.get<AppPageDTO[]>(`${this.baseUrl}/secured/admin/app-page`)
    }

    create(dto: AppPageCreateDTO): Observable<void> {
        return this.httpClient.post<void>(`${this.baseUrl}/secured/admin/app-page`, dto);
    }

    update(dto: AppPageDTO): Observable<void> {
        return this.httpClient.patch<void>(`${this.baseUrl}/secured/admin/app-page/${dto.id}`, dto)
    }
}
