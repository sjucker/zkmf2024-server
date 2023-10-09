import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {JudgeDTO, TimetableEntryCreateDTO, TimetableEntryDTO, VereinSelectionDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class TimetableService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<TimetableEntryDTO[]> {
        return this.httpClient.get<TimetableEntryDTO[]>(`${this.baseUrl}/secured/admin/timetable`)
    }

    create(vereinId: number, dto: TimetableEntryCreateDTO[]): Observable<unknown> {
        return this.httpClient.post<unknown>(`${this.baseUrl}/secured/admin/timetable/${vereinId}`, dto);
    }

    vereine(): Observable<VereinSelectionDTO[]> {
        return this.httpClient.get<VereinSelectionDTO[]>(`${this.baseUrl}/secured/admin/vereine-selection`);
    }

    vereinProgramme(vereinId: number): Observable<TimetableEntryCreateDTO[]> {
        return this.httpClient.get<TimetableEntryCreateDTO[]>(`${this.baseUrl}/secured/admin/vereine/${vereinId}/programme`)
    }

    judges(): Observable<JudgeDTO[]> {
        return this.httpClient.get<JudgeDTO[]>(`${this.baseUrl}/secured/admin/jury`)
    }
}
