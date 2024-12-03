import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class SupplierService {
  private backendUrl = "http://localhost:8080"
  constructor(private http: HttpClient) {}

  getSupplierProducts(): Observable<any> {
    return this.http.get<any>(this.backendUrl + "/supplier");
  }

  getSupplierEscuelaProducts(): Observable<any> {
    return this.http.get<any>(this.backendUrl + "/supplier-escuela");
  }
}
