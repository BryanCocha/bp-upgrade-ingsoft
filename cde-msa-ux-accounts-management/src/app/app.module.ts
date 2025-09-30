import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientesListComponent } from './features/clientes/ui/clientes-list.component';
import { CuentasListComponent } from './features/cuentas/ui/cuentas-list.component';
import { MovimientosListComponent } from './features/movimientos/ui/movimientos-list.component';
import { ReportesListComponent } from './features/reportes/ui/reportes-list.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientesListComponent,
    CuentasListComponent,
    MovimientosListComponent,
    ReportesListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
