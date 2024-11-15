import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { Order } from '../models/Order'

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  saveOrder(order: Order): Observable<Order[]> {
    return this.http.post<Order[]>("http://localhost:8080/order", order,
      {headers: {"Authorization": "Bearer " + (sessionStorage.getItem("token") || "")}}
    );
  }
}
