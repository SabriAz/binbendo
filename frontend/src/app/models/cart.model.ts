import { CartItem } from './cartItem.model';

export interface Cart {
  id: number;
  items: CartItem[];
  totalPrice: number;
}
