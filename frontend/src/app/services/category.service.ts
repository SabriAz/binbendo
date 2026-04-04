import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '../models/category.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private apiUrl = `${environment.apiUrl}/category`;
  constructor(private http: HttpClient) {}

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiUrl}`);
  }

  // ADMIN ENDPOINTS!!
  createCategory(category: { name: string }): Observable<string> {
    return this.http.post(`${this.apiUrl}`, category, { responseType: 'text' });
  }

  updateCategory(id: number, category: { name: string }): Observable<string> {
    return this.http.put(`${this.apiUrl}/${id}`, category, { responseType: 'text' });
  }

  deleteCategory(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }

}
