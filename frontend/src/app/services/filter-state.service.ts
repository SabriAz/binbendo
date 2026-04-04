import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class FilterStateService {
  searchQuery = signal('');
  selectedCategories = signal<number[]>([]);
}
