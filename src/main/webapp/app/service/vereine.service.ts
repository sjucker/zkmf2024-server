import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {
    VereinCommentCreateDTO,
    VereinCommentDTO,
    VereinDTO,
    VereinMessageCreateDTO,
    VereinMessageDTO,
    VereinOverviewDTO
} from "../rest";

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

    getComments(id: number): Observable<VereinCommentDTO[]> {
        return this.httpClient.get<VereinCommentDTO[]>(`${this.baseUrl}/secured/admin/vereine/${id}/comments`);
    }

    getMessages(id: number): Observable<VereinMessageDTO[]> {
        return this.httpClient.get<VereinMessageDTO[]>(`${this.baseUrl}/secured/admin/vereine/${id}/messages`);
    }

    export(): Observable<Blob> {
        return this.httpClient.get(`${this.baseUrl}/secured/admin/download/vereine`, {
            responseType: 'blob'
        });
    }

    saveComment(id: number, comment: string): Observable<VereinCommentDTO> {
        const request: VereinCommentCreateDTO = {
            comment: comment
        };
        return this.httpClient.post<VereinCommentDTO>(`${this.baseUrl}/secured/admin/vereine/${id}/comments`, request);
    }

    saveMessag(id: number, message: string): Observable<VereinMessageDTO> {
        const request: VereinMessageCreateDTO = {
            message: message
        };
        return this.httpClient.post<VereinMessageDTO>(`${this.baseUrl}/secured/admin/vereine/${id}/messages`, request);
    }

    confirmProgramm(id: number) {
        return this.httpClient.post<VereinDTO>(`${this.baseUrl}/secured/admin/vereine/${id}/confirm-programm`, undefined);
    }
}
