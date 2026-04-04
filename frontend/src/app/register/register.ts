import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TranslatePipe, TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink, TranslatePipe],
  templateUrl: './register.html',
  styleUrl: './register.scss',
})
export class Register {
  email = '';
  password = '';

  errorMessage = '';

  passwordRequirements = {
    length: false,
    digit: false,
    lowercase: false,
    uppercase: false,
    special: false,
    noSpaces: true,
  };

  constructor(
    private authService: AuthService,
    private router: Router,
    private translate: TranslateService,
  ) {}

  register(): void {
    this.authService.register({ email: this.email, password: this.password }).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['/']);
      },
      error: () => {
        this.errorMessage = this.translate.instant('register.error');
      }
    });
  }

  checkPassword(): void {
    this.passwordRequirements = {
      length: this.password.length >= 8 && this.password.length <= 30,
      digit: /\d/.test(this.password),
      lowercase: /[a-z]/.test(this.password),
      uppercase: /[A-Z]/.test(this.password),
      special: /[^a-zA-Z0-9]/.test(this.password),
      noSpaces: !/\s/.test(this.password),
    };
  }

  allRequirementsMet(): boolean {
    return Object.values(this.passwordRequirements).every((requirement) => requirement);
  }
}
