import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: 'colaboradores', loadChildren: () => import('../app/component/colaborador/colaborador.module').then(m => m.ColaboradorModule)},
  {path: 'alimentos', loadChildren: () => import('../app/component/alimento/alimento.module').then(m => m.AlimentoModule)},
  {path: '', loadChildren: () => import('../app/component/colaborador/colaborador.module').then(m => m.ColaboradorModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
