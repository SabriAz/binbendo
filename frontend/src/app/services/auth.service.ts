import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthResponse } from '../models/authResponse.model';
import { AuthRequest } from '../models/authRequest.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  constructor(private httpClient: HttpClient) {}

  register(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(`${this.apiUrl}/register`, authRequest);
  }

  login(authRequest: AuthRequest): Observable<AuthResponse> {
    return this.httpClient.post<AuthResponse>(`${this.apiUrl}/login`, authRequest);
  }
}
