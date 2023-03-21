import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {VereinDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class VereineService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<VereinDTO[]> {
        return this.httpClient.get<VereinDTO[]>(`${this.baseUrl}/secured/admin/vereine`)
    }
}
