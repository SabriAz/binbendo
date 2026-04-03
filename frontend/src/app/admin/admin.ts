import { Component, OnInit, signal } from '@angular/core';
import { Product } from '../models/product.model';
import { Category } from '../models/category.model';
import { ProductService } from '../services/product.service';
import { CategoryService } from '../services/category.service';
import { CurrencyPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin',
  imports: [CurrencyPipe, FormsModule],
  templateUrl: './admin.html',
  styleUrl: './admin.scss',
})
export class Admin implements OnInit {
  products = signal<Product[]>([]);
  categories = signal<Category[]>([]);

  // Product form
  editingProductId: number | null = null;
  showNewProductForm = false;
  productForm = { name: '', description: '', price: 0, imageUrl: '', categoryId: 0 };
  confirmDeleteProductId: number | null = null;

  // Category form
  editingCategoryId: number | null = null;
  showNewCategoryForm = false;
  categoryForm = { name: '' };
  confirmDeleteCategoryId: number | null = null;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
  ) {}

  ngOnInit(): void {
    this.loadProducts();
    this.loadCategories();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe((data) => this.products.set(data));
  }

  loadCategories(): void {
    this.categoryService.getAllCategories().subscribe((data) => this.categories.set(data));
  }

  // Product CRUD
  startEditProduct(product: Product): void {
    this.editingProductId = product.id;
    this.showNewProductForm = false;
    this.productForm = {
      name: product.name,
      description: product.description,
      price: product.price,
      imageUrl: product.imageUrl,
      categoryId: product.category?.id ?? 0,
    };
  }

  saveProduct(id: number): void {
    this.productService.updateProduct(id, this.productForm).subscribe(() => {
      this.editingProductId = null;
      this.productForm = { name: '', description: '', price: 0, imageUrl: '', categoryId: 0 };
      this.loadProducts();
    });
  }

  createProduct(): void {
    this.productService.createProduct(this.productForm).subscribe(() => {
      this.showNewProductForm = false;
      this.productForm = { name: '', description: '', price: 0, imageUrl: '', categoryId: 0 };
      this.loadProducts();
    });
  }

  deleteProduct(id: number): void {
    if (this.confirmDeleteProductId === id) {
      this.productService.deleteProduct(id).subscribe(() => {
        this.confirmDeleteProductId = null;
        this.loadProducts();
      });
    } else {
      this.confirmDeleteProductId = id;
    }
  }

  cancelProduct(): void {
    this.editingProductId = null;
    this.showNewProductForm = false;
    this.productForm = { name: '', description: '', price: 0, imageUrl: '', categoryId: 0 };
  }

  // Category CRUD
  startEditCategory(category: Category): void {
    this.editingCategoryId = category.id;
    this.showNewCategoryForm = false;
    this.categoryForm = { name: category.name };
  }

  saveCategory(id: number): void {
    this.categoryService.updateCategory(id, this.categoryForm).subscribe(() => {
      this.editingCategoryId = null;
      this.loadCategories();
    });
  }

  createCategory(): void {
    this.categoryService.createCategory(this.categoryForm).subscribe(() => {
      this.showNewCategoryForm = false;
      this.categoryForm = { name: '' };
      this.loadCategories();
    });
  }

  deleteCategory(id: number): void {
    if (this.confirmDeleteCategoryId === id) {
      this.categoryService.deleteCategory(id).subscribe(() => {
        this.confirmDeleteCategoryId = null;
        this.loadCategories();
      });
    } else {
      this.confirmDeleteCategoryId = id;
    }
  }

  cancelCategory(): void {
    this.editingCategoryId = null;
    this.showNewCategoryForm = false;
    this.categoryForm = { name: '' };
  }
}
