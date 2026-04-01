import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private apiUrl = 'http://localhost:8080/api/cart';

  constructor(private http: HttpClient) {}

  getCart(): Observable<any> {
    return this.http.get(`${this.apiUrl}`);
  }

  addToCart(productId: number, quantity: number): Observable<any> {
    return this.http.post(`${this.apiUrl}`, { productId, quantity }, { responseType: 'text' });
  }

  updateQuantity(cartItemId: number, quantity: number): Observable<any> {
    return this.http.patch(`${this.apiUrl}/${cartItemId}`, { quantity });
  }

  deleteCartItem(cartItemId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${cartItemId}`);
  }

  clearCart(): Observable<any> {
    return this.http.delete(`${this.apiUrl}`);
  }
}
