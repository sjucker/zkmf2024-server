import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {MessageMemberDTO, MessageSendDTO} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class MessagingService {

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    sendNotification(dto: MessageSendDTO): Observable<void> {
        return this.httpClient.post<void>(`${this.baseUrl}/secured/admin/messaging`, dto);
    }

    sendMemberNotification(dto: MessageMemberDTO): Observable<void> {
        return this.httpClient.post<void>(`${this.baseUrl}/secured/admin/messaging/member`, dto);
    }

}
