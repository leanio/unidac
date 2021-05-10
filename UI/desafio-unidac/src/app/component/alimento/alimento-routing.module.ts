import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlimentoListagemComponent } from './alimento-listagem/alimento-listagem.component';

const routes: Routes = [
  {path: '', component: AlimentoListagemComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlimentoRoutingModule { }
