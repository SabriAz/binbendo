import { Component, signal } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Cart } from '../models/cart.model';
import { CurrencyPipe } from '@angular/common';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-cart',
  imports: [CurrencyPipe],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent {
  cart = signal<Cart | null>(null);

  constructor(
    private cartService: CartService,
    private orderService: OrderService,
  ) {}

  ngOnInit(): void {
    this.cartService.getCart().subscribe((data) => this.cart.set(data));
  }

  updateQuantity(id: number, quantity: number): void {
    this.cartService.updateQuantity(id, quantity).subscribe(() => {
      this.cartService.getCart().subscribe((data) => this.cart.set(data));
    });
  }

  deleteItem(id: number): void {
    this.cartService.deleteCartItem(id).subscribe(() => {
      this.cartService.getCart().subscribe((data) => this.cart.set(data));
    });
  }

  clearCart(): void {
    this.cartService.clearCart().subscribe(() => {
      this.cart.set(null);
    });
  }

  placeOrder(): void {
    this.orderService.placeOrder().subscribe({
      next: () => this.cartService.getCart().subscribe((data) => this.cart.set(data)),
      error: () => {},
    });
  }
}
