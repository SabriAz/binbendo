import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { TranslateModule, TranslateService } from '@ngx-translate/core';

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
    private translate: TranslateService,
  ) {
    translate.setDefaultLang('nl');
    translate.use('nl');
  }

  switchLang(lang: string) {
    this.currentLang = lang;
    this.translate.use(lang);
  }
}
