import { Component } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { RouterLink } from '@angular/router'
import { Order } from '../models/Order'
import { OrderRow } from '../models/OrderRow'
import { OrderService } from '../services/order.service'
import { ParcelMachineService } from '../services/parcel-machine.service'

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule, RouterLink], //HTMLs olevad eriasjad
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent {
  cart: OrderRow[] = [];
  isLoggedIn = sessionStorage.getItem("token") !== null;
  // view = "cart";
  // email = "";
  parcelMachines: any[] = [];

  constructor(private orderService: OrderService,
    private parcelMachineService: ParcelMachineService) {} // TS-s olevad teised failid
  
  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.cart = JSON.parse(localStorage.getItem("cart") || "[]");
    this.parcelMachineService.getParcelMachines().subscribe(res => {
      this.parcelMachines = res;
    })
  }

  getPMsByCountry(country: string) {
    this.parcelMachineService.getParcelMachinesByCountry(country).subscribe(res => {
      this.parcelMachines = res;
    })
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

  sendOrderToBE() {
    const order: Order = {
      // person: {email: this.email},
      orderRows: this.cart
    };
    if(sessionStorage.getItem("token") === null) {
      return;
    }
    this.orderService.saveOrder(order).subscribe();
  }
}
