// Implementación HTTP de CuentaPort
import { CuentaPort, Cuenta } from '../../model/ports';

export class CuentaHttpAdapter implements CuentaPort {
  async getCuentas(): Promise<Cuenta[]> {
    // TODO: Implementar llamada HTTP al endpoint de cuentas
    return [];
  }
  async getCuentaById(id: string): Promise<Cuenta | null> {
    // TODO: Implementar llamada HTTP al endpoint de cuenta por id
    return null;
  }
  async createCuenta(cuenta: Cuenta): Promise<Cuenta> {
    // TODO: Implementar llamada HTTP para crear cuenta
    return cuenta;
  }
  async updateCuenta(cuenta: Cuenta): Promise<Cuenta> {
    // TODO: Implementar llamada HTTP para actualizar cuenta
    return cuenta;
  }
  async deleteCuenta(id: string): Promise<void> {
    // TODO: Implementar llamada HTTP para eliminar cuenta
  }
}
