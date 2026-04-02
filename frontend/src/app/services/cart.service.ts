import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CartComponent } from '../cart/cart.component';
import { Cart } from '../models/cart.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private apiUrl = `${environment.apiUrl}/cart`;

  constructor(private http: HttpClient) {}

  getCart(): Observable<Cart> {
    return this.http.get<Cart>(`${this.apiUrl}`);
  }

  addToCart(productId: number, quantity: number): Observable<any> {
    return this.http.post(`${this.apiUrl}`, { productId, quantity }, { responseType: 'text' });
  }

  updateQuantity(cartItemId: number, quantity: number): Observable<any> {
    return this.http.patch(`${this.apiUrl}/${cartItemId}`, { quantity }, { responseType: 'text' });
  }

  deleteCartItem(cartItemId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${cartItemId}`, { responseType: 'text' });
  }

  clearCart(): Observable<any> {
    return this.http.delete(`${this.apiUrl}`,  { responseType: 'text' });
  }
}
