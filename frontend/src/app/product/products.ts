import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../models/product.model';
import { CurrencyPipe } from '@angular/common';
import {signal} from '@angular/core';

@Component({
  selector: 'app-product',
  imports: [CurrencyPipe],
  templateUrl: './products.html',
  styleUrl: './products.scss',
})
export class Products implements OnInit {
  products = signal<Product[]>([]);

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.getProducts().subscribe((data) => (this.products.set(data)));
  }
}
