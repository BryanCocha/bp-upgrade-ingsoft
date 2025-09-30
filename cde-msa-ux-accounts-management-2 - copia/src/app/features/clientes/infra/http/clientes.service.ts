import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ClientesService {
  private apiUrl = 'http://localhost:8080/customers';

  constructor(private http: HttpClient) {}

  getClientes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getClienteByIdentification(identification: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}?identification=${identification}`);
  }

  createCliente(cliente: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, cliente);
  }

  updateCliente(cliente: any): Observable<any> {
    return this.http.put<any>(this.apiUrl, cliente);
  }

  deleteCliente(identification: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}?identification=${identification}`);
  }
}
