import { Component, OnInit } from '@angular/core';
import { MovimientosService } from '../infra/http/movimientos.service';
import { CuentasService } from '../../cuentas/infra/http/cuentas.service';

@Component({
  selector: 'app-movimientos-list',
  templateUrl: './movimientos-list.component.html',
  styleUrls: ['./movimientos-list.component.css']
})
  export class MovimientosListComponent implements OnInit {
    movimientos: any[] = [];
  cuentasPorId: { [id: string]: string } = {};
  cuentasPorNumero: { [numero: string]: any } = {};
    nuevoMovimiento: any = {
      transactionDate: '',
      transactionType: '',
      amount: '',
      balance: '',
      idMgmtAccount: ''
    };
    editando: boolean = false;
    movimientoEditando: any = null;
  busquedaFecha: string = '';
  busquedaNumeroCuenta: string = '';

    constructor(
      private movimientosService: MovimientosService,
      private cuentasService: CuentasService
    ) {}

    ngOnInit() {
      this.cargarMovimientos();
        this.cargarCuentas();
    }

      cargarCuentas() {
        // Obtiene todas las cuentas y crea mapas idMgmtAccount -> accountNumber y accountNumber -> cuenta
        this.cuentasService.getCuentas().subscribe({
          next: (cuentas: any[]) => {
            this.cuentasPorId = {};
            this.cuentasPorNumero = {};
            cuentas.forEach(c => {
              if ((c.idMgmtAccount || c.idAccount) && c.accountNumber) {
                const id = c.idMgmtAccount || c.idAccount;
                this.cuentasPorId[id] = c.accountNumber;
                this.cuentasPorNumero[c.accountNumber] = c;
              }
            });
          },
          error: () => {
            this.cuentasPorId = {};
            this.cuentasPorNumero = {};
          }
        });
      }

    cargarMovimientos() {
      this.movimientosService.getMovimientos().subscribe({
        next: (data: any[]) => this.movimientos = data,
        error: () => this.movimientos = []
      });
    }

    buscarMovimientos() {
      if (!this.busquedaFecha || !this.busquedaNumeroCuenta) return;
      // Buscar idMgmtAccount por número de cuenta
      this.cuentasService.getCuentaByNumber(this.busquedaNumeroCuenta).subscribe({
        next: (cuentas: any[]) => {
          const cuenta = Array.isArray(cuentas) ? cuentas[0] : cuentas;
          if (!cuenta || !cuenta.idAccount) {
            alert('No se encontró la cuenta.');
            return;
          }
          this.movimientosService.getMovimientos({ transactionDate: this.busquedaFecha, idMgmtAccount: cuenta.idAccount }).subscribe({
            next: (data: any[]) => {
              this.movimientos = data;
              this.cargarCuentas();
            },
            error: (err) => {
              if (err.status === 404) {
                this.movimientos = [];
                alert('No hay movimientos registrados.');
              } else {
                alert('Error al buscar movimientos');
              }
            }
          });
        },
        error: () => alert('No se pudo obtener la cuenta')
      });
    }
    obtenerNumeroCuenta(idMgmtAccount: string): string {
      return this.cuentasPorId[idMgmtAccount] || idMgmtAccount;
    }

    crearMovimiento() {
      if (!this.nuevoMovimiento.transactionType || !this.nuevoMovimiento.amount || !this.nuevoMovimiento.accountNumber) {
        alert('Todos los campos son obligatorios.');
        return;
      }
      this.nuevoMovimiento.amount = Number(this.nuevoMovimiento.amount);
      this.nuevoMovimiento.balance = Number(this.nuevoMovimiento.balance);
      // Buscar idMgmtAccount por número de cuenta
      this.cuentasService.getCuentaByNumber(this.nuevoMovimiento.accountNumber).subscribe({
        next: (cuentas: any[]) => {
          const cuenta = Array.isArray(cuentas) ? cuentas[0] : cuentas;
          if (!cuenta || !cuenta.idAccount) {
            alert('No se encontró la cuenta.');
            return;
          }
          const movimiento = {
            transactionType: this.nuevoMovimiento.transactionType,
            amount: this.nuevoMovimiento.amount,
            balance: this.nuevoMovimiento.balance,
            idMgmtAccount: cuenta.idAccount
          };
          this.movimientosService.createMovimiento(movimiento).subscribe({
            next: (data: any) => {
              this.movimientos.push(data);
              this.nuevoMovimiento = { transactionType: '', amount: '', balance: '', accountNumber: '' };
            },
            error: () => alert('Error al crear movimiento')
          });
        },
        error: () => alert('No se pudo obtener la cuenta')
      });
    }

    editarMovimiento(movimiento: any) {
      this.editando = true;
      this.movimientoEditando = { ...movimiento };
    }

    actualizarMovimiento() {
      this.movimientoEditando.amount = Number(this.movimientoEditando.amount);
      this.movimientoEditando.balance = Number(this.movimientoEditando.balance);
      this.movimientosService.updateMovimiento(this.movimientoEditando).subscribe({
        next: (data: any) => {
          this.movimientos = this.movimientos.map(m => m.transactionId === data.transactionId ? data : m);
          this.editando = false;
          this.movimientoEditando = null;
        },
        error: () => alert('Error al actualizar movimiento')
      });
    }

    eliminarMovimiento(movimiento: any) {
      if (!confirm('¿Seguro que deseas eliminar este movimiento?')) return;
      this.movimientosService.deleteMovimiento(movimiento.transactionId).subscribe({
        next: () => {
          this.movimientos = this.movimientos.filter(m => m.transactionId !== movimiento.transactionId);
        },
        error: () => alert('Error al eliminar movimiento')
      });
    }

    cancelarEdicion() {
      this.editando = false;
      this.movimientoEditando = null;
    }
  }
