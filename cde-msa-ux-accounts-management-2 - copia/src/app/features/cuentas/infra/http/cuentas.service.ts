import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CuentasService {
  private apiUrl = 'http://localhost:8080/accounts';

  constructor(private http: HttpClient) {}

  getCuentas(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getCuentaByNumber(accountNumber: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?accountNumber=${accountNumber}`);
  }

  createCuenta(cuenta: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, cuenta);
  }

  updateCuenta(cuenta: any): Observable<any> {
    return this.http.put<any>(this.apiUrl, cuenta);
  }

  deleteCuenta(accountNumber: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}?accountNumber=${accountNumber}`);
  }
}
