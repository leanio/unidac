import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TableModule } from 'primeng/table';

import { AlimentoRoutingModule } from './alimento-routing.module';
import { AlimentoListagemComponent } from './alimento-listagem/alimento-listagem.component';

@NgModule({
  declarations: [
    AlimentoListagemComponent
  ],
  imports: [
    CommonModule,

    TableModule,
    
    AlimentoRoutingModule
  ]
})
export class AlimentoModule { }
