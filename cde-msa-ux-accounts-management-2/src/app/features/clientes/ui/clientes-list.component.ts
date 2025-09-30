import { Component } from '@angular/core';
import { ClientesService } from 'src/app/features/clientes/infra/http/clientes.service';

@Component({
  selector: 'app-clientes-list',
  templateUrl: './clientes-list.component.html',
  styleUrls: ['./clientes-list.component.css']
})
export class ClientesListComponent {
  clientes: any[] = [];
  busqueda: string = '';
  nuevoCliente: any = {
    name: '', gender: '', age: '', identification: '', address: '', phone: '', customerId: '', password: '', state: true
  };
  editando: boolean = false;
  clienteEditando: any = null;

  constructor(private clientesService: ClientesService) {}

  ngOnInit() {
    this.cargarClientes();
  }

  cargarClientes() {
    this.clientesService.getClientes().subscribe({
      next: (data: any[]) => this.clientes = data,
      error: () => this.clientes = []
    });
  }

  mensajeBusqueda: string = '';
  respuestaCruda: any = null;
  buscarCliente() {
    if (!this.busqueda) return;
    this.clientesService.getClienteByIdentification(this.busqueda).subscribe({
      next: (data: any[]) => {
        this.respuestaCruda = data;
        this.clientes = Array.isArray(data) ? data : [];
        this.mensajeBusqueda = this.clientes.length === 0 ? 'No se encontró el cliente.' : '';
        console.log('Respuesta backend:', data);
      },
      error: () => {
        this.clientes = [];
        this.mensajeBusqueda = 'Error al buscar el cliente.';
        this.respuestaCruda = null;
      }
    });
  }

  mensajeCreacion: string = '';

  cargarTodosClientes() {
    this.clientesService.getClientes().subscribe({
      next: (data: any[]) => {
        this.clientes = Array.isArray(data) ? data : [];
      },
      error: () => alert('Error al cargar clientes')
    });
  }

  crearCliente() {
    const campos = [
      { key: 'name', label: 'Nombre' },
      { key: 'gender', label: 'Género' },
      { key: 'age', label: 'Edad' },
      { key: 'identification', label: 'Identificación' },
      { key: 'address', label: 'Dirección' },
      { key: 'phone', label: 'Teléfono' },
      { key: 'password', label: 'Contraseña' }
    ];
    for (let i = 0; i < campos.length; i++) {
      const campo = campos[i];
      if (!this.nuevoCliente[campo.key]) {
        alert(`El campo ${campo.label} es obligatorio.`);
        return;
      }
    }
    this.nuevoCliente.age = Number(this.nuevoCliente.age);
    if (isNaN(this.nuevoCliente.age) || this.nuevoCliente.age <= 0) {
      alert('La edad debe ser un número válido mayor a 0.');
      return;
    }
    this.clientesService.createCliente(this.nuevoCliente).subscribe({
      next: (data: any) => {
        // Agrega el nuevo cliente a la tabla directamente
        this.clientes.push(data);
        this.nuevoCliente = { name: '', gender: '', age: '', identification: '', address: '', phone: '', password: '', state: true };
        this.mensajeCreacion = 'Cliente creado exitosamente.';
        setTimeout(() => this.mensajeCreacion = '', 2000);
      },
      error: () => alert('Error al crear cliente')
    });
  }

  editarCliente(cliente: any) {
    this.editando = true;
    this.clienteEditando = { ...cliente };
  }

  actualizarCliente() {
    // Prepara el objeto para el backend
    const clienteActualizado = {
      name: this.clienteEditando.name,
      gender: this.clienteEditando.gender,
      age: Number(this.clienteEditando.age),
      identification: this.clienteEditando.identification,
      address: this.clienteEditando.address,
      phone: this.clienteEditando.phone,
      customerId: this.clienteEditando.customerId,
      password: this.clienteEditando.password,
      state: this.clienteEditando.state
    };
    this.clientesService.updateCliente(clienteActualizado).subscribe({
      next: (data: any) => {
        // Actualiza el cliente en la tabla con la respuesta del backend
        this.clientes = this.clientes.map(c =>
          c.identification === data.identification ? data : c
        );
        this.editando = false;
        this.clienteEditando = null;
      },
      error: () => alert('Error al actualizar cliente')
    });
  }

  eliminarCliente(cliente: any) {
    if (!confirm('¿Seguro que deseas eliminar este cliente?')) return;
    this.clientesService.deleteCliente(cliente.identification).subscribe({
      next: () => {
        // Elimina el cliente de la tabla sin recargar
        this.clientes = this.clientes.filter(c => c.identification !== cliente.identification);
      },
      error: () => alert('Error al eliminar cliente')
    });
  }

  cancelarEdicion() {
    this.editando = false;
    this.clienteEditando = null;
  }
}
