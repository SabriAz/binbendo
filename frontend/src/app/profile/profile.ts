import { Component, signal } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { OrderService } from '../services/order.service';
import { CurrencyPipe, DatePipe } from '@angular/common';
import { TranslatePipe } from '@ngx-translate/core';

@Component({
  selector: 'app-profile',
  imports: [CurrencyPipe, DatePipe, TranslatePipe],
  templateUrl: './profile.html',
  styleUrl: './profile.scss',
})
export class Profile {
  orders = signal<any[] | null>(null);
  showOrders = false;
  expandedOrderId: number | null = null;
  orderDetails = signal<{ [id: number]: any }>({});

  constructor(
    public authService: AuthService,
    private orderService: OrderService,
    private router: Router,
  ) {}

  loadOrders(): void {
    this.showOrders = true;
    if (this.orders() === null) {
      this.orderService.getOrders().subscribe((data) => this.orders.set(data));
    }
  }

  toggleOrder(id: number) {
    if (this.expandedOrderId === id) {
      this.expandedOrderId = null;
      return;
    }
    this.expandedOrderId = id;
    if (!this.orderDetails()[id]) {
      this.orderService.getOrderById(id).subscribe((data) => {
        this.orderDetails.update((current) => ({ ...current, [id]: data }));
      });
    }
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
