import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class ParcelMachineService {

  constructor(private http: HttpClient) { }

  getParcelMachines(): Observable<any> {
    return this.http.get<any>("http://localhost:8080/parcel-machines");
  }

  getParcelMachinesByCountry(country: string): Observable<any> {
    return this.http.get<any>("http://localhost:8080/parcel-machines/" + country);
  }
}
