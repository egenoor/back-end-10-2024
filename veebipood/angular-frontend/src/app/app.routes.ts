import { Routes } from '@angular/router'
import { CategoriesComponent } from './admin/categories/categories.component'
import { PersonComponent } from './admin/person/person.component'
import { CartComponent } from './cart/cart.component'
import { HomepageComponent } from './homepage/homepage.component'
import { LoginComponent } from './login/login.component'
import { SignupComponent } from './signup/signup.component'

export const routes: Routes = [
  {path: "", component: HomepageComponent},
  {path: "categories", component: CategoriesComponent},
  {path: "persons", component: PersonComponent},
  {path: "cart", component: CartComponent},
  {path: "login", component: LoginComponent},
  {path: "signup", component: SignupComponent},
];
