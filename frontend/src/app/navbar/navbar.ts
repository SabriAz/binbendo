import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import { CartService } from '../services/cart.service';
import { ColorblindService } from '../services/colorblind.service';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, TranslateModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar {
  currentLang = 'nl';

  constructor(
    public authService: AuthService,
    public cartService: CartService,
    public colorblindService: ColorblindService,
    private translate: TranslateService,
  ) {
    translate.setDefaultLang('nl');
    translate.use('nl');
    if (authService.isLoggedIn()) {
      cartService.refreshCount();
    }
  }

  switchLang(lang: string) {
    this.currentLang = lang;
    this.translate.use(lang);
  }
}
