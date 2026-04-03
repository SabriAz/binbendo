import { Component, OnInit, signal } from '@angular/core';
import { Product } from '../models/product.model';
import { Category } from '../models/category.model';
import { ProductService } from '../services/product.service';
import { CategoryService } from '../services/category.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin',
  imports: [FormsModule],
  templateUrl: './admin.html',
  styleUrl: './admin.scss',
})
export class Admin implements OnInit {
  products = signal<Product[]>([]);
  categories = signal<Category[]>([]);

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((data) => this.products.set(data));
    this.categoryService.getAllCategories().subscribe((data) => this.categories.set(data));
  }

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
  ) {}
}
