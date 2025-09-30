// Interfaces para el dominio de Clientes
export interface ClientePort {
  getClientes(): Promise<Cliente[]>;
  getClienteById(id: string): Promise<Cliente | null>;
  createCliente(cliente: Cliente): Promise<Cliente>;
  updateCliente(cliente: Cliente): Promise<Cliente>;
  deleteCliente(id: string): Promise<void>;
}

export interface Cliente {
  id: string;
  nombre: string;
  genero: string;
  edad: number;
  identificacion: string;
  direccion: string;
  telefono: string;
  contrasena: string;
  estado: boolean;
}
