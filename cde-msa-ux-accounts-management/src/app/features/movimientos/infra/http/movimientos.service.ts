import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class MovimientosService {
  private apiUrl = 'http://localhost:8080/transactions';

  constructor(private http: HttpClient) {}

  getMovimientos(params?: any): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl, { params });
  }

  createMovimiento(movimiento: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, movimiento);
  }

  updateMovimiento(movimiento: any): Observable<any> {
    return this.http.put<any>(this.apiUrl, movimiento);
  }

  deleteMovimiento(transactionId: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}?transactionId=${transactionId}`);
  }
}
