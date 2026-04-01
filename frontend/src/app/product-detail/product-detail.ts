import { Component, signal } from '@angular/core';
import { Product } from '../models/product.model';
import { ProductService } from '../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-product-detail',
  imports: [CurrencyPipe],
  templateUrl: './product-detail.html',
  styleUrl: './product-detail.scss',
})
export class ProductDetail {
  product = signal<Product | null>(null);
  quantity = signal(1);

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
  ) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.productService.getProductById(+id!).subscribe((product) => this.product.set(product));
  }
}
