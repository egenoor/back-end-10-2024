import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { BehaviorSubject, Observable, Subject } from 'rxjs'
import { Person } from '../models/Person'
import { Token } from '../models/Token'

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedInSubject = new BehaviorSubject(sessionStorage.getItem("token") !== null);
  adminSubject = new Subject();

  constructor(private http: HttpClient) { }

  registrate(person: Person): Observable<Token> {
    return this.http.post<Token>("http://localhost:8080/signup", person);
  }

  login(email: string, password: string): Observable<Token> {
    return this.http.post<Token>("http://localhost:8080/login", {email, password});
  }

  admin(): Observable<boolean> {
    return this.http.get<boolean>("http://localhost:8080/admin", 
      {headers: {"Authorization": "Bearer " + (sessionStorage.getItem("token")) || ""}}
    );
  }

  signup(person: Person): Observable<Token> {
    return this.http.post<Token>("http://localhost:8080/signup", person);
  }
}
