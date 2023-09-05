import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {JudgeDTO, JuryLoginCreateDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class JuryService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    create(dto: JuryLoginCreateDTO): Observable<unknown> {
        return this.httpClient.post<unknown>(`${this.baseUrl}/secured/admin/jury/login`, dto)
    }

    getAll(): Observable<JudgeDTO[]> {
        return this.httpClient.get<JudgeDTO[]>(`${this.baseUrl}/secured/admin/jury`)
    }

}
