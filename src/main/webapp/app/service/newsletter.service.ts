import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {NewsletterRecipientDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class NewsletterService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    getAll(): Observable<NewsletterRecipientDTO[]> {
        return this.httpClient.get<NewsletterRecipientDTO[]>(`${this.baseUrl}/secured/admin/newsletter`)
    }

    delete(email: string): Observable<any> {
        return this.httpClient.delete<any>(`${this.baseUrl}/secured/admin/newsletter/${email}`)
    }

    unsubscribe(email: string): Observable<any> {
        return this.httpClient.post<any>(`${this.baseUrl}/secured/admin/newsletter/unsubscribe/${email}`, {})
    }
}
