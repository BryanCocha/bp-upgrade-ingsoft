// Interfaces para el dominio de Movimientos
export interface MovimientoPort {
  getMovimientos(): Promise<Movimiento[]>;
  getMovimientoById(id: string): Promise<Movimiento | null>;
  createMovimiento(movimiento: Movimiento): Promise<Movimiento>;
  updateMovimiento(movimiento: Movimiento): Promise<Movimiento>;
  deleteMovimiento(id: string): Promise<void>;
}

export interface Movimiento {
  id: string;
  fecha: string;
  tipo: string;
  valor: number;
  saldo: number;
  cuentaId: string;
}
