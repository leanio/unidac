import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ColaboradorCadastroComponent } from './colaborador-cadastro/colaborador-cadastro.component';
import { ColaboradorListagemComponent } from './colaborador-listagem/colaborador-listagem.component';

const routes: Routes = [
  {path:'', component: ColaboradorListagemComponent},
  {path: 'cadastro', component: ColaboradorCadastroComponent},
  {path: ':id', component: ColaboradorCadastroComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ColaboradorRoutingModule { }
