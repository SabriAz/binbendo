import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { CategoryService } from '../services/category.service';
import { Product } from '../models/product.model';
import { CurrencyPipe } from '@angular/common';
import {signal} from '@angular/core';
import { Category } from '../models/category.model';

@Component({
  selector: 'app-product',
  imports: [CurrencyPipe],
  templateUrl: './products.html',
  styleUrl: './products.scss',
})
export class Products implements OnInit {
  products = signal<Product[]>([]);
  categories = signal<Category[]>([]);

  constructor(private productService: ProductService, private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((data) => this.products.set(data));
    this.categoryService.getAllCategories().subscribe((data) => this.categories.set(data));
  }
}
