import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {JudgeDTO, JudgeReportCreateDTO, JuryLoginCreateDTO} from "../rest";

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

    createReports(timetableEntryId: number, judge1Id: number, judge2Id: number, judge3Id: number): Observable<void> {
        const request: JudgeReportCreateDTO = {
            timetableEntryId: timetableEntryId,
            judge1Id: judge1Id,
            judge2Id: judge2Id,
            judge3Id: judge3Id
        };
        return this.httpClient.post<void>(`${this.baseUrl}/secured/admin/jury/report/${timetableEntryId}`, request)
    }

    getAll(): Observable<JudgeDTO[]> {
        return this.httpClient.get<JudgeDTO[]>(`${this.baseUrl}/secured/admin/jury`)
    }

}
