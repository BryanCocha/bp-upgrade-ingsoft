// Interfaces para el dominio de Cuentas
export interface CuentaPort {
  getCuentas(): Promise<Cuenta[]>;
  getCuentaById(id: string): Promise<Cuenta | null>;
  createCuenta(cuenta: Cuenta): Promise<Cuenta>;
  updateCuenta(cuenta: Cuenta): Promise<Cuenta>;
  deleteCuenta(id: string): Promise<void>;
}

export interface Cuenta {
  id: string;
  numero: string;
  tipo: string;
  saldoInicial: number;
  estado: boolean;
  clienteId: string;
}
