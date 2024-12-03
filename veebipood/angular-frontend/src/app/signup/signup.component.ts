import { Component } from '@angular/core'
import { FormsModule, NgForm } from '@angular/forms'
import { Router } from '@angular/router'
import { Person } from '../models/Person'
import { Token } from '../models/Token'
import { AuthService } from '../services/auth.service'
import { ErrorResponse } from '../models/ErrorMessage'


@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {
  constructor(private authService: AuthService, private router: Router){}
  message: string = "";

  signup(form: NgForm) {
    const email = form.value.email;
    // if (email === "") {
    //   this.message = "Email cannot be empty";
    // }
    const password = form.value.password;
    const firstName = form.value.firstName;
    const lastName = form.value.lastName;
    const person: Person = new Person(email, password, firstName, lastName);

    this.authService.signup(person).subscribe({
      next: this.handleUpdateResponse.bind(this),
      error: this.handleError.bind(this)
    })
  }

  private handleUpdateResponse(res: Token) {
    sessionStorage.setItem("token", res.token);
    sessionStorage.setItem("expiration", res.expiration.getTime().toString());
    this.authService.loggedInSubject.next(true);
    this.authService.adminSubject.next(true);
    this.router.navigateByUrl("/");
  }

  private handleError(res: ErrorResponse) {
    this.message = res.error.name;
  }
}
