import { Component, signal } from '@angular/core';
import { CartService } from '../services/cart.service';
import { Cart } from '../models/cart.model';
import { CurrencyPipe } from '@angular/common';
import { OrderService } from '../services/order.service';
import { RouterLink } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-cart',
  imports: [CurrencyPipe, RouterLink, TranslatePipe],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent {
  cart = signal<Cart | null>(null);

  confirmDeleteId = signal<number | null>(null);

  // Voor bestelling geplaatst melding
  orderPlaced = signal(false);

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
    if (this.confirmDeleteId() === id) {
      this.cartService.deleteCartItem(id).subscribe(() => {
        this.confirmDeleteId.set(null);
        this.cartService.getCart().subscribe((data) => this.cart.set(data));
      });
    } else {
      this.confirmDeleteId.set(id);
    }
  }

  clearCart(): void {
    this.cartService.clearCart().subscribe(() => {
      this.cartService.getCart().subscribe((data) => this.cart.set(data));
    });
  }

  placeOrder(): void {
    this.orderService.placeOrder().subscribe({
      next: () => {
        this.orderPlaced.set(true);
        this.cartService.getCart().subscribe((data) => this.cart.set(data));
      },
      error: () => {},
    });
  }
}
