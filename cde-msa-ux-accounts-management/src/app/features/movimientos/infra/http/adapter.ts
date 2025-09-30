// Implementación HTTP de MovimientoPort
import { MovimientoPort, Movimiento } from '../../model/ports';

export class MovimientoHttpAdapter implements MovimientoPort {
  async getMovimientos(): Promise<Movimiento[]> {
    // TODO: Implementar llamada HTTP al endpoint de movimientos
    return [];
  }
  async getMovimientoById(id: string): Promise<Movimiento | null> {
    // TODO: Implementar llamada HTTP al endpoint de movimiento por id
    return null;
  }
  async createMovimiento(movimiento: Movimiento): Promise<Movimiento> {
    // TODO: Implementar llamada HTTP para crear movimiento
    return movimiento;
  }
  async updateMovimiento(movimiento: Movimiento): Promise<Movimiento> {
    // TODO: Implementar llamada HTTP para actualizar movimiento
    return movimiento;
  }
  async deleteMovimiento(id: string): Promise<void> {
    // TODO: Implementar llamada HTTP para eliminar movimiento
  }
}
