// Implementación HTTP de ClientePort
import { ClientePort, Cliente } from '../../model/ports';

export class ClienteHttpAdapter implements ClientePort {
  async getClientes(): Promise<Cliente[]> {
    // TODO: Implementar llamada HTTP al endpoint de clientes
    return [];
  }
  async getClienteById(id: string): Promise<Cliente | null> {
    // TODO: Implementar llamada HTTP al endpoint de cliente por id
    return null;
  }
  async createCliente(cliente: Cliente): Promise<Cliente> {
    // TODO: Implementar llamada HTTP para crear cliente
    return cliente;
  }
  async updateCliente(cliente: Cliente): Promise<Cliente> {
    // TODO: Implementar llamada HTTP para actualizar cliente
    return cliente;
  }
  async deleteCliente(id: string): Promise<void> {
    // TODO: Implementar llamada HTTP para eliminar cliente
  }
}
