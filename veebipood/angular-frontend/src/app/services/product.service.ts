import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { Product } from '../models/Product'
import { ProductPage } from '../models/ProductPage'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProducts(pageNr: number, pageSize: number): Observable<ProductPage> {
    return this.http.get<ProductPage>("http://localhost:8080/public-products", {params: {page: pageNr, pageSize: pageSize}});
  }

  getProduct(productId: number): Observable<Product> {
    return this.http.get<Product>("http://localhost:8080/product", {params: {id: productId}})
  }

  getProductsByName(search: string): Observable<ProductPage> {
    return this.http.get<ProductPage>("http://localhost:8080/find-by-name", {params: {name: search}})
  }

  addProduct(product: Product): Observable<void> {
    const headers = new HttpHeaders();
    // headers.set("Authorization","Bearer " + sessionStorage.getItem("token") || "")
    console.log(headers);
    return this.http.post<void>("http://localhost:8080/products", 
      product,
      {headers: {"Authorization": "Bearer " + (sessionStorage.getItem("token")) || ""}}
    );
  }
}
