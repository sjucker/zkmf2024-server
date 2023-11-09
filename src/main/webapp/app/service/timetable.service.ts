import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {JudgeDTO, LocationSelectionDTO, TimetableEntryCreateDTO, TimetableEntryDTO, TimetableEntryType, VereinSelectionDTO} from "../rest";

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

    update(dto: TimetableEntryDTO): Observable<unknown> {
        return this.httpClient.patch<unknown>(`${this.baseUrl}/secured/admin/timetable/entry/${dto.id}`, dto);
    }

    deleteTimetableEntry(id: number): Observable<void> {
        return this.httpClient.delete<void>(`${this.baseUrl}/secured/admin/timetable/entry/${id}`);
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

    locations(type: TimetableEntryType): Observable<LocationSelectionDTO[]> {
        return this.httpClient.get<LocationSelectionDTO[]>(`${this.baseUrl}/secured/admin/location/${type}`)
    }
}
