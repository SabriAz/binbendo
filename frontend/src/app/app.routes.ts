import { Routes } from '@angular/router';
import { Products } from './product/products';
import { Login } from './login/login';
import { Register } from './register/register';
import { Cart } from './cart/cart';
import { ProductDetail } from './product-detail/product-detail';

export const routes: Routes = [
  {path: '', component: Products },
  {path: 'product/:id', component: ProductDetail },
  {path: 'cart', component: Cart },
  {path: 'login', component: Login},
  {path: 'register', component: Register}
];
