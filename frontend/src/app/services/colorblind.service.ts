import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ColorblindService {
  isColorblind = signal<boolean>(false);

  constructor() {
    // Onthoud status
    const saved = localStorage.getItem('colorblind');
    if (saved === 'true') {
      this.enable();
    }
  }

  toggle() {
    this.isColorblind() ? this.disable() : this.enable();
  }

  private enable() {
    this.isColorblind.set(true);
    document.body.classList.add('colorblind');
    localStorage.setItem('colorblind', 'true');
  }

  private disable() {
    this.isColorblind.set(false);
    document.body.classList.remove('colorblind');
    localStorage.setItem('colorblind', 'false');
  }
}
