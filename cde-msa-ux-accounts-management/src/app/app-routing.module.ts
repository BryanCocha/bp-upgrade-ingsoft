import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientesListComponent } from './features/clientes/ui/clientes-list.component';
import { CuentasListComponent } from './features/cuentas/ui/cuentas-list.component';
import { MovimientosListComponent } from './features/movimientos/ui/movimientos-list.component';
import { ReportesListComponent } from './features/reportes/ui/reportes-list.component';

const routes: Routes = [
  { path: 'clientes', component: ClientesListComponent },
  { path: 'cuentas', component: CuentasListComponent },
  { path: 'movimientos', component: MovimientosListComponent },
  { path: 'reportes', component: ReportesListComponent },
  { path: '', redirectTo: '/clientes', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
