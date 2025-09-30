import { Component, OnInit } from '@angular/core';
import { CuentasService } from '../infra/http/cuentas.service';
import { ClientesService } from '../../clientes/infra/http/clientes.service';

@Component({
  selector: 'app-cuentas-list',
  templateUrl: './cuentas-list.component.html',
  styles: [`
    .cuentas-actions { margin-bottom: 1rem; }
    input { padding: 0.3rem; margin-right: 0.5rem; }
    button { background: #ffe600; border: none; padding: 0.3rem 1rem; cursor: pointer; }
  `]
})
  export class CuentasListComponent implements OnInit {
    cuentas: any[] = [];
    clientes: any[] = [];
    nuevaCuenta: any = {
      accountNumber: '',
      accountType: '',
      initialBalance: '',
      state: true,
      idMgmtCustomer: ''
    };
    editando: boolean = false;
    cuentaEditando: any = null;
    busqueda: string = '';

    constructor(private cuentasService: CuentasService, private clientesService: ClientesService) {}

    ngOnInit() {
      this.cargarCuentas();
      this.clientesService.getClientes().subscribe({
        next: (data: any[]) => this.clientes = data,
        error: () => this.clientes = []
      });
    }

    cargarCuentas() {
      this.cuentasService.getCuentas().subscribe({
        next: (data: any[]) => this.cuentas = data,
        error: () => this.cuentas = []
      });
    }

    buscarCuenta() {
      if (!this.busqueda) return;
      this.cuentasService.getCuentaByNumber(this.busqueda).subscribe({
        next: (data: any) => {
          this.cuentas = Array.isArray(data) ? data : [data];
        },
        error: (err) => {
          if (err.status === 404) {
            this.cuentas = [];
            alert('No se encontró ninguna cuenta con ese número.');
          } else {
            alert('Error al buscar cuenta');
          }
        }
      });
    }

    crearCuenta() {
      // Validación básica
      if (!this.nuevaCuenta.accountNumber || !this.nuevaCuenta.accountType || !this.nuevaCuenta.initialBalance || !this.nuevaCuenta.idMgmtCustomer) {
        alert('Todos los campos son obligatorios.');
        return;
      }
      this.nuevaCuenta.initialBalance = Number(this.nuevaCuenta.initialBalance);
      this.cuentasService.createCuenta(this.nuevaCuenta).subscribe({
        next: (data: any) => {
          this.cuentas.push(data);
          this.nuevaCuenta = { accountNumber: '', accountType: '', initialBalance: '', state: true, idMgmtCustomer: '' };
        },
        error: () => alert('Error al crear cuenta')
      });
    }

    editarCuenta(cuenta: any) {
      this.editando = true;
      this.cuentaEditando = { ...cuenta };
    }

    actualizarCuenta() {
      this.cuentaEditando.initialBalance = Number(this.cuentaEditando.initialBalance);
      this.cuentasService.updateCuenta(this.cuentaEditando).subscribe({
        next: (data: any) => {
          this.cuentas = this.cuentas.map(c => c.accountNumber === data.accountNumber ? data : c);
          this.editando = false;
          this.cuentaEditando = null;
        },
        error: () => alert('Error al actualizar cuenta')
      });
    }

    eliminarCuenta(cuenta: any) {
      if (!confirm('¿Seguro que deseas eliminar esta cuenta?')) return;
      this.cuentasService.deleteCuenta(cuenta.accountNumber).subscribe({
        next: () => {
          this.cuentas = this.cuentas.filter(c => c.accountNumber !== cuenta.accountNumber);
        },
        error: () => alert('Error al eliminar cuenta')
      });
    }

    cancelarEdicion() {
      this.editando = false;
      this.cuentaEditando = null;
    }

    obtenerNombreCliente(idMgmtCustomer: string): string {
      const cliente = this.clientes.find(c => c.mgmtCustomerId === idMgmtCustomer || c.idMgmtCustomer === idMgmtCustomer);
      return cliente ? cliente.name : idMgmtCustomer;
    }
  }
