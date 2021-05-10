import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { InputTextModule } from 'primeng/inputtext';
import { InputMaskModule } from 'primeng/inputmask';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { ConfirmPopupModule } from 'primeng/confirmpopup';

import { ConfirmationService } from 'primeng/api';

import { ColaboradorRoutingModule } from './colaborador-routing.module';
import { ColaboradorCadastroComponent } from './colaborador-cadastro/colaborador-cadastro.component';
import { ColaboradorListagemComponent } from './colaborador-listagem/colaborador-listagem.component';


@NgModule({
  declarations: [ColaboradorCadastroComponent, ColaboradorListagemComponent],
  imports: [
    CommonModule,
    FormsModule,

    InputTextModule,
    InputMaskModule,
    ButtonModule,
    TableModule,
    ConfirmPopupModule,

    ColaboradorRoutingModule,
  ],
  providers: [
    ConfirmationService
  ]
})
export class ColaboradorModule { }
