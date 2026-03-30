import { Component, computed, OnInit } from '@angular/core';
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
  selectedCategories = signal<number[]>([]);

  searchQuery = signal('');

  constructor(private productService: ProductService, private categoryService: CategoryService) {}

  toggleCategories(id: number) {
    const currentCategories = this.selectedCategories();

    if (currentCategories.includes(id)) {
      this.selectedCategories.set(currentCategories.filter(category => category !== id));
    } else {
      this.selectedCategories.set([...currentCategories, id]);
    }

    if (this.selectedCategories().length === 0) {
      this.productService.getAllProducts().subscribe(data => this.products.set(data))
    } else {
      this.productService.getProductsByCategories(this.selectedCategories()).subscribe(data => this.products.set(data)) //ofzo
    }
  }

  filteredProducts = computed(() =>
  this.products().filter(p =>
  p.name.toLowerCase().includes(this.searchQuery().toLowerCase())))

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((data) => this.products.set(data));
    this.categoryService.getAllCategories().subscribe((data) => this.categories.set(data));
  }
}
