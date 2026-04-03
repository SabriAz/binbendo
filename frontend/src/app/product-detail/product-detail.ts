import { Component, signal } from '@angular/core';
import { Product } from '../models/product.model';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { CurrencyPipe } from '@angular/common';
import { CartService } from '../services/cart.service';
import { TranslatePipe } from '@ngx-translate/core';
import { Location } from '@angular/common';


@Component({
  selector: 'app-product-detail',
  imports: [CurrencyPipe, TranslatePipe],
  templateUrl: './product-detail.html',
  styleUrl: './product-detail.scss',
})
export class ProductDetail {
  product = signal<Product | null>(null);
  quantity = signal(1);

  // Voor melding dat add to cart gelukt is
  addedToCart = signal(false);

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService,
    private location: Location,
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.productService.getProductById(+id!).subscribe((product) => this.product.set(product));
  }

  addToCart(): void {
    this.cartService.addToCart(this.product()!.id, this.quantity()).subscribe({
      next: () => {
        this.addedToCart.set(true);
        setTimeout(() => this.addedToCart.set(false), 1100);
      },
    });
  }

  goBack(): void {
    this.location.back();
  }
}
