import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {LoginRequestDTO, LoginResponseDTO, UserRole} from "../rest";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private readonly token = 'token';
    private readonly userId = 'user-id';
    private readonly role = 'role';

    private baseUrl = environment.baseUrl;

    constructor(private readonly httpClient: HttpClient) {
    }

    login(email: string, password: string): Observable<LoginResponseDTO> {
        const request: LoginRequestDTO = {
            email: email,
            password: password
        };

        return this.httpClient.post<LoginResponseDTO>(`${this.baseUrl}/public/auth`, request);
    }

    logout(): void {
        localStorage.clear();
    }

    setCredentials(dto: LoginResponseDTO): void {
        localStorage.setItem(this.token, dto.jwt);
        localStorage.setItem(this.userId, dto.email);
        localStorage.setItem(this.role, dto.role);
    }

    getAuthorizationToken(): string | null {
        return localStorage.getItem(this.token);
    }

    getUserId(): string | null {
        return localStorage.getItem(this.userId);
    }

    isLoggedIn(): boolean {
        return localStorage.getItem(this.token) !== null;
    }

    isAdmin(): boolean {
        return this.isLoggedIn() && (localStorage.getItem(this.role) === UserRole.ADMIN || localStorage.getItem(this.role) === UserRole.ADMIN_READ_ONLY);
    }

    isReadOnly() {
        return localStorage.getItem(this.role) === null || localStorage.getItem(this.role) === UserRole.ADMIN_READ_ONLY
    }

}
