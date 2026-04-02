import { Component, signal } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { OrderService } from '../services/order.service';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-profile',
  imports: [CurrencyPipe],
  templateUrl: './profile.html',
  styleUrl: './profile.scss',
})
export class Profile {
  orders = signal<any[] | null>(null);
  showOrders = false;

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

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }
}
