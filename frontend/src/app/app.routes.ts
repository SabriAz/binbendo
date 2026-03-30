import { Routes } from '@angular/router';
import { Products } from './product/products';
import { Login } from './login/login';
import { Register } from './register/register';

export const routes: Routes = [
  {path: '', component: Products },
  {path: 'login', component: Login},
  {path: 'register', component: Register}
];
