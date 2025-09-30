import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ReportesService {
  private apiUrl = 'http://localhost:8080/reports';
  private pdfUrl = 'http://localhost:8080/reports/pdf';

  constructor(private http: HttpClient) {}

  getTransacciones(identification: string, transactionDate: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}?identification=${identification}&transactionDate=${transactionDate}`);
  }

  descargarPDF(identification: string, transactionDate: string): Observable<Blob> {
    return this.http.get(`${this.pdfUrl}?identification=${identification}&transactionDate=${transactionDate}`, { responseType: 'blob' });
  }
}
