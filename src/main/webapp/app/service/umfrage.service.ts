import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {SurveyAnswerDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class UmfrageService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<SurveyAnswerDTO[]> {
        return this.httpClient.get<SurveyAnswerDTO[]>(`${this.baseUrl}/secured/admin/umfrage`)
    }

    delete(id: number): Observable<any> {
        return this.httpClient.delete<any>(`${this.baseUrl}/secured/admin/umfrage/${id}`)
    }
}
