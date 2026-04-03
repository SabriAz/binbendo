import { Component, computed, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { CategoryService } from '../services/category.service';
import { Product } from '../models/product.model';
import { CurrencyPipe } from '@angular/common';
import {signal} from '@angular/core';
import { Category } from '../models/category.model';
import { RouterLink } from '@angular/router';
import { TranslatePipe } from '@ngx-translate/core';
import { FilterStateService } from '../services/filter-state.service';

@Component({
  selector: 'app-product',
  imports: [CurrencyPipe, RouterLink, TranslatePipe],
  templateUrl: './products.html',
  styleUrl: './products.scss',
})
export class Products implements OnInit {
  products = signal<Product[]>([]);
  categories = signal<Category[]>([]);

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    public filterState: FilterStateService,
  ) {}

  toggleCategories(id: number) {
    const currentCategories = this.filterState.selectedCategories();

    if (currentCategories.includes(id)) {
      this.filterState.selectedCategories.set(
        currentCategories.filter((category) => category !== id),
      );
    } else {
      this.filterState.selectedCategories.set([...currentCategories, id]);
    }

    if (this.filterState.selectedCategories().length === 0) {
      this.productService.getAllProducts().subscribe((data) => this.products.set(data));
    } else {
      this.productService
        .getProductsByCategories(this.filterState.selectedCategories())
        .subscribe((data) => this.products.set(data));
    }
  }

  clearSearch(): void {
    this.filterState.searchQuery.set('');
  }

  clearCategories(): void {
    this.filterState.selectedCategories.set([]);
    this.productService.getAllProducts().subscribe((data) => this.products.set(data));
  }

  filteredProducts = computed(() =>
    this.products().filter((p) =>
      p.name.toLowerCase().includes(this.filterState.searchQuery().toLowerCase()),
    ),
  );

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe((data) => this.products.set(data));
    this.categoryService.getAllCategories().subscribe((data) => this.categories.set(data));
  }
}
