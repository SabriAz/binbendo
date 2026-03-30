import { Category } from './category.model';

export interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  category?: Category;
}

// Category is a JsonBackReference so not getting it through the product endpoints, might have a use later
