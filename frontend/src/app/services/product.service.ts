import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private apiUrl = `${environment.apiUrl}/product`;

  constructor(private http: HttpClient) {}

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}`);
  }

  getProductsByCategories(ids: number[]): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/filter?categoryIds=${ids.join(',')}`);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }

  // ADMIN ENDPOINTS!!
  createProduct(product: { name: string; description: string; price: number; imageUrl: string; categoryId: number }): Observable<string> {
    return this.http.post(`${this.apiUrl}`, product, { responseType: 'text' });
  }

  updateProduct(id: number, product: { name: string; description: string; price: number; imageUrl: string; categoryId: number }): Observable<string> {
    return this.http.put(`${this.apiUrl}/${id}`, product, { responseType: 'text' });
  }

  deleteProduct(id: number): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${id}`, { responseType: 'text' });
  }
}
