import { Component } from '@angular/core'
import { Router, RouterLink } from '@angular/router'
import { AuthService } from '../services/auth.service'

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  loggedIn = false;
  showButton = false;
  constructor(private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // behaviorSubjecti .subscribe käivitub kohe
    this.showAdmin();
    this.authService.loggedInSubject.subscribe(res => {
      this.loggedIn = res;
    })

    // tavalise subjecti .subscribe ei lähe kohe käima -> läheb käima ainult .nexti abil
    this.showAdmin();
    this.authService.adminSubject.subscribe(() => {
      this.showAdmin();
    })
  }

  showAdmin() {
    this.authService.admin().subscribe(res => {
      this.showButton = res;
    })
  }

  logout(){
    sessionStorage.clear();
    this.authService.loggedInSubject.next(false);
    this.authService.adminSubject.next(false);
    this.router.navigateByUrl("/");
  }
}
