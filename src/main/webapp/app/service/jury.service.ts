import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {JuryLoginCreateDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class JuryService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    create(dto: JuryLoginCreateDTO): Observable<unknown> {
        return this.httpClient.post<unknown>(`${this.baseUrl}/secured/judge/login`, dto)
    }

}
