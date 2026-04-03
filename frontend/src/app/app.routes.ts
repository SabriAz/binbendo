import { Routes } from '@angular/router';
import { Products } from './product/products';
import { Login } from './login/login';
import { Register } from './register/register';
import { CartComponent } from './cart/cart.component';
import { ProductDetail } from './product-detail/product-detail';
import { Profile } from './profile/profile';

export const routes: Routes = [
  { path: '', component: Products },
  { path: 'product/:id', component: ProductDetail },
  { path: 'cart', component: CartComponent },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'profile', component: Profile }

];
