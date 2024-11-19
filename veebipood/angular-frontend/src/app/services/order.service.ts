import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { Order } from '../models/Order'

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  saveOrder(order: Order): Observable<{link: string}> {
    return this.http.post<{link: string}>("http://localhost:8080/order", order,
      {headers: {"Authorization": "Bearer " + (sessionStorage.getItem("token") || "")}}
    );
  }

  checkPayment(paymentReference: string): Observable<{status: string}> {
    return this.http.get<{status: string}>("http://localhost:8080/check-payment/" + paymentReference,
      {headers: {"Authorization": "Bearer " + (sessionStorage.getItem("token") || "")}}
    );
  }
}
