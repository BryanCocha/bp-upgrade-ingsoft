// Interfaces para el dominio de Reportes
export interface ReportePort {
  getReportes(): Promise<Reporte[]>;
  getReporteById(id: string): Promise<Reporte | null>;
  createReporte(reporte: Reporte): Promise<Reporte>;
  updateReporte(reporte: Reporte): Promise<Reporte>;
  deleteReporte(id: string): Promise<void>;
}

export interface Reporte {
  id: string;
  fecha: string;
  clienteId: string;
  cuentas: any[];
  movimientos: any[];
  saldoDisponible: number;
}
