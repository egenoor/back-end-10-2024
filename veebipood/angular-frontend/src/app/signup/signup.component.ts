import { Component } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { AuthService } from '../services/auth.service'

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {
  constructor(private authService: AuthService){}

  // login(form: NgForm) {
  //   const email = form.value.email;
  //   const password = form.value.password;
  //   this.authService.signup(email, password).subscribe(res => {
  //     sessionStorage.setItem("token", res.token);
  //     sessionStorage.setItem("expiration", res.expiration.getTime().toString());
  //   });
  // }
}
