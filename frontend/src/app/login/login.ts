import { Component } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  email = '';
  password = '';

  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  login(): void {
    this.authService.login({ email: this.email, password: this.password }).subscribe({
    next: (response) => {
      localStorage.setItem('token', response.token);
      this.router.navigate(['/']);
    },
      error: () => {this.errorMessage = 'E-mailadres of wachtwoord klopt niet';
      }
    });
  }
}
