import { Component } from '@angular/core';
import { ReportesService } from '../infra/http/reportes.service';

@Component({
  selector: 'app-reportes-list',
  templateUrl: './reportes-list.component.html',
  styleUrls: ['./reportes-list.component.css']
})
export class ReportesListComponent {
  identification: string = '';
  transactionDate: string = '';
  transacciones: any[] = [];
  cargando: boolean = false;

  constructor(private reportesService: ReportesService) {}

  buscarTransacciones() {
    if (!this.identification || !this.transactionDate) {
      alert('Debe ingresar la identificación y la fecha.');
      return;
    }
    this.cargando = true;
    this.reportesService.getTransacciones(this.identification, this.transactionDate).subscribe({
      next: (data: any[]) => {
        this.transacciones = data;
        this.cargando = false;
      },
      error: (err) => {
        if (err.status === 404) {
          this.transacciones = [];
          alert('Cliente no encontrado');
        } else {
          alert('Error al consultar transacciones');
        }
        this.cargando = false;
      }
    });
  }

  descargarPDF() {
    if (!this.identification || !this.transactionDate) {
      alert('Debe ingresar la identificación y la fecha.');
      return;
    }
    this.reportesService.descargarPDF(this.identification, this.transactionDate).subscribe({
      next: (blob: Blob) => {
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `reporte_${this.identification}_${this.transactionDate}.pdf`;
        a.click();
        window.URL.revokeObjectURL(url);
      },
      error: () => alert('Error al descargar PDF')
    });
  }
}
