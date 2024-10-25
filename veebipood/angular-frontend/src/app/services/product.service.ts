import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { Product } from '../models/Product'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>("http://localhost:8080/all-products");
  }

  getProduct(productId: number): Observable<Product> {
    return this.http.get<Product>("http://localhost:8080/product", {params: {id: productId}})
  }
}
