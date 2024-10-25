import { Component } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { OrderRow } from '../models/OrderRow'
import { OrderService } from '../services/order.service'

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule], //HTMLs olevad eriasjad
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent {
  cart: OrderRow[] = [];
  view = "cart";
  email = "";

  constructor(private orderService: OrderService) {} // TS-s olevad teised failid
  
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
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

  changeView(newView: string) {
    this.view = newView;
  }

  sendOrderToBE() {
    const order = {
      person: {email: this.email},
      orderRows: this.cart
    };
    this.orderService.saveOrder(order).subscribe();
  }
}
