import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {
    LocationSelectionDTO,
    TimetableEntryCreateDTO,
    TimetableEntryDTO,
    VereinProgrammSelectionDTO,
    VereinSelectionDTO
} from "../rest";

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

    create(dto: TimetableEntryCreateDTO): Observable<unknown> {
        return this.httpClient.post<unknown>(`${this.baseUrl}/secured/admin/timetable`, dto);
    }

    locations(): Observable<LocationSelectionDTO[]> {
        return this.httpClient.get<LocationSelectionDTO[]>(`${this.baseUrl}/secured/admin/location`);
    }

    vereine(): Observable<VereinSelectionDTO[]> {
        return this.httpClient.get<VereinSelectionDTO[]>(`${this.baseUrl}/secured/admin/vereine-selection`);
    }

    vereinProgramme(vereinId: number): Observable<VereinProgrammSelectionDTO[]> {
        return this.httpClient.get<VereinProgrammSelectionDTO[]>(`${this.baseUrl}/secured/admin/vereine/${vereinId}/programme`)
    }
}
