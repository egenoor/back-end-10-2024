import { Component, Input } from '@angular/core'
import { Order } from '../../models/Order'
import { OrderRow } from '../../models/OrderRow'
import { OrderService } from '../../services/order.service'

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.scss'
})
export class PaymentComponent {
  @Input() cart: OrderRow[] = [];

  constructor(private orderService: OrderService) {}
  sendOrderToBE() {
    const order: Order = {
      orderRows: []
    };

    if(sessionStorage.getItem("token") === null) {
      return;
    }
    this.orderService.saveOrder(order).subscribe(res => {
      window.location.href = res.link;
    });
  }
}
