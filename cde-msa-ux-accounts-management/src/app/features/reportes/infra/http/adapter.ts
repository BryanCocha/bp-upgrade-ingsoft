// Implementación HTTP de ReportePort
import { ReportePort, Reporte } from '../../model/ports';

export class ReporteHttpAdapter implements ReportePort {
  async getReportes(): Promise<Reporte[]> {
    // TODO: Implementar llamada HTTP al endpoint de reportes
    return [];
  }
  async getReporteById(id: string): Promise<Reporte | null> {
    // TODO: Implementar llamada HTTP al endpoint de reporte por id
    return null;
  }
  async createReporte(reporte: Reporte): Promise<Reporte> {
    // TODO: Implementar llamada HTTP para crear reporte
    return reporte;
  }
  async updateReporte(reporte: Reporte): Promise<Reporte> {
    // TODO: Implementar llamada HTTP para actualizar reporte
    return reporte;
  }
  async deleteReporte(id: string): Promise<void> {
    // TODO: Implementar llamada HTTP para eliminar reporte
  }
}
