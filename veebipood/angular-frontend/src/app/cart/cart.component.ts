import { DatePipe } from '@angular/common'
import { Component } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { RouterLink } from '@angular/router'
import { OrderRow } from '../models/OrderRow'
import { ParcelMachinesComponent } from './parcel-machines/parcel-machines.component'
import { PaymentComponent } from "./payment/payment.component"

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule, RouterLink, ParcelMachinesComponent, PaymentComponent, DatePipe],
  templateUrl: './cart.component.html'
})
export class CartComponent {
  date = new Date();
  cart: OrderRow[] = [];
  isLoggedIn = sessionStorage.getItem("token") !== null;
  // view = "cart";
  // email = "";
  
  ngOnInit(): void {
    this.cart = JSON.parse(localStorage.getItem("cart") || "[]");
  }

  decreaseQuantity(index: number){
    this.cart[index].pcs--;
    localStorage.setItem("cart", JSON.stringify(this.cart))
  }

  increaseQuantity(index: number){
    this.cart[index].pcs++;
    localStorage.setItem("cart", JSON.stringify(this.cart))
  }

  removeFromCart(index: number){
    this.cart.splice(index, 1);
    localStorage.setItem("cart", JSON.stringify(this.cart))
  }

  calculateTotal() {
    let sum = 0;
    this.cart.forEach(orderRow => {
      sum += orderRow.product.price * orderRow.pcs
    });
    return sum;
  }

  // changeView(newView: string) {
  //   this.view = newView;
  // }

}
