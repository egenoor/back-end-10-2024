import { Component, OnInit } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { OrderService } from '../services/order.service'

@Component({
  selector: 'app-payment-check',
  standalone: true,
  imports: [],
  templateUrl: './payment-check.component.html',
  styleUrl: './payment-check.component.scss'
})
export class PaymentCheckComponent implements OnInit {
  //localhost:4200/payment?order_reference=111002&payment_reference=d4c65b04fa3a5af7d74ba2163711f36dff5f4d1c5156c57f4424a9735a096899
  // rejected payment
  // localhost:4200/payment?order_reference=111003&payment_reference=a88f5767c70e8ab7cc631695734e2955fa41ed169c95926497abc6b46b8d1549
  paidStatus = "";

  constructor(private route: ActivatedRoute, private orderService: OrderService){}

  ngOnInit(): void {
    this.route.queryParams.subscribe(res => {
      const paymentReference = res["payment_reference"];
      this.orderService.checkPayment(paymentReference).subscribe(res => {
        this.paidStatus = res.status; // settled, cancelled, voided
      })
    })
  }
}
