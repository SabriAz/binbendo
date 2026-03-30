import { Routes } from '@angular/router';
import { Products } from './product/products';
import { Login } from './login/login';
import { Register } from './register/register';
import { Cart } from './cart/cart';

export const routes: Routes = [
  {path: '', component: Products },
  {path: 'cart', component: Cart },
  {path: 'login', component: Login},
  {path: 'register', component: Register}
];
