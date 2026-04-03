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
  confirmClearCart = signal(false);

  // Voor bestelling geplaatst melding
  orderPlaced = signal(false);

  constructor(
    private cartService: CartService,
    private orderService: OrderService,
  ) {}

  ngOnInit(): void {
    this.cartService.getCart().subscribe((data) => {
      this.cart.set(data);
      this.cartService.refreshCount();
    });
  }

  updateQuantity(id: number, quantity: number): void {
    this.cartService.updateQuantity(id, quantity).subscribe(() => {
      this.cartService.getCart().subscribe((data) => this.cart.set(data));
      this.cartService.refreshCount();
    });
  }

  deleteItem(id: number): void {
    if (this.confirmDeleteId() === id) {
      this.cartService.deleteCartItem(id).subscribe(() => {
        this.confirmDeleteId.set(null);
        this.cartService.getCart().subscribe((data) => this.cart.set(data));
        this.cartService.refreshCount();
      });
    } else {
      this.confirmDeleteId.set(id);
    }
  }

  clearCart(): void {
    if (this.confirmClearCart()) {
      this.cartService.clearCart().subscribe(() => {
        this.confirmClearCart.set(false);
        this.cartService.getCart().subscribe((data) => this.cart.set(data));
        this.cartService.refreshCount();
      });
    } else {
      this.confirmClearCart.set(true);
    }
  }

  placeOrder(): void {
    this.orderService.placeOrder().subscribe({
      next: () => {
        this.orderPlaced.set(true);
        this.cartService.getCart().subscribe((data) => this.cart.set(data));
        this.cartService.refreshCount();
      },
      error: () => {},
    });
  }
}
